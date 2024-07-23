package com.cqrs.cqrs.example.employepoc.query.events;

import com.cqrs.cqrs.example.employepoc.command.events.*;
import org.springframework.stereotype.Service;

@Service
public interface DeviceEventHandlerInterface{
    void on(DeviceCreatedEvent event);
    void on(DeviceUpdatedEvent event);
    void on(DeviceDeletedEvent event);
}
