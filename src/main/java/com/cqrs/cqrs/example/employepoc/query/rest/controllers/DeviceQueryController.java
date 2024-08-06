//package com.cqrs.cqrs.example.employepoc.query.rest.controllers;
//
//import com.cqrs.cqrs.example.employepoc.query.queries.FindAllDevicesQuery;
//
//import com.cqrs.cqrs.example.employepoc.query.rest.dto.Device;
//import com.cqrs.cqrs.example.employepoc.query.rest.response.FindAllDevicesResponse;
//import com.hydatis.cqrsref.infrastructure.IQueryDispatcher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/api/devices")
//public class DeviceQueryController {
//    @Autowired
//    private IQueryDispatcher queryDispatcher;
//
//    @GetMapping
//    public ResponseEntity<FindAllDevicesResponse> getAllDevices() {
//        List<Device> devices = queryDispatcher.send(new FindAllDevicesQuery());
//        var response = FindAllDevicesResponse.builder()
//                .devices(devices)
//                .message("Devices retrieved successfully")
//                .build();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//}