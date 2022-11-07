package ru.mikhailova.mapper;

import org.springframework.stereotype.Component;
import ru.mikhailova.domain.DeliveryState;

import java.util.Map;

@Component
public class StateMapper {
    private static final Map<String, DeliveryState> map = Map.of(
            "inProcessingState", DeliveryState.IN_PROCESSING,
            "registeredState", DeliveryState.REGISTERED,
            "paymentErrorState", DeliveryState.PAYMENT_ERROR,
            "paidState", DeliveryState.PAID,
            "receivedState", DeliveryState.RECEIVED,
            "noFeedbackState", DeliveryState.NO_FEEDBACK,
            "finishedState", DeliveryState.FINISHED,
            "clientCancellationState", DeliveryState.CLIENT_CANCELLATION,
            "techErrorState", DeliveryState.TECH_ERROR
    );

    public DeliveryState getStateByActivityId(String activityId) {
        return map.get(activityId);
    }

}
