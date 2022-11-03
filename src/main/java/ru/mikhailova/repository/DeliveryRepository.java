package ru.mikhailova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mikhailova.domain.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
