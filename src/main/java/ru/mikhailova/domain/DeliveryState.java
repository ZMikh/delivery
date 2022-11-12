package ru.mikhailova.domain;

public enum DeliveryState {
    NEW,
    IN_PROCESSING,
    REGISTERED,
    PAID,
    FINISHED,
    TECH_ERROR,
    CLIENT_CANCELLATION,
    PAYMENT_ERROR
}
