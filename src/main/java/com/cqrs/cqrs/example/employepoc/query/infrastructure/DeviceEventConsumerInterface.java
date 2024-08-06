//package com.cqrs.cqrs.example.employepoc.query.infrastructure;
//
//import com.cqrs.cqrs.example.employepoc.command.events.*;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.messaging.handler.annotation.Payload;
//
//public interface DeviceEventConsumerInterface {
//    void consume(@Payload DeviceCreatedEvent event, Acknowledgment ack);
//
//    void consume(@Payload DeviceUpdatedEvent event, Acknowledgment ack);
//
//    void consume(@Payload DeviceDeletedEvent event, Acknowledgment ack);
//}
