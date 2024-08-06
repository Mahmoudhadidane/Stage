package com.cqrs.cqrs.example.employepoc.command.rest.response;

import com.cqrs.cqrs.example.employepoc.command.rest.dto.Hardware;
import lombok.Data;


@Data

public class HardwareResponse {
    private String message;
    private Hardware hardware ;
    public HardwareResponse (String message, Hardware hardware) {
        this.message = message;
        this.hardware = hardware;
    }
    public HardwareResponse(String message) {

        this.message = message;
    }
}
