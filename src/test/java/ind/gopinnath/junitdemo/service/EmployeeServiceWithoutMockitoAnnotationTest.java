package ind.gopinnath.junitdemo.service;

import ind.gopinnath.junitdemo.entity.EmployeeEntity;
import ind.gopinnath.junitdemo.model.Employee;
import ind.gopinnath.junitdemo.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeServiceWithoutMockitoAnnotationTest {
    private EmployeeService service;
    private EmployeeRepository repository;

    @BeforeEach
    public void setup() {
        repository = mock(EmployeeRepository.class);
        EmployeeMapper mapper = new EmployeeMapper();
        service = new EmployeeService(repository, mapper);
    }

    @AfterEach
    public void tearDown() {
        clearInvocations(repository);
    }

    @Test
    public void testGetEmployeeByIdHappyPath() {
        // Given
        long id = 10L;
        when(repository.findById(anyLong())).thenReturn(Optional.of(buildMockEntity(id)));

        // When
        Employee actualEmployee = service.getEmployeeById(id);

        // Then
        assertEquals("Alice", actualEmployee.getName());
        assertEquals("HR", actualEmployee.getDepartment());
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        // Given
        long id = 10L;
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // When and Then
        assertThrows(RuntimeException.class, () ->  service.getEmployeeById(id));
    }

    @Test
    public void testCreateEmployeeHappyPath() {
        // Given
        long id = 10L;
        Employee employee = buildMockEmployee();
        when(repository.save(any(EmployeeEntity.class))).thenReturn(buildMockEntity(id));

        // When
        long createdId = service.createEmployee(employee);

        // Then
        assertEquals(createdId, id);
        verify(repository, times(1)).save(any(EmployeeEntity.class));
    }

    @Test
    public void testCreateEmployeeFailed() {
        // Given
        Employee employee = buildMockEmployee();
        when(repository.save(any(EmployeeEntity.class))).thenThrow(new RuntimeException("Unable to save"));

        // When and Then
        assertThrows(RuntimeException.class, () -> service.createEmployee(employee));
    }

    private Employee buildMockEmployee() {
        Employee employee = new Employee();
        employee.setName("Alice");
        employee.setDepartment("HR");
        return employee;
    }

    private EmployeeEntity buildMockEntity(long id) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setEmployeeId(id);
        entity.setName("Alice");
        entity.setDepartment("HR");
        return entity;
    }
}
