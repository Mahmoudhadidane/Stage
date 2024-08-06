package com.cqrs.cqrs.example.employepoc.query.rest.services;



import com.cqrs.cqrs.example.employepoc.query.rest.dto.Hardware;
import com.cqrs.cqrs.example.employepoc.query.rest.repository.HardwareQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HardwareQueryService {
    private final HardwareQueryRepository hardwareQueryRepository;

    @Autowired
    public HardwareQueryService(HardwareQueryRepository hardwareQueryRepository) {
        this.hardwareQueryRepository = hardwareQueryRepository;
    }

    public List<Hardware> getAllHardware() {
        return hardwareQueryRepository.findAll();
    }
    public Hardware getHardwareById(String id) {
        Optional<Hardware> optionalHardware = hardwareQueryRepository.findById(id);
        return optionalHardware.orElse(null);
    }
}
