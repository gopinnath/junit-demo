package ind.gopinnath.junitdemo.controller;

import ind.gopinnath.junitdemo.model.Employee;
import ind.gopinnath.junitdemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeResource {

    private final EmployeeService service;

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        return service.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public ResponseEntity createEmployee(@RequestBody Employee employee) {
        return ResponseEntity
                .created(URI.create("http://localhost:8080/api/employees/" + service.createEmployee(employee)))
                .build();
    }
}
