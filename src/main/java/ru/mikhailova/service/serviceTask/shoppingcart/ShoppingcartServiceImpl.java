package ru.mikhailova.service.serviceTask.shoppingcart;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.dto.ShoppingcartResponseDtoList;
import ru.mikhailova.repository.DeliveryRepository;
import ru.mikhailova.resttemplate.ShoppingcartRestTemplate;

@Service
@RequiredArgsConstructor
public class ShoppingcartServiceImpl implements ShoppingcartService {
    private final DeliveryRepository repository;
    private final ShoppingcartRestTemplate shoppingcartRestTemplate;

    @Override
    public Delivery getDelivery(Long id) throws JsonProcessingException {
        ResponseEntity<ShoppingcartResponseDtoList> resource = shoppingcartRestTemplate.getResource(id);
        ShoppingcartResponseDtoList body = resource.getBody();
        return repository.findById(id).orElseThrow();
    }
}
