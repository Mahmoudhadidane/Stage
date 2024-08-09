package com.cqrs.cqrs.example.employepoc.query.rest.repository;

import com.cqrs.cqrs.example.employepoc.query.rest.dto.Hardware;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HardwareQueryRepository extends MongoRepository<Hardware, String> {
    @Query("{ 'ipAddress': ?0 }")
    List<Hardware> findByIpAddress(String ipAddress);

}
