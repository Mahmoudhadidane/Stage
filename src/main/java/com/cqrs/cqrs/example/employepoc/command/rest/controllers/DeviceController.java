package com.cqrs.cqrs.example.employepoc.command.rest.controllers;

import com.cqrs.cqrs.example.employepoc.command.commands.CreateDeviceCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.DeleteDeviceCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.UpdateDeviceCommand;
import com.cqrs.cqrs.example.employepoc.command.rest.dto.Device;
import com.cqrs.cqrs.example.employepoc.command.rest.response.DeviceResponse;
import com.hydatis.cqrsref.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    private final CommandDispatcher commandDispatcher;

    @Autowired
    public DeviceController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PostMapping
    public ResponseEntity<DeviceResponse> saveDevice(@RequestBody Device device) {
        CreateDeviceCommand createDeviceCommand = new CreateDeviceCommand();
        var id = UUID.randomUUID().toString();
        createDeviceCommand.setId(id);
        createDeviceCommand.setDevice(device);
        commandDispatcher.send(createDeviceCommand);
        return ResponseEntity.ok(new DeviceResponse("Device created", createDeviceCommand.getDevice()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceResponse> updateDevice(@PathVariable String id, @RequestBody Device device) {
        UpdateDeviceCommand updatedDevice = new UpdateDeviceCommand();
        updatedDevice.setId(id);
        updatedDevice.setDevice(device);
        updatedDevice.getDevice().setId(id);
        commandDispatcher.send(updatedDevice);
        return ResponseEntity.ok(new DeviceResponse("Device updated", updatedDevice.getDevice()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceResponse> deleteDevice(@PathVariable String id, @RequestBody Device device) {
        DeleteDeviceCommand deletedDeviceCommand = new DeleteDeviceCommand();
        deletedDeviceCommand.setId(id);
        deletedDeviceCommand.setDevice(device);
        deletedDeviceCommand.getDevice().setId(id);
        commandDispatcher.send(deletedDeviceCommand);
        return ResponseEntity.ok(new DeviceResponse("Device deleted", deletedDeviceCommand.getDevice()));
    }
}
