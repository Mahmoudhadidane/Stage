package com.cqrs.cqrs.example.employepoc.command.handlers;

import com.cqrs.cqrs.example.employepoc.command.commands.CreateEmployeeCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.DeleteEmployeeCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.UpdateEmployeeCommand;


public interface EmployeeCommandHandlersInterface {
    void handle(CreateEmployeeCommand createEmployeeCommand);
    void handle(UpdateEmployeeCommand updateEmployeeCommand);
    void handle(DeleteEmployeeCommand deleteEmployeeCommand);

}
