package com.kropotov.asrd.controllers;


import com.kropotov.asrd.controllers.util.PageValues;
import com.kropotov.asrd.controllers.util.PageWrapper;
import com.kropotov.asrd.converters.UserToSimple;
import com.kropotov.asrd.converters.items.DeviceToDto;
import com.kropotov.asrd.converters.items.DtoToDevice;
import com.kropotov.asrd.dto.items.DeviceDto;
import com.kropotov.asrd.entities.items.Device;
import com.kropotov.asrd.services.UserService;
import com.kropotov.asrd.services.springdatajpa.items.DeviceComponentService;
import com.kropotov.asrd.services.springdatajpa.items.DeviceService;
import com.kropotov.asrd.services.springdatajpa.items.SystemService;
import com.kropotov.asrd.services.springdatajpa.titles.DeviceComponentTitleService;
import com.kropotov.asrd.services.springdatajpa.titles.DeviceTitleService;
import com.kropotov.asrd.services.springdatajpa.titles.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final DeviceTitleService deviceTitleService;
    private final UserService userService;
    private final TopicService topicService;
    private final DeviceComponentService deviceComponentService;
    private final SystemService systemService;
    private final DeviceComponentTitleService deviceComponentTitleService;
    private final UserToSimple userToSimple;
    private final DeviceToDto deviceToDto;
    private final DtoToDevice dtoToDevice;

    @GetMapping
    public String displayDevices(Model model,
                                 @RequestParam(required = false) Byte entityStatus,
                                 @RequestParam(required = false) String system,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdAtFrom,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdAtTo,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime updatedAtFrom,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime updatedAtTo,
                                 @RequestParam(required = false) String number,
                                 @RequestParam(required = false) Byte location,
                                 @RequestParam(required = false) String purpose,
                                 @RequestParam(required = false) String purposePassport,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vintageFrom,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vintageTo,
                                 @RequestParam(required = false) Integer vpNumber,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate otkDateFrom,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate otkDateTo,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vpDateFrom,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vpDateTo,
                                 @RequestParam(required = false) String title,
                                 @RequestParam(required = false) String userName,
                                 @PageableDefault(page = 0, size = 15, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        PageWrapper<Device> page = new PageWrapper<>(deviceService.getAll(
                entityStatus, system, createdAtFrom, createdAtTo, updatedAtFrom, updatedAtTo, number, location, purpose, purposePassport,
                vintageFrom, vintageTo, vpNumber, otkDateFrom, otkDateTo, vpDateFrom, vpDateTo, title, userName,
                pageable.previousOrFirst()), "/devices");

        PageValues.addContentToModel(model, page);
        model.addAttribute("topicTitleList", topicService.getAll().orElse(new ArrayList<>()));

        return "devices/list-devices";
    }

    @GetMapping("/{id}/show")
    public String displayById(@PathVariable Long id, Model model) {
        if (deviceService.getById(id).isPresent()) {
            model.addAttribute("device", deviceService.getDtoById(id));
            return "devices/show";
        } else {
            return "redirect:/devices";
        }
    }

    @GetMapping("/{id}/update")
    public String displayDeviceForm(Model model, Principal principal, @PathVariable("id") Long id,
                                    @RequestParam(value = "deviceTitle", required = false) Long deviceTitleId) throws Exception {
        if (principal == null) {
            return "redirect:/login";
        }

        DeviceDto deviceDto;
        if (deviceTitleId != null) {
            Device device = new Device();
            device.setTitle(deviceTitleService.getById(deviceTitleId).get());
            deviceDto = deviceToDto.convert(device);
        } else {
            deviceDto = deviceService.getDtoById(id);
        }

        model.addAttribute("systemTitles", deviceDto.getDeviceTitle().getSystemTitles());
        model.addAttribute("device", deviceDto);

        return "devices/edit-device";
    }

    // TODO нужен рефакторинг
    @PostMapping("/edit")
    public String editDevice(@ModelAttribute("device") DeviceDto deviceDto, BindingResult bindingResult, Model model,
                             Principal principal) {
        if (principal == null) {
            model.addAttribute("device", deviceDto);
            model.addAttribute("deviceCreationError", "Необходима авторизация");
            return "devices/edit-device";
        }
        /*if (bindingResult.hasErrors()) {
            model.addAttribute("deviceCreationError", "BindingResult error!");
            return "devices/edit-device";
        }*/
        /*Device existing = deviceService.getByTitle(device.getTitle());
        if (existing != null && !device.getId().equals(existing.getId())) {
            model.addAttribute("device", device);
            model.addAttribute("deviceCreationError", "Прибор стакими именем уже сузществует");
            return "devices/edit-deviceя";
        }*/

        deviceDto.setUser(userToSimple.convert(userService.findByUserName(principal.getName())));
        deviceService.save(dtoToDevice.convert(deviceDto));
        return "redirect:/devices";
    }
}
