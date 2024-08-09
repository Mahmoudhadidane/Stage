package com.cqrs.cqrs.example.employepoc.query.rest.controllers;

import com.cqrs.cqrs.example.employepoc.query.handlers.HardwareQueryHandler;
import com.cqrs.cqrs.example.employepoc.query.queries.GetSerialNumberQuery;
import com.cqrs.cqrs.example.employepoc.query.rest.dto.Hardware;
import com.cqrs.cqrs.example.employepoc.query.rest.response.FindAllHardwareResponse;
import com.cqrs.cqrs.example.employepoc.query.queries.FindAllHardwareQuery;
import com.hydatis.cqrsref.infrastructure.IQueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hardware")
public class HardwareQueryController {

    @Autowired
    private IQueryDispatcher queryDispatcher;
    @Autowired
    private HardwareQueryHandler hardwareQueryHandler;

    @GetMapping
    public ResponseEntity<FindAllHardwareResponse> getAllHardware() {
        List<Hardware> hardware = queryDispatcher.send(new FindAllHardwareQuery());
        var response = FindAllHardwareResponse.builder()
                .hardware(hardware)
                .message("Hardware retrieved successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/serial-number")
    public ResponseEntity<String> getSerialNumber(
            @RequestParam String ipAddress,
            @RequestParam int portNumber) {
        try {
            String serialNumber = hardwareQueryHandler.handle(new GetSerialNumberQuery(ipAddress, portNumber));
            return new ResponseEntity<>(serialNumber, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
