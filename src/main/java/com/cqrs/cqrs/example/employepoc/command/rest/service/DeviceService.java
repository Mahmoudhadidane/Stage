//package com.cqrs.cqrs.example.employepoc.command.rest.service;
//
//import com.cqrs.cqrs.example.employepoc.command.rest.repository.Device;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//@Service
//public class DeviceService {
//    private final Device deviceRepository;
//
//    @Autowired
//    public DeviceService(Device deviceRepository) {
//        this.deviceRepository = deviceRepository;
//    }
//
//
//    public com.cqrs.cqrs.example.employepoc.command.rest.dto.Device saveDevice(com.cqrs.cqrs.example.employepoc.command.rest.dto.Device device) {
//        return deviceRepository.save(device);
//    }
//    public com.cqrs.cqrs.example.employepoc.command.rest.dto.Device updateDevice (String id, com.cqrs.cqrs.example.employepoc.command.rest.dto.Device deviceDetails) {
//        Optional<com.cqrs.cqrs.example.employepoc.command.rest.dto.Device> optionalDevice = deviceRepository.findById(id);
//
//        if(optionalDevice.isPresent()) {
//            com.cqrs.cqrs.example.employepoc.command.rest.dto.Device device = optionalDevice.get();
//            device.setName(deviceDetails.getName());
//            return deviceRepository.save(device);
//        } else {
//            return null;
//        }
//    }
//    public void deleteDevice(String id) {
//        deviceRepository.deleteById(id);
//    }
//}