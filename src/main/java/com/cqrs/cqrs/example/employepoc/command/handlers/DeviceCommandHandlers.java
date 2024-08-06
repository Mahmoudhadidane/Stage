//package com.cqrs.cqrs.example.employepoc.command.handlers;
//
//import com.cqrs.cqrs.example.employepoc.command.commands.CreateDeviceCommand;
//import com.cqrs.cqrs.example.employepoc.command.commands.DeleteDeviceCommand;
//import com.cqrs.cqrs.example.employepoc.command.commands.UpdateDeviceCommand;
//import com.cqrs.cqrs.example.employepoc.command.domain.DeviceAggregate;
//
//import com.cqrs.cqrs.example.employepoc.command.rest.dto.Hardware;
//import com.cqrs.cqrs.example.employepoc.command.rest.repository.HardwareReprository;
//import com.hydatis.cqrsref.handlers.EventSourcingHandler;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Slf4j
//@Service
//@ComponentScan("com.example.employepoc.command.rest.repository")
//@ComponentScan("com.example.employepoc.infrastructure")
//public class
//
//HardwareCommandHandlers implements HardwareCommandHandlersInterface{
//    private final EventSourcingHandler<HardwareAggregate> eventSourceHandler;
//    private final HardwareReprository hardwareReprository;
//
//    @Autowired
//    public HardwareCommandHandlers(EventSourcingHandler<HardwareAggregate> eventSourceHandler , HardwareReprository hardwareRepository ) {
//        this.eventSourceHandler = eventSourceHandler ;
//        this.hardwareReprository = hardwareRepository ;
//    }
//
//    @Override
//    public void handle(CreateHardwareCommand createHardwareCommand) {
//        Hardware newHardware = Hardware.builder()
//                .id((UUID.randomUUID().toString()))
//                .name(createHardwareCommand.getHardware().getName())
//                .build();
//        System.out.println("hard to save: " + newHardware.toString());
//        hardwareReprository.save(newHardware);
//        createHardwareCommand.getHardware().setId(newHardware.getId());
//        HardwareAggregate hardwareAggregate = new HardwareAggregate(createHardwareCommand);
//        eventSourceHandler.save(hardwareAggregate);
//    }
//
//    @Override
//    public void handle(UpdateHardwareCommand updateHardwareCommand) {
//        Hardware hardwareToUpdate = hardwareReprository.findById(updateHardwareCommand.getHardware().getId())
//                .orElseThrow(() -> new RuntimeException("Hardware not found"));
//        hardwareToUpdate.setName(updateHardwareCommand.getHardware().getName());
//        hardwareReprository.save(hardwareToUpdate);
//        HardwareAggregate hardAggregate = new HardwareAggregate(new UpdateHardwareCommand());
//        eventSourceHandler.save(hardwareAggregate);
//    }
//    @Override
//    public void handle(DeleteHardwareCommand deleteHardwareCommand) {
//        Hardware hardwareToDelete = hardwareReprository.findById(deleteHardwareCommand.getHardware().getId())
//                .orElseThrow(() -> new RuntimeException("Hardware not found"));
//        hardwareReprository.save(hardwareToDelete);
//        HardwareAggregate hardwareAggregate = new hardwareAggregate(new DeleteHardwareCommand());
//        eventSourceHandler.save(hardwareAggregate);
//    }
//}
