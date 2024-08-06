//package com.cqrs.cqrs.example.employepoc.query.handlers;
//
//import com.cqrs.cqrs.example.employepoc.query.queries.FindDevicesByIdQuery ;
//import com.cqrs.cqrs.example.employepoc.query.queries.FindAllDevicesQuery ;
//import com.cqrs.cqrs.example.employepoc.query.rest.dto.Device;
//import com.cqrs.cqrs.example.employepoc.query.rest.repository.DeviceQueryRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Slf4j
//@EnableMongoRepositories(basePackages = "com.cqrs.cqrs.example.employepoc.query.rest.repository")
//public class DeviceQueryHandler implements DeviceQueryHandlerInterface {
//    private final DeviceQueryRepository deviceQueryRepository;
//    @Autowired
//    public DeviceQueryHandler(DeviceQueryRepository deviceQueryRepository) {
//        this.deviceQueryRepository = deviceQueryRepository;
//    }
//
//    @Override
//    public List<Device> handle(FindAllDevicesQuery query) {
//
//        return deviceQueryRepository.findAll();
//    }
//
//    @Override
//    public Device handle(FindDevicesByIdQuery query) {
//        return deviceQueryRepository.findById((query.getId())).orElseThrow(() -> new RuntimeException("Device not found"));
//    }
//
//
//
//}
