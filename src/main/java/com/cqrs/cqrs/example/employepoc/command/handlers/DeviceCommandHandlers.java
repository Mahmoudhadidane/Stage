package com.cqrs.cqrs.example.employepoc.command.handlers;

import com.cqrs.cqrs.example.employepoc.command.commands.CreateDeviceCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.DeleteDeviceCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.UpdateDeviceCommand;
import com.cqrs.cqrs.example.employepoc.command.domain.DeviceAggregate;
import com.cqrs.cqrs.example.employepoc.command.rest.dto.Device;
import com.cqrs.cqrs.example.employepoc.command.rest.repository.DeviceReprository;
import com.hydatis.cqrsref.handlers.EventSourcingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@ComponentScan("com.example.employepoc.command.rest.repository")
@ComponentScan("com.example.employepoc.infrastructure")
public class

DeviceCommandHandlers implements DeviceCommandHandlersInterface{
    private final EventSourcingHandler<DeviceAggregate> eventSourceHandler;
    private final DeviceReprository deviceRepository;

    @Autowired
    public DeviceCommandHandlers(EventSourcingHandler<DeviceAggregate> eventSourceHandler , DeviceReprository deviceRepository ) {
        this.eventSourceHandler = eventSourceHandler ;
        this.deviceRepository = deviceRepository ;
    }

    @Override
    public void handle(CreateDeviceCommand createDeviceCommand) {
        Device newDevice = Device.builder()
                .id((UUID.randomUUID().toString()))
                .name(createDeviceCommand.getDevice().getName())
                .build();
        System.out.println("Device to save: " + newDevice.toString());
        deviceRepository.save(newDevice);
        createDeviceCommand.getDevice().setId(newDevice.getId());
        DeviceAggregate deviceAggregate = new DeviceAggregate(createDeviceCommand);
        eventSourceHandler.save(deviceAggregate);
    }

    @Override
    public void handle(UpdateDeviceCommand updateDeviceCommand) {
        Device deviceToUpdate = deviceRepository.findById(updateDeviceCommand.getDevice().getId())
                .orElseThrow(() -> new RuntimeException("Device not found"));
        deviceToUpdate.setName(updateDeviceCommand.getDevice().getName());
        deviceRepository.save(deviceToUpdate);
        DeviceAggregate deviceAggregate = new DeviceAggregate(new UpdateDeviceCommand());
        eventSourceHandler.save(deviceAggregate);
    }
    @Override
    public void handle(DeleteDeviceCommand deleteDeviceCommand) {
        Device deviceToDelete = deviceRepository.findById(deleteDeviceCommand.getDevice().getId())
                .orElseThrow(() -> new RuntimeException("device not found"));
        deviceRepository.save(deviceToDelete);
        DeviceAggregate deviceAggregate = new DeviceAggregate(new DeleteDeviceCommand());
        eventSourceHandler.save(deviceAggregate);
    }
}
