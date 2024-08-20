package com.cqrs.cqrs.example.employepoc.command.handlers;

import com.cqrs.cqrs.example.employepoc.command.commands.CreateHardwareCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.DeleteHardwareCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.UpdateHardwareCommand;
import com.cqrs.cqrs.example.employepoc.command.domain.HardwareAggregate;
import com.cqrs.cqrs.example.employepoc.command.rest.dto.Hardware;
import com.cqrs.cqrs.example.employepoc.command.rest.repository.HardwareRepository;
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
public class HardwareCommandHandlers implements HardwareCommandHandlersInterface {
    private final EventSourcingHandler<HardwareAggregate> eventSourceHandler;
    private final HardwareRepository hardwareRepository;

    @Autowired
    public HardwareCommandHandlers(EventSourcingHandler<HardwareAggregate> eventSourceHandler, HardwareRepository hardwareRepository) {
        this.eventSourceHandler = eventSourceHandler;
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public void handle(CreateHardwareCommand createHardwareCommand) {
        if (createHardwareCommand.getHardware().getIpAddress().trim().isEmpty()){
            throw new RuntimeException("invalid ip adress");

        }

        Hardware newHardware = new Hardware();
        newHardware.setId(UUID.randomUUID().toString());
        newHardware.setDirection(createHardwareCommand.getHardware().getDirection());
        newHardware.setType(createHardwareCommand.getHardware().getType());
        newHardware.setSerialNumber(createHardwareCommand.getHardware().getSerialNumber());
        newHardware.setCommType(createHardwareCommand.getHardware().getCommType());
        newHardware.setTrigger(createHardwareCommand.getHardware().getTrigger());
        newHardware.setIpAddress(createHardwareCommand.getHardware().getIpAddress());
        newHardware.setPortNumber(createHardwareCommand.getHardware().getPortNumber());
        newHardware.setDate(createHardwareCommand.getHardware().getDate());
        newHardware.setOtherAttributes(createHardwareCommand.getHardware().getOtherAttributes());

        hardwareRepository.save(newHardware);
        createHardwareCommand.getHardware().setId(newHardware.getId());
        HardwareAggregate hardwareAggregate = new HardwareAggregate(createHardwareCommand);
        eventSourceHandler.save(hardwareAggregate);
    }

    @Override
    public void handle(UpdateHardwareCommand updateHardwareCommand) {
        System.out.println("id:"+updateHardwareCommand.getId());
        Hardware hardwareToUpdate = hardwareRepository.findById(updateHardwareCommand.getId())
                .orElseThrow(() -> new RuntimeException("Hardware not found"));
        System.out.println("hardware : "+hardwareToUpdate);
        hardwareToUpdate.setDirection(updateHardwareCommand.getHardware().getDirection());
        hardwareToUpdate.setType(updateHardwareCommand.getHardware().getType());
        hardwareToUpdate.setSerialNumber(updateHardwareCommand.getHardware().getSerialNumber());
        hardwareToUpdate.setCommType(updateHardwareCommand.getHardware().getCommType());
        hardwareToUpdate.setTrigger(updateHardwareCommand.getHardware().getTrigger());
        hardwareToUpdate.setIpAddress(updateHardwareCommand.getHardware().getIpAddress());
        hardwareToUpdate.setPortNumber(updateHardwareCommand.getHardware().getPortNumber());
        hardwareToUpdate.setDate(updateHardwareCommand.getHardware().getDate());
        hardwareToUpdate.setOtherAttributes(updateHardwareCommand.getHardware().getOtherAttributes());
        hardwareRepository.save(hardwareToUpdate);
        updateHardwareCommand.setHardware(hardwareToUpdate);
        HardwareAggregate hardwareAggregate = new HardwareAggregate(updateHardwareCommand);
        eventSourceHandler.save(hardwareAggregate);
    }

    @Override
    public void handle(DeleteHardwareCommand deleteHardwareCommand) {
        Hardware hardwareToDelete = hardwareRepository.findById(deleteHardwareCommand.getHardware().getId())
                .orElseThrow(() -> new RuntimeException("Hardware not found"));

        hardwareRepository.delete(hardwareToDelete);
        HardwareAggregate hardwareAggregate = new HardwareAggregate(deleteHardwareCommand);
        eventSourceHandler.save(hardwareAggregate);
    }
}
