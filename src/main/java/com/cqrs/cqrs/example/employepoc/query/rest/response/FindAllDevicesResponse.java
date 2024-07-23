package com.cqrs.cqrs.example.employepoc.query.rest.response;

import com.cqrs.cqrs.example.employepoc.query.rest.dto.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FindAllDevicesResponse extends BaseResponse{

    List<Device> devices;
    public FindAllDevicesResponse(String message) {
        super(message);
    }
}
