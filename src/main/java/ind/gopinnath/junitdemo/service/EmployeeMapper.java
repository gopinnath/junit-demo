package ind.gopinnath.junitdemo.service;

import ind.gopinnath.junitdemo.entity.EmployeeEntity;
import ind.gopinnath.junitdemo.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee buildEmployeeFromEntity(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeEntity.getEmployeeId());
        employee.setName(employeeEntity.getName());
        employee.setDepartment(employeeEntity.getDepartment());
        return employee;
    }

    public EmployeeEntity buildEntityForSave(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employee.getName());
        employeeEntity.setDepartment(employee.getDepartment());
        return employeeEntity;
    }
}
