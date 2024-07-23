package com.cqrs.cqrs.example.employepoc.query.handlers;

import com.cqrs.cqrs.example.employepoc.query.queries.FindAllDevicesQuery;
import com.cqrs.cqrs.example.employepoc.query.queries.FindDevicesByIdQuery;
import com.cqrs.cqrs.example.employepoc.query.rest.dto.Device;

import java.util.List;

public interface DeviceQueryHandlerInterface {
    List <Device> handle(FindAllDevicesQuery query);
    Device handle(FindDevicesByIdQuery query);

}
