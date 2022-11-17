package ru.mikhailova.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mikhailova.controller.dto.DeliveryRequestConfirmDto;
import ru.mikhailova.controller.dto.DeliveryRequestCreateDto;
import ru.mikhailova.controller.dto.DeliveryRequestUpdateDto;
import ru.mikhailova.controller.dto.DeliveryResponseDto;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.mapper.DeliveryMapper;
import ru.mikhailova.service.userTask.DeliveryConfirmInfo;
import ru.mikhailova.service.userTask.DeliveryService;
import ru.mikhailova.service.userTask.DeliveryUpdateInfo;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery")
public class DeliveryController {
    private final DeliveryService service;
    private final DeliveryMapper mapper;

    @PostMapping("/create")
    @ApiOperation("Создание доставки")
    public DeliveryResponseDto create(@RequestBody DeliveryRequestCreateDto deliveryRequestCreateDto) {
        Delivery delivery = service.createDelivery(mapper.toDelivery(deliveryRequestCreateDto));
        return mapper.toDeliveryResponseDto(delivery);
    }

    @GetMapping("/get/{id}")
    @ApiOperation("Получение доставки по идентификатору")
    public DeliveryResponseDto getDeliveryById(@PathVariable Long id) {
        return mapper.toDeliveryResponseDto(service.getDeliveryById(id));
    }

    @GetMapping("/get-all")
    @ApiOperation("Получение всех доставок")
    public List<DeliveryResponseDto> getAll() {
        return service.getAllDeliveries().stream()
                .map(mapper::toDeliveryResponseDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Удаление доставки по идентификатору")
    public void deleteById(@PathVariable Long id) {
        service.deleteDeliveryById(id);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("Обновление параметров доставки по идентификатору")
    public DeliveryResponseDto updateById(@PathVariable Long id, @RequestBody DeliveryRequestUpdateDto dto) {
        DeliveryUpdateInfo deliveryUpdateInfo = mapper.toDeliveryUpdateInfo(dto);
        return mapper.toDeliveryResponseDto(service.updateDeliveryById(id, deliveryUpdateInfo));
    }

    @PostMapping("/confirm/{id}")
    @ApiOperation("Подтверждение заказа")
    public DeliveryResponseDto confirm(@PathVariable Long id, @RequestBody DeliveryRequestConfirmDto dto) {
        DeliveryConfirmInfo deliveryConfirmInfo = mapper.toDeliveryConfirm(dto);
        return mapper.toDeliveryResponseDto(service.confirmDelivery(id, deliveryConfirmInfo));
    }

    @PostMapping("/pick-up/{id}")
    @ApiOperation("Выдача заказа клиенту самовывозом")
    public DeliveryResponseDto pickUp(@PathVariable Long id) {
        return mapper.toDeliveryResponseDto(service.pickUpDelivery(id));
    }
}
