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
    private final DeviceQueryHandler deviceQueryHandler;
    private final DeviceCommandHandlers deviceCommandHandlers;
    private final CommandDispatcher commandDispatcher;
    private final QueryDispatcher queryDispatcher;

    @Autowired
    public CqrsApplication(EmployeeQueryHandler employeeQueryHandler, EmployeeCommandHandlers employeeCommandHandlers,
                           DeviceQueryHandler deviceQueryHandler, DeviceCommandHandlers deviceCommandHandlers,
                           CommandDispatcher commandDispatcher, QueryDispatcher queryDispatcher) {
        this.employeeQueryHandler = employeeQueryHandler;
        this.employeeCommandHandlers = employeeCommandHandlers;
        this.deviceQueryHandler = deviceQueryHandler;
        this.deviceCommandHandlers = deviceCommandHandlers;
        this.commandDispatcher = commandDispatcher;
        this.queryDispatcher = queryDispatcher;
    }

    @PostConstruct
    public void registerHandlers() {
        commandDispatcher.registerHandler(CreateEmployeeCommand.class, employeeCommandHandlers::handle);
        commandDispatcher.registerHandler(UpdateEmployeeCommand.class, employeeCommandHandlers::handle);
        commandDispatcher.registerHandler(DeleteEmployeeCommand.class, employeeCommandHandlers::handle);
        commandDispatcher.registerHandler(CreateDeviceCommand.class, deviceCommandHandlers::handle);
        commandDispatcher.registerHandler(UpdateDeviceCommand.class, deviceCommandHandlers::handle);
        commandDispatcher.registerHandler(DeleteDeviceCommand.class, deviceCommandHandlers::handle);

        queryDispatcher.registerHandler(FindAllEmployeesQuery.class, employeeQueryHandler::handle);
        queryDispatcher.registerHandler(FindEmployeeByIdQuery.class, employeeQueryHandler::handle);
        queryDispatcher.registerHandler(FindAllDevicesQuery.class, deviceQueryHandler::handle);
        queryDispatcher.registerHandler(FindDevicesByIdQuery.class, deviceQueryHandler::handle);
    }

    public static void main(String[] args) {
        SpringApplication.run(CqrsApplication.class, args);
    }
}
