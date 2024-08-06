package com.cqrs.cqrs.example.employepoc.query.events;


import com.cqrs.cqrs.example.employepoc.command.events.*;
import org.springframework.stereotype.Service;

@Service

public interface HardwareEventHandlerInterface {
    void on(HardwareCreatedEvent event);

    void on(HardwareUpdatedEvent event);

    void on(HardwareDeletedEvent event);
}
