package com.cqrs.cqrs.example.employepoc.command.commands;

import com.cqrs.cqrs.example.employepoc.command.rest.dto.Employee;
import com.hydatis.cqrsref.commands.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmployeeCommand extends BaseCommand {
    private String id;
    private Employee employee;
}
