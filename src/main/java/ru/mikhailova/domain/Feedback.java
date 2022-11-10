package ru.mikhailova.domain;

public enum Feedback {
    BAD(1), POOR(2), ACCEPTABLE(3), GOOD(4), EXCELLENT(5);

    Feedback(final Integer rating) {
    }
}
