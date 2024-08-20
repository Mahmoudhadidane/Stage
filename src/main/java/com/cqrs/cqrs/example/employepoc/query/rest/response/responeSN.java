package com.cqrs.cqrs.example.employepoc.query.rest.response;


import com.hydatis.cqrsref.Response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class responeSN extends BaseResponse {
    private String message;
    private String SerialNumber;
}
