package ru.mikhailova.service.cart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.mikhailova.domain.Cart;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.dto.CartResponseDtoList;
import ru.mikhailova.mapper.CartMapper;
import ru.mikhailova.repository.DeliveryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    private static final String CART_API = "api/v1/cart/get/";
    private final RestTemplate restTemplate;
    private final DeliveryRepository repository;
    private final CartMapper mapper;
    private final String cartUrl;
    private final Boolean cartStub;

    public CartServiceImpl(RestTemplate restTemplate,
                           DeliveryRepository repository,
                           CartMapper mapper,
                           @Value("${service.cart.url}") String cartUrl,
                           @Value("${stubs.cart.enable}") Boolean cartStub) {
        this.restTemplate = restTemplate;
        this.repository = repository;
        this.mapper = mapper;
        this.cartUrl = cartUrl;
        this.cartStub = cartStub;
    }

    @Transactional
    public void getCartResponseDtoList(Long id) {
        Delivery delivery = repository.findById(id).orElseThrow();
        List<Cart> carts;

        if (Boolean.TRUE.equals(cartStub)) {
            carts = generateCart();
        } else {
            log.info("Get cartResponseDtoList with delivery id: {} by service task", id);
            carts = getCart(id);
        }

        delivery.setCarts(carts);
        log.info("Cart is saved in delivery with id: {}", delivery.getId());
        repository.save(delivery);
    }

    private List<Cart> getCart(Long id) {
        ResponseEntity<CartResponseDtoList> responseEntity = restTemplate.getForEntity(cartUrl + CART_API + id,
                CartResponseDtoList.class);
        return  requireNonNull(responseEntity.getBody())
                .getCarts()
                .stream()
                .map(mapper::toCart)
                .collect(Collectors.toList());
    }

    private List<Cart> generateCart() {
        List<Cart> carts = new ArrayList<>();
        Cart cart = new Cart();
        cart.setProductName("Headphones");
        cart.setArticleNumber("TvH128D");
        cart.setAmount(2L);
        cart.setPricePerItem(30D);
        cart.setTotalCost(2L, 30D);
        cart.setDescription("Wireless headphones");

        carts.add(cart);
        return carts;
    }
}
