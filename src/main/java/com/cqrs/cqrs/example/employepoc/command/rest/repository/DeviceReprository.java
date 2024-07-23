package com.cqrs.cqrs.example.employepoc.command.rest.repository;

import com.cqrs.cqrs.example.employepoc.command.rest.dto.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceReprository extends JpaRepository<Device, String> {
}
