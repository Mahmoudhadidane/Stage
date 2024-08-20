package com.cqrs.cqrs.example.employepoc.query.rest.controllers;
import com.cqrs.cqrs.example.employepoc.query.handlers.HardwareQueryHandler;
import com.cqrs.cqrs.example.employepoc.query.queries.GetInformationQuery;
import com.cqrs.cqrs.example.employepoc.query.queries.GetSerialNumberQuery;
import com.cqrs.cqrs.example.employepoc.query.queries.GetTimeQuery;
import com.cqrs.cqrs.example.employepoc.query.rest.dto.Hardware;
import com.cqrs.cqrs.example.employepoc.query.rest.response.FindAllHardwareResponse;
import com.cqrs.cqrs.example.employepoc.query.queries.FindAllHardwareQuery;
import com.cqrs.cqrs.example.employepoc.query.rest.response.InfoResponse;
import com.cqrs.cqrs.example.employepoc.query.rest.response.TimeResponse;
import com.cqrs.cqrs.example.employepoc.query.rest.response.responeSN;
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
    public ResponseEntity<responeSN> getSerialNumber(
            @RequestParam String ipAddress,
            @RequestParam int portNumber) {

            String serialNumber = hardwareQueryHandler.handle(new GetSerialNumberQuery(ipAddress, portNumber));
            System.out.println(serialNumber);
            var response = responeSN.builder()
                    .SerialNumber(serialNumber)
                    .message("hello")
                    .build();
            return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/time")
    public ResponseEntity<TimeResponse> getDeviceTime(
            @RequestParam String ipAddress,
            @RequestParam int portNumber) {

        String deviceTime = hardwareQueryHandler.handle(new GetTimeQuery(ipAddress, portNumber));
        var response = TimeResponse.builder()
                .time(deviceTime)
                .message("Device time retrieved successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/information")
    public ResponseEntity<InfoResponse> getDeviceInformation(
            @RequestParam String ipAddress,
            @RequestParam int portNumber) {

        GetInformationQuery query = new GetInformationQuery(ipAddress, portNumber);
        InfoResponse infoResponse = hardwareQueryHandler.handle(query);
        return new ResponseEntity<>(infoResponse, HttpStatus.OK);
    }


}
