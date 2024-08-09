package com.cqrs.cqrs.example.employepoc.query.handlers;

import com.cqrs.cqrs.example.employepoc.query.queries.FindHardwareByIdQuery;
import com.cqrs.cqrs.example.employepoc.query.queries.FindAllHardwareQuery;
import com.cqrs.cqrs.example.employepoc.query.queries.GetSerialNumberQuery;
import com.cqrs.cqrs.example.employepoc.query.rest.dto.Hardware;
import com.cqrs.cqrs.example.employepoc.query.rest.repository.HardwareQueryRepository;
import com.cqrs.cqrs.zkteco4j.cnn.Connector;
import com.cqrs.cqrs.zkteco4j.terminal.ZKTerminal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@EnableMongoRepositories(basePackages = "com.cqrs.cqrs.example.employepoc.query.rest.repository", includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = HardwareQueryRepository.class))
public class HardwareQueryHandler implements HardwareQueryHandlerInterface {
    private final HardwareQueryRepository hardwareQueryRepository;
    @Autowired
    public HardwareQueryHandler(HardwareQueryRepository hardwareQueryRepository) {
        this.hardwareQueryRepository = hardwareQueryRepository;
    }

    @Override
    public List<Hardware> handle(FindAllHardwareQuery query) {

        return hardwareQueryRepository.findAll();
    }

    @Override
    public Hardware handle( FindHardwareByIdQuery query) {
        return hardwareQueryRepository.findById((query.getId())).orElseThrow(() -> new RuntimeException("hardware not found"));
    }



    @Override

    public String handle(GetSerialNumberQuery query) {
        // Récupérer tous les matériels correspondant à l'adresse IP donnée
        List<Hardware> hardwareList = hardwareQueryRepository.findByIpAddress(query.getIpAddress());

        if (hardwareList.isEmpty()) {
            throw new RuntimeException("Hardware not found with IP address: " + query.getIpAddress());
        } else if (hardwareList.size() > 1) {
            throw new RuntimeException("Multiple hardware found with IP address: " + query.getIpAddress());
        }

        Hardware hardware = hardwareList.get(0);

        ZKTerminal terminal = new ZKTerminal(query.getIpAddress(), query.getPortNumber(), Connector.ConnectorType.TCP);
        boolean connectionEstablished = terminal.establishFullConnection(0);

        if (connectionEstablished) {
            try {
                return terminal.getDeviceSerialNumber();
            } catch (IOException e) {
                log.error("Error retrieving serial number from device", e);
                throw new RuntimeException("Error retrieving serial number: " + e.getMessage(), e);
            }
        } else {
            log.warn("Failed to establish connection with device at IP: {}", query.getIpAddress());
            throw new RuntimeException("Failed to establish connection with device at IP: " + query.getIpAddress());
        }


    }}

