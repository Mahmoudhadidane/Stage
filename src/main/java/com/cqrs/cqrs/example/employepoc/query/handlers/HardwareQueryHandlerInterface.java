package com.cqrs.cqrs.example.employepoc.query.handlers;

import com.cqrs.cqrs.example.employepoc.query.queries.FindAllHardwareQuery;
import com.cqrs.cqrs.example.employepoc.query.queries.FindHardwareByIdQuery;
import com.cqrs.cqrs.example.employepoc.query.rest.dto.Hardware;

import java.util.List;

public interface HardwareQueryHandlerInterface {
    List<Hardware> handle(FindAllHardwareQuery query);
    Hardware handle(FindHardwareByIdQuery query);


}
