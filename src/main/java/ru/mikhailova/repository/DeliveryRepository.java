package ru.mikhailova.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mikhailova.domain.Delivery;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    @Override
    @EntityGraph(value = "Delivery.withShoppingcarts")
    Optional<Delivery> findById(Long aLong);

    @Override
    @EntityGraph(value = "Delivery.withShoppingcarts")
    List<Delivery> findAll();
}
