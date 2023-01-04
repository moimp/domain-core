package org.masil.domain.core;

import java.time.LocalDateTime;

public interface DomainEvent {

    static DomainEvent of(Object payload) {
        return new GeneralDomainEvent(payload);
    }

    static DomainEvent deserialize(String serialized) {
        return Serializer.getInstance().deserialize(serialized, GeneralDomainEvent.class);
    }

    DomainEventId getId();
    String getType();
    LocalDateTime getOccurredAt();
    String serialize();

    Object getPayload();
    <T> T getPayload(Class<T> tClass);
}
