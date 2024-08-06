package com.cqrs.cqrs.example.employepoc.query.events;

import com.cqrs.cqrs.example.employepoc.command.events.HardwareCreatedEvent;
import com.cqrs.cqrs.example.employepoc.command.events.HardwareDeletedEvent;
import com.cqrs.cqrs.example.employepoc.command.events.HardwareUpdatedEvent;
import com.cqrs.cqrs.example.employepoc.query.rest.dto.Hardware;
import com.cqrs.cqrs.example.employepoc.query.rest.repository.HardwareQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HardwareEventHandler implements HardwareEventHandlerInterface {
    private final HardwareQueryRepository hardwareQueryRepository;

    @Override
    public void on(HardwareCreatedEvent event) {
        System.out.println("Hardware created event received: " + event.toString());
        Hardware h = Hardware.builder()
                .direction(event.getHardware().getDirection())
                .type(event.getHardware().getType())
                .serialNumber(event.getHardware().getSerialNumber())
                .commType(event.getHardware().getCommType())
                .trigger(event.getHardware().getTrigger())
                .ipAddress(event.getHardware().getIpAddress())
                .portNumber(event.getHardware().getPortNumber())
                .date(event.getHardware().getDate())
                .otherAttributes(event.getHardware().getOtherAttributes())
                .build();
        hardwareQueryRepository.save(h);
    }

    @Override
    public void on(HardwareUpdatedEvent event) {
        Hardware h = Hardware.builder()
                .direction(event.getHardware().getDirection())
                .type(event.getHardware().getType())
                .serialNumber(event.getHardware().getSerialNumber())
                .commType(event.getHardware().getCommType())
                .trigger(event.getHardware().getTrigger())
                .ipAddress(event.getHardware().getIpAddress())
                .portNumber(event.getHardware().getPortNumber())
                .date(event.getHardware().getDate())
                .otherAttributes(event.getHardware().getOtherAttributes())
                .build();
        hardwareQueryRepository.save(h);
    }

    @Override
    public void on(HardwareDeletedEvent event) {
        Hardware h = Hardware.builder()
                .direction(event.getHardware().getDirection())
                .type(event.getHardware().getType())
                .serialNumber(event.getHardware().getSerialNumber())
                .commType(event.getHardware().getCommType())
                .trigger(event.getHardware().getTrigger())
                .ipAddress(event.getHardware().getIpAddress())
                .portNumber(event.getHardware().getPortNumber())
                .date(event.getHardware().getDate())
                .otherAttributes(event.getHardware().getOtherAttributes())
                .build();

        hardwareQueryRepository.save(h);
    }
}
