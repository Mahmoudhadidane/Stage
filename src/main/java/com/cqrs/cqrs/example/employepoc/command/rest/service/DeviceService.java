package com.cqrs.cqrs.example.employepoc.command.rest.service;

import com.cqrs.cqrs.example.employepoc.command.rest.dto.Device;

import com.cqrs.cqrs.example.employepoc.command.rest.repository.DeviceReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DeviceService {
    private final DeviceReprository deviceRepository;

    @Autowired
    public DeviceService(DeviceReprository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }


    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }
    public Device updateDevice (String id, Device deviceDetails) {
        Optional<Device> optionalDevice = deviceRepository.findById(id);

        if(optionalDevice.isPresent()) {
            Device device = optionalDevice.get();
            device.setName(deviceDetails.getName());
            return deviceRepository.save(device);
        } else {
            return null;
        }
    }
    public void deleteDevice(String id) {
        deviceRepository.deleteById(id);
    }
}