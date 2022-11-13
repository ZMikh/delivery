package ru.mikhailova.service.serviceTask.shoppingcart;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mikhailova.dto.ShoppingcartResponseDtoList;

@Service
@RequiredArgsConstructor
public class ShoppingcartServiceImpl implements ShoppingcartService {
    private final RestTemplate restTemplate;

    private static final String DELIVERY_RESOURCE_URL = "http://localhost:8080/api/v1/shoppingcart/get/";

    public ShoppingcartServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    public ShoppingcartResponseDtoList getShoppingcartResponseDtoList(Long id) throws JsonProcessingException {
        ResponseEntity<ShoppingcartResponseDtoList> responseEntity = restTemplate.getForEntity(DELIVERY_RESOURCE_URL + id, ShoppingcartResponseDtoList.class);
        return responseEntity.getStatusCode() == HttpStatus.OK ? responseEntity.getBody() : null;
    }
}
