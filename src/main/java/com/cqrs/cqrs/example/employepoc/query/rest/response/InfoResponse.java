package com.cqrs.cqrs.example.employepoc.query.rest.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class InfoResponse extends BaseResponse {
    private String info;
    private String message;
}
