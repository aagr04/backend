package com.employeedept.api;

import com.employeedept.api.dto.EmployeeDto;
import com.employeedept.api.entity.Department;
import com.employeedept.api.entity.Employee;
import com.employeedept.api.exception.GeneralException;
import com.employeedept.api.repository.DepartmentRepository;
import com.employeedept.api.repository.EmployeeRepository;
import com.employeedept.api.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceUniTest {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        departmentRepository = mock(DepartmentRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository, departmentRepository);
    }

    // ---------- GUARDAR (save) ----------

    /**
     * Caso de éxito: Se crea un empleado correctamente.
     */
    @Test
    void save_shouldCreateEmployee_whenDataIsValid() {
        // Arrange
        int depId = 1;
        EmployeeDto dto = new EmployeeDto(
                depId, "John", "Doe", 25, new BigDecimal("1000"),
                LocalDate.of(2024, 1, 1), null, "A"
        );
        Department dep = new Department("TI", "A");
        when(departmentRepository.findById(depId)).thenReturn(Optional.of(dep));
        when(departmentRepository.existsById(depId)).thenReturn(true);

        Employee emp = new Employee(
                depId, "John", "Doe", 25, new BigDecimal("1000"),
                LocalDate.of(2024, 1, 1), null, "A"
        );
        when(employeeRepository.save(any(Employee.class))).thenReturn(emp);

        // Act
        Employee result = employeeService.save(dto);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getEmployeeName());
        assertEquals("Doe", result.getEmployeeLastName());
        assertEquals(25, result.getAge());
        assertEquals("A", result.getEmployeeStatus());
        verify(employeeRepository).save(any(Employee.class));
    }

    /**
     * Caso de error: el nombre es nulo.
     */
    @Test
    void save_shouldThrowException_whenNameIsNull() {
        // Arrange
        int depId = 1;
        EmployeeDto dto = new EmployeeDto(
                depId, null, "Doe", 25, new BigDecimal("1000"),
                LocalDate.of(2024, 1, 1), null, "A"
        );

        // Act & Assert
        GeneralException ex = assertThrows(GeneralException.class, () -> employeeService.save(dto));
        assertEquals("Employee name is required", ex.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        verify(employeeRepository, never()).save(any());
    }

    // ---------- ELIMINAR (delete) ----------

    /**
     * Caso de éxito: Se inactiva el empleado correctamente.
     */
    @Test
    void delete_shouldSetStatusInactive_whenEmployeeExistsAndActive() {
        // Arrange
        int id = 1;
        Employee emp = new Employee(
                1, "Jane", "Smith", 30, new BigDecimal("1200"),
                LocalDate.of(2023, 5, 1), null, "A"
        );
        when(employeeRepository.findById(id)).thenReturn(Optional.of(emp));
        when(employeeRepository.save(any(Employee.class))).thenReturn(emp);

        // Act
        employeeService.delete(id);

        // Assert
        assertEquals("I", emp.getEmployeeStatus());
        verify(employeeRepository).save(emp);
    }

    /**
     * Caso de error: El empleado no existe.
     */
    @Test
    void delete_shouldThrowException_whenEmployeeNotFound() {
        // Arrange
        int id = 99;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        GeneralException ex = assertThrows(GeneralException.class, () -> employeeService.delete(id));
        assertEquals("Employee with ID 99 not found", ex.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        verify(employeeRepository, never()).save(any());
    }
}

