package ru.mikhailova.service.serviceTask.shoppingcart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.domain.Shoppingcart;
import ru.mikhailova.dto.ShoppingcartResponseDto;
import ru.mikhailova.dto.ShoppingcartResponseDtoList;
import ru.mikhailova.mapper.ShoppingcartMapper;
import ru.mikhailova.repository.DeliveryRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingcartServiceImpl implements ShoppingcartService {
    private final RestTemplate restTemplate;

    private final DeliveryRepository repository;

    private final ShoppingcartMapper mapper;

    private static final String DELIVERY_RESOURCE_URL = "http://localhost:8080/api/v1/shoppingcart/get/";

    @Transactional
    public void getShoppingcartResponseDtoList(Long id) {
        ResponseEntity<ShoppingcartResponseDtoList> responseEntity = restTemplate.getForEntity(DELIVERY_RESOURCE_URL + id, ShoppingcartResponseDtoList.class);
        log.info("get shoppingcartResponseDtoList with delivery id: {} by service task", id);

        Delivery delivery = repository.findById(id).orElseThrow();
        List<ShoppingcartResponseDto> shoppingcartResponseDtoList = Objects.requireNonNull(responseEntity.getBody()).getShoppingcarts();
        List<Shoppingcart> shoppingcarts = shoppingcartResponseDtoList.stream()
                .map(mapper::toShoppingcart)
                .collect(Collectors.toList());
        delivery.setShoppingcarts(shoppingcarts);
        log.info("shoppingcart is saved in delivery with id: {}", delivery.getId());
        repository.save(delivery);
    }
}
