package com.cqrs.cqrs.example.employepoc.command.handlers;

import com.cqrs.cqrs.example.employepoc.command.commands.*;

public interface DeviceCommandHandlersInterface {
    void handle(CreateDeviceCommand createDeviceCommand);
    void handle(UpdateDeviceCommand updateDeviceCommand);
    void handle(DeleteDeviceCommand deleteDeviceCommand);
}
