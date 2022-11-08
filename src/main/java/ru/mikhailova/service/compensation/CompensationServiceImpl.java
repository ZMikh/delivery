package ru.mikhailova.service.compensation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mikhailova.repository.DeliveryRepository;

@Component
@RequiredArgsConstructor
public class CompensationServiceImpl implements CompensationService {
    private final DeliveryRepository repository;
}
