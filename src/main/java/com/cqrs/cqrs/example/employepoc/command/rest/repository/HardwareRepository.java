package com.cqrs.cqrs.example.employepoc.command.rest.repository;

import com.cqrs.cqrs.example.employepoc.command.rest.dto.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, String> {
}
