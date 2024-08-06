package com.cqrs.cqrs.example.employepoc.command.events;

import com.cqrs.cqrs.example.employepoc.command.rest.dto.Hardware;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hydatis.cqrsref.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonSerialize
public class HardwareDeletedEvent extends BaseEvent {
    private String identifier;
    private Hardware hardware ;
}
