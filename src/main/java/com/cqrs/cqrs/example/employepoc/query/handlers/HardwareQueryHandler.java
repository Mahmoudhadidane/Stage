package com.cqrs.cqrs.example.employepoc.query.handlers;

import com.cqrs.cqrs.example.employepoc.query.queries.*;
import com.cqrs.cqrs.example.employepoc.query.rest.dto.Hardware;
import com.cqrs.cqrs.example.employepoc.query.rest.repository.HardwareQueryRepository;
import com.cqrs.cqrs.example.employepoc.query.rest.response.InfoResponse;
import com.cqrs.cqrs.zkteco4j.Packet.GetReadSizes;
import com.cqrs.cqrs.zkteco4j.Packet.GetTime;
import com.cqrs.cqrs.zkteco4j.cnn.Connector;
import com.cqrs.cqrs.zkteco4j.commands.CommandReplyCode;
import com.cqrs.cqrs.zkteco4j.commands.GetReadSizesReply;
import com.cqrs.cqrs.zkteco4j.commands.GetTimeReply;
import com.cqrs.cqrs.zkteco4j.terminal.ZKTerminal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.copyOfRange;

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
    public Hardware handle(FindHardwareByIdQuery query) {
        return hardwareQueryRepository.findById(query.getId())
                .orElseThrow(() -> new RuntimeException("Hardware not found"));
    }

    @Override
    public String handle(GetSerialNumberQuery query) {
        List<Hardware> hardwareList = hardwareQueryRepository.findByIpAddress(query.getIpAddress());

        if (hardwareList.isEmpty()) {
            throw new RuntimeException("Hardware not found with IP address: " + query.getIpAddress());
        } else if (hardwareList.size() > 1) {
            throw new RuntimeException("Multiple hardware found with IP address: " + query.getIpAddress());
        }

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
    }


    public String handle(GetTimeQuery query) {
        List<Hardware> hardwareList1 = hardwareQueryRepository.findByIpAddress(query.getIpAddress());

        if (hardwareList1.isEmpty()) {
            throw new RuntimeException("Hardware not found with IP address: " + query.getIpAddress());
        } else if (hardwareList1.size() > 1) {
            throw new RuntimeException("Multiple hardware found with IP address: " + query.getIpAddress());
        }

        ZKTerminal terminal = new ZKTerminal(query.getIpAddress(), query.getPortNumber(), Connector.ConnectorType.TCP);
        boolean connectionEstablished = terminal.establishFullConnection(0);

        if (connectionEstablished) {
            try {
                return terminal.getTime();
            } catch (IOException e) {
                log.error("Error retrieving device time", e);
                throw new RuntimeException("Error retrieving device time: " + e.getMessage(), e);
            }
        } else {
            log.warn("Failed to establish connection with device at IP: {}", query.getIpAddress());
            throw new RuntimeException("Failed to establish connection with device at IP: " + query.getIpAddress());
        }
    }


    // Other methods

    public InfoResponse handle(GetInformationQuery query) {
        List<Hardware> hardwareList2 = hardwareQueryRepository.findByIpAddress(query.getIpAddress());

        if (hardwareList2.isEmpty()) {
            throw new RuntimeException("Hardware not found with IP address: " + query.getIpAddress());
        } else if (hardwareList2.size() > 1) {
            throw new RuntimeException("Multiple hardware found with IP address: " + query.getIpAddress());
        }

        ZKTerminal terminal = new ZKTerminal(query.getIpAddress(), query.getPortNumber(), Connector.ConnectorType.TCP);
        boolean connectionEstablished = terminal.establishFullConnection(0);

        if (connectionEstablished) {
            try {
                return terminal.getReadSizes();
            } catch (IOException e) {
                log.error("Error retrieving information from device", e);
                throw new RuntimeException("Error retrieving information: " + e.getMessage(), e);
            }
        } else {
            log.warn("Failed to establish connection with device at IP: {}", query.getIpAddress());
            throw new RuntimeException("Failed to establish connection with device at IP: " + query.getIpAddress());
        }
    }

}
