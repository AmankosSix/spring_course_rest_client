package com.amankos.rest;

import com.amankos.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://localhost:8080/api/employees";

    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<Employee>>() {});
        return responseEntity.getBody();
    }

    public Employee getEmployee(int id) {
        ResponseEntity<Employee> responseEntity = restTemplate.exchange(URL + "/" + id, HttpMethod.GET, HttpEntity.EMPTY, Employee.class);
        return responseEntity.getBody();
    }

    public Employee saveEmployee(Employee employee) {
        // if id == 0 then create new employee, else update existing employee
        if (employee.getId() == 0) {
            ResponseEntity<Employee> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, new HttpEntity<>(employee), Employee.class);
            return responseEntity.getBody();
        } else {
            ResponseEntity<Employee> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, new HttpEntity<>(employee), Employee.class);
            return responseEntity.getBody();
        }
    }

    public String deleteEmployee(int id) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
        return responseEntity.getBody();
    }
}
