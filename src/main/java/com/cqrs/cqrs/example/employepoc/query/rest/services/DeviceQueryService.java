package com.cqrs.cqrs.example.employepoc.query.rest.services;

import com.cqrs.cqrs.example.employepoc.query.rest.dto.Device;
import com.cqrs.cqrs.example.employepoc.query.rest.repository.DeviceQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceQueryService {
    private final DeviceQueryRepository deviceQueryRepository;

    @Autowired
    public DeviceQueryService(DeviceQueryRepository deviceQueryRepository) {
        this.deviceQueryRepository = deviceQueryRepository;
    }

    public List<Device> getAllDevice() {
        return deviceQueryRepository.findAll();
    }
    public Device getEmployeeById(String id) {
        Optional<Device> optionalDevice = deviceQueryRepository.findById(id);
        return optionalDevice.orElse(null);
    }
}
