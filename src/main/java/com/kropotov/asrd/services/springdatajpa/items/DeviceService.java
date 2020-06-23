package com.kropotov.asrd.services.springdatajpa.items;

import com.kropotov.asrd.converters.items.DeviceToDto;
import com.kropotov.asrd.dto.items.DeviceDto;
import com.kropotov.asrd.entities.items.Device;
import com.kropotov.asrd.entities.titles.DeviceTitle;
import com.kropotov.asrd.repositories.DeviceRepository;
import com.kropotov.asrd.services.CrudService;
import lombok.RequiredArgsConstructor;
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

import static com.kropotov.asrd.services.springdatajpa.specification.DeviceSpecification.*;

@Service
@RequiredArgsConstructor
public class DeviceService implements CrudService<Device, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    private final DeviceRepository deviceRepository;
    private final DeviceToDto deviceToDto;


    public Page<Device> getAll(Pageable pageable) {
        return deviceRepository.findAll(pageable);
    }

    @Override
    public Optional<List<Device>> getAll() {
        return Optional.ofNullable(deviceRepository.findAll());
    }

    public Page<Device> getAll(Byte entityStatus, String system, LocalDateTime createdAtFrom, LocalDateTime createdAtTo, LocalDateTime updatedAtFrom, LocalDateTime updatedAtTo,
                                      String number, Byte location, String purpose, String purposePassport, LocalDate vintageFrom, LocalDate vintageTo, Integer vpNumber,
                                      LocalDate otkDateFrom, LocalDate otkDateTo, LocalDate vpDateFrom, LocalDate vpDateTo, String title, String userName,
                                      Pageable pageable) {

        Specification<Device> specification = Specification.where(null);
        if (entityStatus != null)
            specification = specification.and(hasStatus(entityStatus));

        if (system != null && !system.isEmpty())
            specification = specification.and(inSystem(system));

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

        return deviceRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<Device> getById(Long id) {
        return id == null ? Optional.empty() : deviceRepository.findById(id);
    }

    @Override
    @Transactional
    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    public Device getByNumberAndTitle(String number, DeviceTitle title) {
        return deviceRepository.findByNumberAndTitle(number, title);
    }

    @Transactional
    public DeviceDto getDtoById(Long id) {
        return deviceToDto.convert(getById(id).orElse(new Device()));
    }

    @Override
    public void deleteById(Long id) {
        deviceRepository.deleteById(id);
    }
}
