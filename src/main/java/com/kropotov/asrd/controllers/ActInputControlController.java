package com.kropotov.asrd.controllers;

import com.kropotov.asrd.controllers.errors.CustomErrorController;
import com.kropotov.asrd.dto.docs.ActInputControlDto;
import com.kropotov.asrd.facades.docs.ActInputControlFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("acts")
@RequiredArgsConstructor
public class ActInputControlController extends CustomErrorController {
    private final ActInputControlFacade actFacade;

    @GetMapping("/{id}/show")
    public String displayById(@PathVariable Long id, Model model) {
        model.addAttribute("act", actFacade.getDtoById(id));
        return "acts/show";
    }

    @GetMapping("/{id}/update")
    public String updateAct(@PathVariable Long id, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("act", actFacade.getDtoById(id));
        return "acts/edit-act";
    }

    @GetMapping
    public String displayAll(Model model,
                             @RequestParam(required = false) Byte entityStatus,
                             @RequestParam(required = false) String system,
                             @RequestParam(required = false) String device,
                             @RequestParam(required = false) String invoiceNumber,
                             @RequestParam(required = false) Byte result,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdAtFrom,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdAtTo,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime updatedAtFrom,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime updatedAtTo,
                             @RequestParam(required = false) String number,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate actDateFrom,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate actDateTo,
                             @RequestParam(required = false) String userName,
                             @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        actFacade.fillPage(model, entityStatus, system, device, invoiceNumber, result, createdAtFrom,
                createdAtTo, updatedAtFrom, updatedAtTo, number, actDateFrom, actDateTo, userName, pageable);
        return "acts/list-acts";
    }

    // todo добавить проверку с какой страницы пришел, НЕЛЬЗЯ ДОПУСТИТЬ ЦИКЛИЧНОГО УДАЛЕНИЯ ВСЕХ ФАЙЛОВ ПО ID!!!!!!!
    // todo Перенести удаление файлов в FileController. Уточнить у преподавателя
    @GetMapping("/{actId}/file/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteActFileById(@PathVariable("actId") Long actId) {
        actFacade.deleteFile(actId);
    }

    @PostMapping("/edit")
    public String saveOrUpdate(@ModelAttribute ActInputControlDto actDto, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        actFacade.save(actDto, principal.getName());
        return "redirect:/acts";
    }
}
