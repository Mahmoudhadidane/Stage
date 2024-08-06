package com.cqrs.cqrs.example.employepoc.command.rest.dto;

import java.io.Serializable;

public enum Direction implements Serializable {
    IN, OUT, BOTH, NONE
}