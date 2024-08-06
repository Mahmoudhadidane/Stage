package com.cqrs.cqrs.example.employepoc.query.rest.response;


import com.cqrs.cqrs.example.employepoc.query.rest.dto.Employee;
import com.cqrs.cqrs.example.employepoc.query.rest.dto.Hardware;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FindHardwareByIdResponse extends BaseResponse{
    Hardware hardware ;
    public FindHardwareByIdResponse(String message, Hardware hardware) {
        super(message);
        this.hardware=hardware;
    }
}
