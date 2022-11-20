package ru.mikhailova.deliveryService.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import ru.mikhailova.deliveryService.controller.dto.DeliveryRequestConfirmDto;
import ru.mikhailova.deliveryService.controller.dto.DeliveryRequestCreateDto;
import ru.mikhailova.deliveryService.controller.dto.DeliveryRequestUpdateDto;
import ru.mikhailova.deliveryService.controller.dto.DeliveryResponseDto;
import ru.mikhailova.deliveryService.domain.Delivery;
import ru.mikhailova.deliveryService.service.userTask.DeliveryConfirmInfo;
import ru.mikhailova.deliveryService.service.userTask.DeliveryUpdateInfo;

import static ru.mikhailova.deliveryService.domain.DeliveryState.NEW;

@Component
public class DeliveryMapper {
    private final ModelMapper mapper;

    public DeliveryMapper() {
        this.mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.typeMap(DeliveryRequestCreateDto.class, Delivery.class)
                .addMapping(dto -> NEW, Delivery::setState);
    }

    public DeliveryResponseDto toDeliveryResponseDto(Delivery delivery) {
        return mapper.map(delivery, DeliveryResponseDto.class);
    }

    public Delivery toDelivery(DeliveryRequestCreateDto dto) {
        return mapper.map(dto, Delivery.class);
    }

    public DeliveryUpdateInfo toDeliveryUpdateInfo(DeliveryRequestUpdateDto dto) {
        return mapper.map(dto, DeliveryUpdateInfo.class);
    }

    public DeliveryConfirmInfo toDeliveryConfirm(DeliveryRequestConfirmDto dto) {
        return mapper.map(dto, DeliveryConfirmInfo.class);
    }
}
