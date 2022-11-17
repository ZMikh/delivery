package ru.mikhailova.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import ru.mikhailova.controller.dto.CartResponseDto;
import ru.mikhailova.domain.Cart;

@Component
public class CartMapper {
    private final ModelMapper mapper;

    public CartMapper() {
        this.mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.typeMap(CartResponseDto.class, Cart.class)
                .addMappings(mpr -> mpr.skip(Cart::setId));
    }

    public Cart toCart(CartResponseDto cartResponseDto) {
        return mapper.map(cartResponseDto, Cart.class);
    }
}
