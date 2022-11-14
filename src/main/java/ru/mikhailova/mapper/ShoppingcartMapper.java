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
        mapper.typeMap(ShoppingcartResponseDto.class, Shoppingcart.class)
                .addMappings(mpr -> mpr.skip(Shoppingcart::setId));
    }

    public Shoppingcart toShoppingcart(ShoppingcartResponseDto shoppingcartResponseDto) {
        return mapper.map(shoppingcartResponseDto, Shoppingcart.class);
    }
}
