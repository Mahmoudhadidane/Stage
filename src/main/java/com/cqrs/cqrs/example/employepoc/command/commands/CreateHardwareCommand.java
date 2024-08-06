package com.cqrs.cqrs.example.employepoc.command.commands;


import com.cqrs.cqrs.example.employepoc.command.rest.dto.Hardware;
import com.hydatis.cqrsref.commands.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateHardwareCommand extends BaseCommand{
    private String id;
    private Hardware hardware ;
}
