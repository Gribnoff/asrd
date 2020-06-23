package com.kropotov.asrd.facades.docs;

import com.kropotov.asrd.controllers.util.PageValues;
import com.kropotov.asrd.controllers.util.PageWrapper;
import com.kropotov.asrd.converters.UserToSimple;
import com.kropotov.asrd.converters.docs.DtoToActInputControl;
import com.kropotov.asrd.dto.docs.ActInputControlDto;
import com.kropotov.asrd.entities.docs.ActInputControl;
import com.kropotov.asrd.exceptions.StorageException;
import com.kropotov.asrd.services.ActInputControlService;
import com.kropotov.asrd.services.StorageService;
import com.kropotov.asrd.services.UserService;
import com.kropotov.asrd.services.springdatajpa.docs.ActInputControlSDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ActInputControlFacade {
    private final ActInputControlService actService;
    private final UserService userService;
    private final DtoToActInputControl dtoToActInputControl;
    private final UserToSimple userToSimple;
    private final StorageService storageService;

    // todo продумать обращение по несуществующему id
    public ActInputControlDto getDtoById(Long id) {
        return id == null ? new ActInputControlDto() : actService.getDtoById(id);
    }

    public void fillPage(Model model, Byte entityStatus, String system, String device, String invoiceNumber, Byte result,
                         LocalDateTime createdAtFrom, LocalDateTime createdAtTo, LocalDateTime updatedAtFrom, LocalDateTime updatedAtTo,
                         String number, LocalDate actDateFrom, LocalDate actDateTo, String userName, Pageable pageable) {
        PageWrapper<ActInputControl> page = new PageWrapper<>(
                ((ActInputControlSDService)actService)
                        .getAll(entityStatus, system, device, invoiceNumber, result,
                                createdAtFrom, createdAtTo, updatedAtFrom, updatedAtTo,
                                number, actDateFrom, actDateTo, userName, pageable.previousOrFirst()), "/acts");
        PageValues.addContentToModel(model, page);
    }

    public ActInputControl save(ActInputControlDto actDto, String username) {
        actDto.setUser(userToSimple.convert(userService.findByUserName(username)));
        return actService.save(dtoToActInputControl.convert(actDto));
    }

    public void deleteFile(Long actId) {
        Optional<ActInputControl> actOptional = actService.getById(actId);
        try {
            if (actOptional.isPresent()) {
                storageService.delete("acts", actOptional.get().getPath());
                actOptional.get().setPath(null);
                actService.save(actOptional.get());
            }
        } catch (StorageException e) {
        }
    }
}
