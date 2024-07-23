package com.cqrs.cqrs.example.employepoc.command.domain;

import com.cqrs.cqrs.example.employepoc.command.commands.CreateDeviceCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.DeleteDeviceCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.UpdateDeviceCommand;
import com.cqrs.cqrs.example.employepoc.command.events.DeviceCreatedEvent;
import com.cqrs.cqrs.example.employepoc.command.events.DeviceDeletedEvent;
import com.cqrs.cqrs.example.employepoc.command.events.DeviceUpdatedEvent;
import com.hydatis.cqrsref.domain.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceAggregate extends AggregateRoot {
    private String id ;
    public DeviceAggregate(CreateDeviceCommand command) {
        raiseEvent(DeviceCreatedEvent.builder()
                .identifier(command.getId())
                .device(command.getDevice())
                .build());
    }

    public void apply(DeviceUpdatedEvent event) {
        this.id = event.getIdentifier();
    }
    public DeviceAggregate(UpdateDeviceCommand command) {
        raiseEvent(DeviceUpdatedEvent.builder()
                .identifier(command.getId())
                .device(command.getDevice())
                .build());
    }

    public void apply(DeviceDeletedEvent event) {
        this.id = event.getIdentifier();
    }
    public DeviceAggregate(DeleteDeviceCommand command) {
        raiseEvent(DeviceDeletedEvent.builder()
                .identifier(command.getId())
                .device(command.getDevice())
                .build());
    }

    public void apply(DeviceCreatedEvent event) {
        this.id = event.getIdentifier();
    }


}
