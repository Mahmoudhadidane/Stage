package com.cqrs.cqrs.example.employepoc.command.rest.service;

import com.cqrs.cqrs.example.employepoc.command.rest.dto.Hardware;
import com.cqrs.cqrs.example.employepoc.command.rest.repository.HardwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HardwareService {

    private final HardwareRepository hardwareRepository;

    @Autowired
    public HardwareService(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    public Hardware saveHardware(Hardware hardware) {
        return hardwareRepository.save(hardware);
    }

    public Hardware updateHardware(String id, Hardware hardwareDetails) {
        Optional<Hardware> optionalHardware = hardwareRepository.findById(id);

        if (optionalHardware.isPresent()) {
            Hardware hardware = optionalHardware.get();
            hardware.setDirection(hardwareDetails.getDirection());
            hardware.setType(hardwareDetails.getType());
            hardware.setSerialNumber(hardwareDetails.getSerialNumber());
            hardware.setCommType(hardwareDetails.getCommType());
            hardware.setTrigger(hardwareDetails.getTrigger());
            hardware.setIpAddress(hardwareDetails.getIpAddress());
            hardware.setPortNumber(hardwareDetails.getPortNumber());
            hardware.setDate(hardwareDetails.getDate());
            hardware.setOtherAttributes(hardwareDetails.getOtherAttributes());
            return hardwareRepository.save(hardware);
        } else {
            return null;
        }
    }

    public void deleteHardware(String id) {
        hardwareRepository.deleteById(id);
    }
}
