package ru.mikhailova.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.dto.DeliveryRequestCreateDto;
import ru.mikhailova.dto.DeliveryRequestUpdateDto;
import ru.mikhailova.dto.DeliveryResponseDto;
import ru.mikhailova.service.UpdateDeliveryInfo;

import static ru.mikhailova.domain.DeliveryState.NEW;

@Component
public class DeliveryMapper {
    private final ModelMapper mapper;

    public DeliveryMapper() {
        this.mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.typeMap(DeliveryRequestCreateDto.class, Delivery.class)
                .addMapping(dto -> NEW,  Delivery::setState);
    }

    public DeliveryResponseDto toDto(Delivery delivery) {
        return mapper.map(delivery, DeliveryResponseDto.class);
    }


    public Delivery toEntity(DeliveryRequestCreateDto dto) {
        return mapper.map(dto, Delivery.class);
    }
    public UpdateDeliveryInfo toEntity(DeliveryRequestUpdateDto dto) {
        return mapper.map(dto, UpdateDeliveryInfo.class);
    }
}
