package ru.mikhailova.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.dto.DeliveryRequestConfirmDto;
import ru.mikhailova.dto.DeliveryRequestCreateDto;
import ru.mikhailova.dto.DeliveryRequestUpdateDto;
import ru.mikhailova.dto.DeliveryResponseDto;
import ru.mikhailova.service.DeliveryConfirmInfo;
import ru.mikhailova.service.DeliveryUpdateInfo;

import static ru.mikhailova.domain.DeliveryState.NEW;

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
