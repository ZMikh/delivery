package ru.mikhailova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.dto.DeliveryRequestCreateDto;
import ru.mikhailova.dto.DeliveryRequestUpdateDto;
import ru.mikhailova.dto.DeliveryResponseDto;
import ru.mikhailova.mapper.DeliveryMapper;
import ru.mikhailova.service.DeliveryService;
import ru.mikhailova.service.UpdateDeliveryInfo;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery")
public class DeliveryController {
    private final DeliveryService service;
    private final DeliveryMapper mapper;

    @PostMapping("/create")
    public DeliveryResponseDto create(@RequestBody DeliveryRequestCreateDto deliveryRequestCreateDto) {
        Delivery delivery = service.createDelivery(mapper.toEntity(deliveryRequestCreateDto));
        return mapper.toDto(delivery);
    }

    @GetMapping("/get/{id}")
    public DeliveryResponseDto getById(@PathVariable Long id) {
        Delivery delivery = service.getDeliveryById(id);
        return mapper.toDto(delivery);
    }

    @GetMapping("/get-all")
    public List<DeliveryResponseDto> getAll() {
        List<Delivery> deliveries = service.getAllDeliveries();
        return deliveries.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteDeliveryById(id);
    }

    @PutMapping("/update/{id}")
    public DeliveryResponseDto updateById(@PathVariable Long id, @RequestBody DeliveryRequestUpdateDto dto) {
        UpdateDeliveryInfo updateDeliveryInfo = mapper.toEntity(dto);
        return mapper.toDto(service.updateDeliveryById(id, updateDeliveryInfo));
    }
}
