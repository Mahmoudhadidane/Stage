package com.cqrs.cqrs.example.employepoc.command.rest.controllers;

import com.cqrs.cqrs.example.employepoc.command.commands.CreateHardwareCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.DeleteHardwareCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.UpdateHardwareCommand;
import com.cqrs.cqrs.example.employepoc.command.rest.dto.Hardware;
import com.cqrs.cqrs.example.employepoc.command.rest.response.HardwareResponse;
import com.hydatis.cqrsref.infrastructure.CommandDispatcher;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/hardware")
@RequiredArgsConstructor

public class HardwareController {
    private final CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<HardwareResponse> saveHardware(@RequestBody Hardware hardware) {
        CreateHardwareCommand createHardwareCommand = new CreateHardwareCommand();
        var id = UUID.randomUUID().toString();
        createHardwareCommand.setId(id);
        createHardwareCommand.setHardware(hardware);
        commandDispatcher.send(createHardwareCommand);
        return ResponseEntity.ok(new HardwareResponse("Hardware created", createHardwareCommand.getHardware()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HardwareResponse> updateHardware(@PathVariable String id, @RequestBody Hardware hardware) {
        UpdateHardwareCommand updatedHardware = new UpdateHardwareCommand();
        updatedHardware.setId(id);
        updatedHardware.setHardware(hardware);
        updatedHardware.getHardware().setId(id);
        commandDispatcher.send(updatedHardware);
        return ResponseEntity.ok(new HardwareResponse("Hardware updated", updatedHardware.getHardware()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HardwareResponse> deleteHardware(@PathVariable String id, @RequestBody Hardware hardware) {
        DeleteHardwareCommand deletedHardwareCommand = new DeleteHardwareCommand();
        deletedHardwareCommand.setId(id);
        deletedHardwareCommand.setHardware(hardware);
        deletedHardwareCommand.getHardware().setId(id);
        commandDispatcher.send(deletedHardwareCommand);
        return ResponseEntity.ok(new HardwareResponse("Hardware deleted", deletedHardwareCommand.getHardware()));
    }
}
