//package com.cqrs.cqrs.example.employepoc.command.rest.controllers;
//
//import com.cqrs.cqrs.example.employepoc.command.commands.CreateDeviceCommand;
//import com.cqrs.cqrs.example.employepoc.command.commands.DeleteDeviceCommand;
//import com.cqrs.cqrs.example.employepoc.command.commands.UpdateDeviceCommand;
//import com.cqrs.cqrs.example.employepoc.command.rest.dto.Hardware;
//import com.cqrs.cqrs.example.employepoc.command.rest.response.HardwareResponse;
//import com.hydatis.cqrsref.infrastructure.CommandDispatcher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/hardware")
//public class HardwareController {
//    private final CommandDispatcher commandDispatcher;
//
//    @Autowired
//    public HardwareController(CommandDispatcher commandDispatcher) {
//        this.commandDispatcher = commandDispatcher;
//    }
//
//    @PostMapping
//    public ResponseEntity<HardwareResponse> saveHardware(@RequestBody Hardware hardware) {
//        CreateHardwareCommand createHardwareCommand = new CreateHardwareCommand();
//        var id = UUID.randomUUID().toString();
//        createHardwareCommand.setId(id);
//        createHardwareCommand.setHardware(hardware);
//        commandDispatcher.send(createHardwareCommand);
//        return ResponseEntity.ok(new HardwareResponse("Hardwarecreated", createHardwareCommand.getHardware()));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<HardwareResponse> updateHardware(@PathVariable String id, @RequestBody Hardware hardware) {
//        UpdateHardwareCommand updatedHardware = new UpdateHardwareCommand();
//        updatedHardware.setId(id);
//        updatedHardware.setHardware(hardware);
//        updatedHardware.getHardware().setId(id);
//        commandDispatcher.send(updatedHardware);
//        return ResponseEntity.ok(new HardwareResponse("Device updated", updatedHardware.getHardware()));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<HardwareResponse> deleteHardware(@PathVariable String id, @RequestBody Hardware hardware) {
//        DeleteHardwareCommand deletedHardwareCommand = new DeleteHardwareCommand();
//        deletedHardwareCommand.setId(id);
//        deletedHardwareCommand.setHardware(hardware);
//        deletedHardwareCommand.getHardware().setId(id);
//        commandDispatcher.send(deletedHardwareCommand);
//        return ResponseEntity.ok(new HardwareResponse("Hardware deleted", deletedHardwareCommand.getHardware()));
//    }
//}
