package com.cqrs.cqrs;

import com.cqrs.cqrs.example.employepoc.command.commands.CreateEmployeeCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.DeleteEmployeeCommand;
import com.cqrs.cqrs.example.employepoc.command.commands.UpdateEmployeeCommand;
import com.cqrs.cqrs.example.employepoc.command.handlers.EmployeeCommandHandlers;
import com.cqrs.cqrs.example.employepoc.query.handlers.EmployeeQueryHandler;
import com.cqrs.cqrs.example.employepoc.query.queries.FindAllEmployeesQuery;
import com.cqrs.cqrs.example.employepoc.query.queries.FindEmployeeByIdQuery;
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
@ComponentScan({"com.cqrs.cqrs.example.employepoc.command.handlers"})
@ComponentScan({"com.cqrs.cqrs.example.employepoc.command.events"})
@ComponentScan({"com.cqrs.cqrs.example.employepoc.query.rest.repository"})
@ComponentScan({"com.cqrs.cqrs.example.employepoc.query.handlers"})
@ComponentScan({"com.cqrs.cqrs.example.employepoc.command.infrastructure"})
@ComponentScan({"com.hydatis.cqrsref.infrastructure"})
@ComponentScan({"com.hydatis.cqrsref.handlers"})
public class CqrsApplication {
    private final EmployeeQueryHandler employeeQueryHandler;
    private final EmployeeCommandHandlers employeeCommandHandlers;
    private final CommandDispatcher commandDispatcher;
    private final QueryDispatcher queryDispatcher;

    @Autowired
    public CqrsApplication(EmployeeQueryHandler employeeQueryHandler, EmployeeCommandHandlers employeeCommandHandlers, CommandDispatcher commandDispatcher, QueryDispatcher queryDispatcher) {
        this.employeeQueryHandler = employeeQueryHandler;
        this.employeeCommandHandlers = employeeCommandHandlers;
        this.commandDispatcher = commandDispatcher;
        this.queryDispatcher = queryDispatcher;
    }

    @PostConstruct
    public void registerHandlers() {
        commandDispatcher.registerHandler(CreateEmployeeCommand.class, employeeCommandHandlers::handle);
        commandDispatcher.registerHandler(UpdateEmployeeCommand.class, employeeCommandHandlers::handle);
        commandDispatcher.registerHandler(DeleteEmployeeCommand.class, employeeCommandHandlers::handle);


        queryDispatcher.registerHandler(FindAllEmployeesQuery.class, employeeQueryHandler::handle);
        queryDispatcher.registerHandler(FindEmployeeByIdQuery.class, employeeQueryHandler::handle);

    }


    public static void main(String[] args) {

        SpringApplication.run(CqrsApplication.class, args);
    }

}

