//package com.cqrs.cqrs.example.employepoc.query.events;
//
//import com.cqrs.cqrs.example.employepoc.command.events.DeviceCreatedEvent;
//import com.cqrs.cqrs.example.employepoc.command.events.DeviceDeletedEvent;
//import com.cqrs.cqrs.example.employepoc.command.events.DeviceUpdatedEvent;
//import com.cqrs.cqrs.example.employepoc.query.rest.dto.Device;
//import com.cqrs.cqrs.example.employepoc.query.rest.repository.DeviceQueryRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//
//@Service
//@RequiredArgsConstructor
//public class DeviceEventHandler implements DeviceEventHandlerInterface {
//
//    private final DeviceQueryRepository deviceQueryRepository;
//
//    @Override
//    public void on(DeviceCreatedEvent event) {
//        System.out.println("Device created event received"+event.toString());
//        Device e = new Device().builder()
//                .id(String.valueOf(event.getDevice().getId()))
//                .name(event.getDevice().getName())
//                .build();
//        deviceQueryRepository.save(e);
//    }
//
//    @Override
//    public void on(DeviceUpdatedEvent event) {
//        Device e = new Device().builder()
//                .id(String.valueOf(event.getDevice().getId()))
//                .name(event.getDevice().getName())
//                .build();
//        deviceQueryRepository.save(e);
//
//    }
//
//    @Override
//    public void on(DeviceDeletedEvent event) {
//        Device e = new Device().builder()
//                .id(String.valueOf(event.getDevice().getId()))
//                .name(event.getDevice().getName())
//                .build();
//
//        deviceQueryRepository.save(e);
//
//    }
//}
