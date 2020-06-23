package com.kropotov.asrd.services.springdatajpa.items;

import com.kropotov.asrd.converters.items.ControlSystemToDto;
import com.kropotov.asrd.dto.items.ControlSystemDto;
import com.kropotov.asrd.entities.items.ControlSystem;
import com.kropotov.asrd.entities.titles.SystemTitle;
import com.kropotov.asrd.repositories.SystemRepository;
import com.kropotov.asrd.services.CrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.kropotov.asrd.services.springdatajpa.specification.SystemSpecification.*;

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

        return systemRepository.findAll(specification, pageable);
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
}
