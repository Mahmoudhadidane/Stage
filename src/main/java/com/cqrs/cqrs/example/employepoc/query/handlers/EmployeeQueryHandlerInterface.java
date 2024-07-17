package com.cqrs.cqrs.example.employepoc.query.handlers;

import com.cqrs.cqrs.example.employepoc.query.queries.FindAllEmployeesQuery;
import com.cqrs.cqrs.example.employepoc.query.queries.FindEmployeeByIdQuery;
import com.cqrs.cqrs.example.employepoc.query.rest.dto.Employee;

import java.util.List;

public interface EmployeeQueryHandlerInterface {
    List<Employee> handle(FindAllEmployeesQuery query);
    Employee handle(FindEmployeeByIdQuery query);
}
