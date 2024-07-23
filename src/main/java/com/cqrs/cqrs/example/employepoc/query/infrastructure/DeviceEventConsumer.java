package com.cqrs.cqrs.example.employepoc.query.infrastructure;

import com.cqrs.cqrs.example.employepoc.command.events.*;
import com.cqrs.cqrs.example.employepoc.query.events.DeviceEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class DeviceEventConsumer implements DeviceEventConsumerInterface{
    private final DeviceEventHandler handler;

    @Autowired
    public DeviceEventConsumer(DeviceEventHandler handler) {
        this.handler = handler;
    }

    @KafkaListener(topics = "DeviceCreatedEvent", groupId = "device-consumer")
    @Override
    public void consume(DeviceCreatedEvent event, Acknowledgment ack) {
        this.handler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "DeviceUpdatedEvent", groupId = "device-consumer")
    @Override
    public void consume(DeviceUpdatedEvent event, Acknowledgment ack) {
        this.handler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "DeviceDeletedEvent", groupId = "device-consumer")
    @Override
    public void consume(DeviceDeletedEvent event, Acknowledgment ack) {
        this.handler.on(event);
        ack.acknowledge();
    }
}
