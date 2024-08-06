package com.cqrs.cqrs;

import com.cqrs.cqrs.example.employepoc.command.commands.*;
import com.cqrs.cqrs.example.employepoc.command.handlers.*;
import com.cqrs.cqrs.example.employepoc.query.handlers.*;
import com.cqrs.cqrs.example.employepoc.query.queries.*;
import com.hydatis.cqrsref.infrastructure.CommandDispatcher;
import com.hydatis.cqrsref.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableKafka
@EnableScheduling
@ComponentScan({"com.cqrs.cqrs.example.employepoc.command.handlers", "com.cqrs.cqrs.example.employepoc.command.events",
        "com.cqrs.cqrs.example.employepoc.query.rest.repository", "com.cqrs.cqrs.example.employepoc.query.handlers",
        "com.cqrs.cqrs.example.employepoc.command.infrastructure", "com.hydatis.cqrsref.infrastructure", "com.hydatis.cqrsref.handlers"})
public class CqrsApplication {

    private final EmployeeQueryHandler employeeQueryHandler;
    private final EmployeeCommandHandlers employeeCommandHandlers;
    private final HardwareQueryHandler hardwareQueryHandler;
    private final HardwareCommandHandlers hardwareCommandHandlers;
    private final CommandDispatcher commandDispatcher;
    private final QueryDispatcher queryDispatcher;

    @Autowired
    public CqrsApplication(EmployeeQueryHandler employeeQueryHandler, EmployeeCommandHandlers employeeCommandHandlers,
                           HardwareQueryHandler hardwareQueryHandler, HardwareCommandHandlers hardwareCommandHandlers,
                           CommandDispatcher commandDispatcher, QueryDispatcher queryDispatcher) {
        this.employeeQueryHandler = employeeQueryHandler;
        this.employeeCommandHandlers = employeeCommandHandlers;
        this.hardwareQueryHandler = hardwareQueryHandler;
        this.hardwareCommandHandlers = hardwareCommandHandlers;
        this.commandDispatcher = commandDispatcher;
        this.queryDispatcher = queryDispatcher;
    }

    @PostConstruct
    public void registerHandlers() {
        commandDispatcher.registerHandler(CreateEmployeeCommand.class, employeeCommandHandlers::handle);
        commandDispatcher.registerHandler(UpdateEmployeeCommand.class, employeeCommandHandlers::handle);
        commandDispatcher.registerHandler(DeleteEmployeeCommand.class, employeeCommandHandlers::handle);

        commandDispatcher.registerHandler(CreateHardwareCommand.class, hardwareCommandHandlers::handle);
        commandDispatcher.registerHandler(UpdateHardwareCommand.class, hardwareCommandHandlers::handle);
        commandDispatcher.registerHandler(DeleteHardwareCommand.class, hardwareCommandHandlers::handle);

        queryDispatcher.registerHandler(FindAllEmployeesQuery.class, employeeQueryHandler::handle);
        queryDispatcher.registerHandler(FindEmployeeByIdQuery.class, employeeQueryHandler::handle);

        queryDispatcher.registerHandler(FindAllHardwareQuery.class, hardwareQueryHandler::handle);
        queryDispatcher.registerHandler(FindHardwareByIdQuery.class, hardwareQueryHandler::handle);
    }

    public static void main(String[] args) {
        SpringApplication.run(CqrsApplication.class, args);
    }
}
