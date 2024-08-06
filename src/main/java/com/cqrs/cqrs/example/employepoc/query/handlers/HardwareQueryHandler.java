package com.cqrs.cqrs.example.employepoc.query.handlers;

import com.cqrs.cqrs.example.employepoc.query.queries.FindHardwareByIdQuery;
import com.cqrs.cqrs.example.employepoc.query.queries.FindAllHardwareQuery;
import com.cqrs.cqrs.example.employepoc.query.rest.dto.Hardware;
import com.cqrs.cqrs.example.employepoc.query.rest.repository.HardwareQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

