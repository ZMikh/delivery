package ru.mikhailova.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import ru.mikhailova.domain.Shoppingcart;
import ru.mikhailova.dto.ShoppingcartResponseDto;

@Component
public class ShoppingcartMapper {
    private final ModelMapper mapper;

    public ShoppingcartMapper() {
        this.mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ShoppingcartResponseDto toShoppingcartResponseDto(Shoppingcart shoppingcart) {
       return mapper.map(shoppingcart, ShoppingcartResponseDto.class);
    }
}
