package org.masil.domain.core;

import lombok.Value;

@Value(staticConstructor = "of")
public class DomainEventId {

    Long value;

    public Long get() {
        return value;
    }
}
