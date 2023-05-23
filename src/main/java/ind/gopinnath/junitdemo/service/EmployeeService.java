package ind.gopinnath.junitdemo.service;

import ind.gopinnath.junitdemo.entity.EmployeeEntity;
import ind.gopinnath.junitdemo.model.Employee;
import ind.gopinnath.junitdemo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    public Employee getEmployeeById(long id) {
        Optional<EmployeeEntity> entity = repository.findById(id);
        return entity
                .map(mapper::buildEmployeeFromEntity)
                .orElseThrow(() -> new RuntimeException("No Employee Found"));
    }

    public long createEmployee(Employee employee) {
        log.info("Employee About to save {}", employee);
        EmployeeEntity entity = mapper.buildEntityForSave(employee);
        log.info("Employee About to save {}", entity);
        EmployeeEntity resultEntity = repository.save(entity);
        return resultEntity.getEmployeeId();
    }
}
