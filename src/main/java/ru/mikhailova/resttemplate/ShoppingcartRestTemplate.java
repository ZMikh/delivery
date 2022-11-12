package ru.mikhailova.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.mikhailova.dto.ShoppingcartResponseDtoList;

@Component
public class ShoppingcartRestTemplate {
    private final RestTemplate restTemplate;

    private static final String DELIVERY_RESOURCE_URL = "http://localhost:8080/api/v1/shoppingcart/get/";

    public ShoppingcartRestTemplate() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<ShoppingcartResponseDtoList> getResource(Long id) throws JsonProcessingException {
        return restTemplate.getForEntity(DELIVERY_RESOURCE_URL + id, ShoppingcartResponseDtoList.class);
    }
}
