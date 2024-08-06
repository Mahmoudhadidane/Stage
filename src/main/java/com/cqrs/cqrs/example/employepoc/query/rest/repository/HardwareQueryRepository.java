package com.cqrs.cqrs.example.employepoc.query.rest.repository;

import com.cqrs.cqrs.example.employepoc.query.rest.dto.Hardware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;


@Repository
public interface HardwareQueryRepository extends MongoRepository<Hardware, String> {
}
