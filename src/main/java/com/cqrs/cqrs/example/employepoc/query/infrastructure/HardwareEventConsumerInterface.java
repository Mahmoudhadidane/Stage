package com.cqrs.cqrs.example.employepoc.query.infrastructure;

import com.cqrs.cqrs.example.employepoc.command.events.*;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface HardwareEventConsumerInterface {
    void consume(@Payload HardwareCreatedEvent event, Acknowledgment ack);

    void consume(@Payload HardwareUpdatedEvent event, Acknowledgment ack);

    void consume(@Payload HardwareDeletedEvent event, Acknowledgment ack);
}
