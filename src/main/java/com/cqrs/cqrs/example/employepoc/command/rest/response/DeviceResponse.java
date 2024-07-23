package com.cqrs.cqrs.example.employepoc.command.rest.response;


import com.cqrs.cqrs.example.employepoc.command.rest.dto.Device;

import lombok.Data;

@Data
public class DeviceResponse {
    private String message;
    private Device device;

    public DeviceResponse(String message, Device device) {
        this.message = message;
        this.device = device;
    }

    public DeviceResponse(String message) {
        this.message = message;
    }
}
