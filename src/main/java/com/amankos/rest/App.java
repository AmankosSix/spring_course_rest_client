package com.amankos.rest;

import com.amankos.rest.config.MyConfig;
import com.amankos.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        // Get all employees
        List<Employee> employeeList = communication.getAllEmployees();
        System.out.println(employeeList);

        // Get employee by id
//        Employee employee = communication.getEmployee(1);
//        System.out.println(employee);

        // Add new employee
        Employee employee1 = new Employee("Elena", "Sokolova", "IT", 1000);
        Employee elena = communication.saveEmployee(employee1);

        // Get all employees
        employeeList = communication.getAllEmployees();
        System.out.println(employeeList);

        // Update employee
        elena.setSalary(2000);
        communication.saveEmployee(elena);

        // Get all employees
        employeeList = communication.getAllEmployees();
        System.out.println(employeeList);

        // Delete employee
        communication.deleteEmployee(elena.getId());

        // Get all employees
        employeeList = communication.getAllEmployees();
        System.out.println(employeeList);

        context.close();
    }
}
