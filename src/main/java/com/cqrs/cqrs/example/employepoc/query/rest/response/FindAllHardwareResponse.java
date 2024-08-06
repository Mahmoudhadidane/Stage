package com.cqrs.cqrs.example.employepoc.query.rest.response;


import com.cqrs.cqrs.example.employepoc.query.rest.dto.Employee;
import com.cqrs.cqrs.example.employepoc.query.rest.dto.Hardware;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FindAllHardwareResponse extends BaseResponse {
    List<Hardware> hardware;
    public FindAllHardwareResponse(String message) {
        super(message);
    }
}
