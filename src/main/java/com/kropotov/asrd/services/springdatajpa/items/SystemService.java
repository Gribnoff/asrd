package com.kropotov.asrd.services.springdatajpa.items;

import com.kropotov.asrd.converters.items.ControlSystemToDto;
import com.kropotov.asrd.dto.items.ControlSystemDto;
import com.kropotov.asrd.entities.items.ControlSystem;
import com.kropotov.asrd.entities.items.ControlSystem_;
import com.kropotov.asrd.entities.titles.SystemTitle;
import com.kropotov.asrd.entities.titles.SystemTitle_;
import com.kropotov.asrd.repositories.SystemRepository;
import com.kropotov.asrd.services.CrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.kropotov.asrd.services.springdatajpa.specification.SystemSpecification.*;
import static java.util.stream.Collectors.toMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class SystemService implements CrudService<ControlSystem, Long> {
	@PersistenceContext
	private EntityManager entityManager;

	private final SystemRepository systemRepository;
	private final ControlSystemToDto controlSystemToDto;

	public Page<ControlSystem> getAll(Pageable pageable) {
		return systemRepository.findAll(pageable);
	}

	public Page<ControlSystem> getAll(Byte entityStatus, LocalDateTime createdAtFrom, LocalDateTime createdAtTo, LocalDateTime updatedAtFrom, LocalDateTime updatedAtTo,
									  String number, Byte location, String purpose, String purposePassport, LocalDate vintageFrom, LocalDate vintageTo, Integer vpNumber,
									  LocalDate otkDateFrom, LocalDate otkDateTo, LocalDate vpDateFrom, LocalDate vpDateTo, String title, String userName,
									  Pageable pageable) {

		Specification<ControlSystem> specification = getSpecificationFromGivenParams(entityStatus, createdAtFrom, createdAtTo, updatedAtFrom, updatedAtTo, number,
				location, purpose, purposePassport, vintageFrom, vintageTo, vpNumber, otkDateFrom, otkDateTo, vpDateFrom, vpDateTo, title, userName);

		return systemRepository.findAll(specification, pageable);
	}

	public Specification<ControlSystem> getSpecificationFromGivenParams(Byte entityStatus, LocalDateTime createdAtFrom, LocalDateTime createdAtTo, LocalDateTime updatedAtFrom,
																		 LocalDateTime updatedAtTo, String number, Byte location, String purpose, String purposePassport,
																		 LocalDate vintageFrom, LocalDate vintageTo, Integer vpNumber, LocalDate otkDateFrom, LocalDate otkDateTo,
																		 LocalDate vpDateFrom, LocalDate vpDateTo, String title, String userName) {

		Specification<ControlSystem> specification = Specification.where(null);

		if (entityStatus != null)
			specification = specification.and(hasStatus(entityStatus));

		if (createdAtFrom != null)
			specification = specification.and(createdAtAfter(createdAtFrom));

		if (createdAtTo != null)
			specification = specification.and(createdAtBefore(createdAtTo));

		if (updatedAtFrom != null)
			specification = specification.and(updatedAtAfter(updatedAtFrom));

		if (updatedAtTo != null)
			specification = specification.and(updatedAtBefore(updatedAtTo));

		if (number != null && !number.isEmpty())
			specification = specification.and(hasNumberLike(number));

		if (location != null)
			specification = specification.and(hasLocation(location));

		if (purpose != null && !purpose.isEmpty())
			specification = specification.and(hasPurposeLike(purpose));

		if (purposePassport != null && !purposePassport.isEmpty())
			specification = specification.and(hasPurposePassportLike(purposePassport));

		if (vintageFrom != null)
			specification = specification.and(vintageAfter(vintageFrom));

		if (vintageTo != null)
			specification = specification.and(vintageBefore(vintageTo));

		if (vpNumber != null)
			specification = specification.and(hasVpNumber(vpNumber));

		if (otkDateFrom != null)
			specification = specification.and(otkDateAfter(otkDateFrom));

		if (otkDateTo != null)
			specification = specification.and(otkDateBefore(otkDateTo));

		if (vpDateFrom != null)
			specification = specification.and(vpDateAfter(vpDateFrom));

		if (vpDateTo != null)
			specification = specification.and(vpDateBefore(vpDateTo));

		if (title != null && !title.isEmpty())
			specification = specification.and(hasTitleLike(title));

		if (userName != null && !userName.isEmpty())
			specification = specification.and(hasUserLike(userName));

		return specification;
	}

	@Override
	public Optional<List<ControlSystem>> getAll() {
		return Optional.ofNullable(systemRepository.findAll());
	}

	public Optional<ControlSystem> getById(Long id) {
		return id == null ? Optional.empty() : systemRepository.findById(id);
	}

	public ControlSystem save(ControlSystem system) {
		return systemRepository.save(system);
	}

	public ControlSystem getByNumberAndTitle(String number, SystemTitle title) {
		return systemRepository.findByNumberAndTitle(number, title);
	}

	@Transactional
	public ControlSystemDto getDtoById(Long id) {
		return controlSystemToDto.convert(getById(id).orElse(new ControlSystem()));
	}

	@Override
	public void deleteById(Long id) {
		systemRepository.deleteById(id);
	}

//	public Workbook generateReport(Byte entityStatus, LocalDateTime createdAtFrom, LocalDateTime createdAtTo, LocalDateTime updatedAtFrom,
//								   LocalDateTime updatedAtTo, String number, Byte location, String purpose, String purposePassport,
//								   LocalDate vintageFrom, LocalDate vintageTo, Integer vpNumber, LocalDate otkDateFrom, LocalDate otkDateTo,
//								   LocalDate vpDateFrom, LocalDate vpDateTo, String title, String userName) {
//
//		Specification<ControlSystem> specification = getSpecificationFromGivenParams(entityStatus, createdAtFrom, createdAtTo, updatedAtFrom, updatedAtTo, number,
//				location, purpose, purposePassport, vintageFrom, vintageTo, vpNumber, otkDateFrom, otkDateTo, vpDateFrom, vpDateTo, title, userName);
//		Map<String, Integer> resultMap = groupAndCount(specification);
//
//		Workbook workbook = new HSSFWorkbook();
//		Sheet sheet = workbook.createSheet("");
//		CellStyle style = createStyleForTitle(workbook);
//
//		int rowNum = 0;
//		Row row = sheet.createRow(rowNum);
//		Cell cell;
//
//		cell = row.createCell(0, CellType.STRING);
//		cell.setCellValue("Название системы");
//		cell = row.createCell(1, CellType.STRING);
//		cell.setCellValue("Количество");
//		row.forEach(cell1 -> cell1.setCellStyle(style));
//
//		for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
//			rowNum++;
//			row = sheet.createRow(rowNum);
//
//			cell = row.createCell(0, CellType.STRING);
//			cell.setCellValue(entry.getKey());
//			cell = row.createCell(1, CellType.NUMERIC);
//			cell.setCellValue(entry.getValue());
//		}
//
//		File file = new File("C:/demo/employee.xls");
//		file.getParentFile().mkdirs();
//
//		FileOutputStream outFile = null;
//		try {
//			outFile = new FileOutputStream(file);
//			workbook.write(outFile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Created file: " + file.getAbsolutePath());
//
//		return workbook;
//	}

	public Workbook generateReport(Specification<ControlSystem> specification) {
		Map<String, Integer> resultMap = groupAndCount(specification);

		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("");
		sheet.autoSizeColumn(0);
		CellStyle style = createStyleForTitle(workbook);

		int rowNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell;

		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Название системы");
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Количество");
		row.forEach(cell1 -> cell1.setCellStyle(style));

		for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
			rowNum++;
			row = sheet.createRow(rowNum);

			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(entry.getKey());
			cell = row.createCell(1, CellType.NUMERIC);
			cell.setCellValue(entry.getValue());
		}

		File file = new File("C:/demo/employee.xls");
		file.getParentFile().mkdirs();

		try(FileOutputStream outFile = new FileOutputStream(file)) {
			workbook.write(outFile);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Created file: " + file.getAbsolutePath());

		return workbook;
	}

	private static CellStyle createStyleForTitle(Workbook workbook) {
		Font font = workbook.createFont();
		font.setBold(true);
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

//	private Map<String, Integer> groupAndCount(Specification<ControlSystem> whereSpec) {
//		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		final CriteriaQuery<Tuple> query = criteriaBuilder.createQuery(Tuple.class);
//		final Root<ControlSystem> root = query.from(ControlSystem.class);
//		final Join<ControlSystem, SystemTitle> join
//		final Path<String> expression = root.join(ControlSystem_.title).get(SystemTitle_.title);
////		query.multiselect(expression, criteriaBuilder.count(root));
//		query.select(criteriaBuilder.tuple(expression, criteriaBuilder.count(root)));
//		query.where(whereSpec.toPredicate(root, query, criteriaBuilder));
//		query.groupBy(expression);
//		EntityManager em = this.entityManager;
//		TypedQuery<Tuple> tq = em.createQuery(query);
//		List<Tuple> resultList = tq.getResultList();
////		final List<Tuple> resultList = entityManager.createQuery(query).getResultList();
//		return resultList.stream()
//				.collect(toMap(
//						t -> t.get(0, String.class),
//						t -> t.get(1, Integer.class))
//				);
//	}

	private Map<String, Integer> groupAndCount(final Specification<ControlSystem> whereSpec) {
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Tuple> cr = cb.createTupleQuery();
		final Root<ControlSystem> root = cr.from(ControlSystem.class);
		final Join<ControlSystem, SystemTitle> join = root.join(ControlSystem_.title, JoinType.LEFT);

		cr.select(cb.tuple(join.get(SystemTitle_.title), cb.count(root)))
				.where(whereSpec.toPredicate(root, cr, cb))
				.groupBy(join.get(SystemTitle_.title));

		return entityManager.createQuery(cr)
				.getResultList()
				.stream()
				.collect(toMap(
						tuple -> tuple.get(0, String.class),
						tuple -> tuple.get(1, Integer.class)));
	}
}
