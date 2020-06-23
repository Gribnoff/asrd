package com.kropotov.asrd.services.springdatajpa.docs;

import com.kropotov.asrd.converters.docs.ActInputControlToDto;
import com.kropotov.asrd.dto.docs.ActInputControlDto;
import com.kropotov.asrd.entities.docs.ActInputControl;
import com.kropotov.asrd.exceptions.NotFoundException;
import com.kropotov.asrd.repositories.ActInputControlRepository;
import com.kropotov.asrd.services.ActInputControlService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.kropotov.asrd.services.springdatajpa.specification.ActInputControlSpecification.*;

@Service
@RequiredArgsConstructor
public class ActInputControlSDService implements ActInputControlService {

    private final ActInputControlRepository actInputControlRepository;
    //private final DtoToActInputControl dtoToActInputControl;
    private final ActInputControlToDto actInputControlToDto;

    @Override
    public Optional<List<ActInputControl>> getAll() {
        return Optional.ofNullable(actInputControlRepository.findAll());

    }

    public Page<ActInputControl> getAll(Pageable pageable) {
        return actInputControlRepository.findAll(pageable);
    }

    public Page<ActInputControl> getAll(Byte entityStatus, String system, String device, String invoiceNumber, Byte result,
                                LocalDateTime createdAtFrom, LocalDateTime createdAtTo, LocalDateTime updatedAtFrom, LocalDateTime updatedAtTo,
                                String number, LocalDate actDateFrom, LocalDate actDateTo, String userName,
                                Pageable pageable) {

        Specification<ActInputControl> specification = Specification.where(null);

        if (entityStatus != null)
            specification = specification.and(hasStatus(entityStatus));

        if (system != null && !system.isEmpty())
            specification = specification.and(inSystem(system));

        if (device != null && !device.isEmpty())
            specification = specification.and(inDevice(device));

        if (invoiceNumber != null)
            specification = specification.and(hasInvoiceNumberLike(invoiceNumber));

        if (result != null)
            specification = specification.and(hasResult(result));

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

        if (actDateFrom != null)
            specification = specification.and(actDateAfter(actDateFrom));

        if (actDateTo != null)
            specification = specification.and(actDateBefore(actDateTo));

        if (userName != null && !userName.isEmpty())
            specification = specification.and(hasUserLike(userName));

        return actInputControlRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<ActInputControl> getById(@NonNull Long id) {
        return actInputControlRepository.findById(id);
    }

    @Override
    @Transactional
    public ActInputControl save(ActInputControl act) {
        return actInputControlRepository.save(act);
    }


/*    @Override
    @Transactional
    public ActInputControlDto saveDto(ActInputControlDto actDto) {
        ActInputControl detachedAct = dtoToActInputControl.convert(actDto);
        ActInputControl savedAct = actInputControlRepository.save(detachedAct);
        return actInputControlToDto.convert(savedAct);
    }*/

    @Override
    @Transactional
    public ActInputControlDto getDtoById(Long id) {
        return actInputControlToDto.convert(getById(id).orElseThrow(
                () -> new NotFoundException("ActInputControl with id = " + id + " not found")
        ));
    }

    @Override
    public void deleteById(Long id) {
        actInputControlRepository.findById(id);
    }
}
