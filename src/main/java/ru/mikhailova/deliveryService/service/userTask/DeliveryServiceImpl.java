package ru.mikhailova.deliveryService.service.userTask;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailova.deliveryService.domain.ConfirmState;
import ru.mikhailova.deliveryService.domain.Delivery;
import ru.mikhailova.deliveryService.repository.DeliveryRepository;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {
    private final TaskService taskService;
    private final DeliveryRepository repository;
    private final RuntimeService runtimeService;

    @Transactional
    @Override
    public Delivery createDelivery(Delivery delivery) {
        Delivery savedDelivery = repository.save(delivery);
        log.info("Delivery with id: {} is created", savedDelivery.getId());

        HashMap<String, Object> map = new HashMap<>();
        map.put("id", savedDelivery.getId());
        runtimeService.startProcessInstanceByKey("delivery", map);

        return savedDelivery;
    }

    @Transactional(readOnly = true)
    @Override
    public Delivery getDeliveryById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Delivery> getAllDeliveries() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Delivery updateDeliveryById(Long id, DeliveryUpdateInfo deliveryUpdateInfo) {
        Delivery delivery = repository.findById(id).orElseThrow();
        delivery.setDeliveryTime(deliveryUpdateInfo.getDeliveryTime());
        delivery.setDescription(deliveryUpdateInfo.getDescription());
        delivery.setAddress(delivery.getAddress());
        repository.save(delivery);
        log.info("Delivery with id: {} is updated", id);
        return delivery;
    }

    @Transactional
    @Override
    public void deleteDeliveryById(Long id) {
        repository.deleteById(id);
        log.info("Delivery with id: {} is deleted", id);
    }

    @Transactional
    @Override
    public Delivery confirmDelivery(Long id, DeliveryConfirmInfo deliveryConfirmInfo) {
        Delivery delivery = repository.findById(id).orElseThrow();
        delivery.setDeliveryTime(deliveryConfirmInfo.getDeliveryTime());
        delivery.setIsPickUp(deliveryConfirmInfo.getIsPickUp());
        delivery.setDescription(deliveryConfirmInfo.getDescription());
        delivery.setAddress(deliveryConfirmInfo.getAddress());
        repository.save(delivery);
        log.info("Delivery with id {} confirmed", id);

        Task task = taskService.createTaskQuery()
                .taskDefinitionKey("taskConfirmation")
                .processVariableValueEquals("id", delivery.getId())
                .singleResult();
        if (task == null) {
            throw new RuntimeException();
        }
        taskService.setVariable(task.getId(), "isCancelled", ConfirmState.CANCELLED.equals(deliveryConfirmInfo.getConfirmationState()));
        taskService.setVariable(task.getId(), "isPickUp", deliveryConfirmInfo.getIsPickUp());
        taskService.complete(task.getId());

        return delivery;
    }

    @Transactional
    @Override
    public Delivery pickUpDelivery(Long id) {
        Delivery delivery = repository.findById(id).orElseThrow();

        Task task = taskService.createTaskQuery()
                .taskDefinitionKey("taskPickUp")
                .processVariableValueEquals("id", delivery.getId())
                .singleResult();

        if (task == null) {
            throw new RuntimeException();
        }
        taskService.complete(task.getId());
        log.info("Delivery with id {} picked-up by client", id);
        return delivery;
    }
}
