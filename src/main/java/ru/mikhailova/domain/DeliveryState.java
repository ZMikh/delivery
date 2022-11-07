package ru.mikhailova.domain;

public enum DeliveryState {
    NEW,
    IN_PROCESSING,
    REGISTERED,
    PAID,
    RECEIVED,
    FINISHED,
    TECH_ERROR,
    CLIENT_CANCELLATION,
    NO_FEEDBACK,
    PAYMENT_ERROR,
}
