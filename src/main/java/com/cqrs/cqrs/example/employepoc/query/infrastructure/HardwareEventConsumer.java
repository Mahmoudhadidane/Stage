package com.cqrs.cqrs.example.employepoc.query.infrastructure;

import com.cqrs.cqrs.example.employepoc.command.events.*;
import com.cqrs.cqrs.example.employepoc.query.events.HardwareEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;


@Service
public class HardwareEventConsumer implements HardwareEventConsumerInterface{
    private final HardwareEventHandler handler ;
    @Autowired
    public HardwareEventConsumer(HardwareEventHandler handler) {
        this.handler = handler;
    }

    @KafkaListener(topics = "HardwareCreatedEvent", groupId = "hardware-consumer")
    @Override
    public void consume(HardwareCreatedEvent event, Acknowledgment ack) {
        this.handler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "HardwareUpdatedEvent", groupId = "hardware-consumer")
    @Override
    public void consume(HardwareUpdatedEvent event, Acknowledgment ack) {
        this.handler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "HardwareDeletedEvent", groupId = "hardware-consumer")
    @Override
    public void consume(HardwareDeletedEvent event, Acknowledgment ack) {
        this.handler.on(event);
        ack.acknowledge();
    }
}
