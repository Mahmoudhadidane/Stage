package com.cqrs.cqrs.example.employepoc.query.rest.controllers;

import com.cqrs.cqrs.example.employepoc.query.rest.dto.Hardware;
import com.cqrs.cqrs.example.employepoc.query.rest.response.FindAllHardwareResponse;
import com.cqrs.cqrs.example.employepoc.query.queries.FindAllHardwareQuery;
import com.hydatis.cqrsref.infrastructure.IQueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hardware")
public class HardwareQueryController {

    @Autowired
    private IQueryDispatcher queryDispatcher;

    @GetMapping
    public ResponseEntity<FindAllHardwareResponse> getAllHardware() {
        List<Hardware> hardware = queryDispatcher.send(new FindAllHardwareQuery());
        var response = FindAllHardwareResponse.builder()
                .hardware(hardware)
                .message("Hardware retrieved successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
