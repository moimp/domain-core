package org.masil.domain.core;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class GeneralDomainEvent implements DomainEvent {


    private static final DomainEventId of = DomainEventId.of(1L);
    DomainEventId id;
    LocalDateTime occurredAt;
    Object payload;
    String type;

    public GeneralDomainEvent(Object payload) {
        this(of, payload);
    }

    public GeneralDomainEvent(DomainEventId id, Object payload) {
        this.id = id;
        this.occurredAt = LocalDateTime.now();
        this.type = payload.getClass().getName();
        this.payload = Serializer.getInstance().serialize(payload);
    }

    @Override
    public String serialize() {
        return Serializer.getInstance().serialize(this);
    }

    public <T> T getPayload(Class<T> aClass) {
        return Serializer.getInstance().deserialize((String) payload, aClass);
    }
}
