package com.cqrs.cqrs.example.employepoc.query.queries;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class GetInformationQuery {
    private String ipAddress ;
    private int portNumber ;
}
