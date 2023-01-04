package org.masil.domain.core;


import com.likelen.identifier.core.LongValueGenerator;
import com.likelen.identifier.generator.IdGeneratorFactory;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DomainEventTest {

    @Data
    public static class Foo {
        private String name;
        private Integer age;
    }

    @Data
    public static class FooDto {
        private String name;
        private Integer age;
    }

    @Data
    public static class FooV2 {
        private String name;
        private Integer age;
        private String phone;
    }

    @BeforeEach
    void setUp() {
        IdGeneratorFactory idGeneratorFactory = new IdGeneratorFactory(() -> 111);
        LongValueGenerator longValueGenerator = idGeneratorFactory.create();
    }

    @Test
    void name() {
        // serialize
        DomainEventId id = DomainEventId.of(111L);
        Foo payload = new Foo();

        DomainEvent event = DomainEvent.of(payload);

        assertThat(event.getId()).isEqualTo(id);
        assertThat(event.getType()).isEqualTo(Foo.class.getName());

        String serialized = event.serialize();

        // =================================================

        // deserialized
        DomainEvent deserialized = DomainEvent.deserialize(serialized);

        assertThat(deserialized).isEqualTo(event);

        FooDto deserializedPayload = deserialized.getPayload(FooDto.class);

        assertThat(deserializedPayload.getAge()).isEqualTo(payload.getAge());
        assertThat(deserializedPayload.getName()).isEqualTo(payload.getName());


        //Foo payload = deserialized.getPayload(Foo.class);
    }




}
