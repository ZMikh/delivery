package ru.mikhailova.service.serviceTask.compensation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailova.repository.DeliveryRepository;
//TODO service
@Service
@RequiredArgsConstructor
public class CompensationServiceImpl implements CompensationService {
    private final DeliveryRepository repository;
}
