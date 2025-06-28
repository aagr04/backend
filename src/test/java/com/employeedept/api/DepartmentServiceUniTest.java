package com.employeedept.api;

import com.employeedept.api.dto.DepartmentDto;
import com.employeedept.api.entity.Department;
import com.employeedept.api.exception.GeneralException;
import com.employeedept.api.repository.DepartmentRepository;
import com.employeedept.api.repository.EmployeeRepository;
import com.employeedept.api.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para DepartmentServiceImpl.
 */
public class DepartmentServiceUniTest {

    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        departmentRepository = mock(DepartmentRepository.class);
        employeeRepository = mock(EmployeeRepository.class);
        departmentService = new DepartmentServiceImpl(employeeRepository, departmentRepository);
    }

    // ---------------- GUARDAR (POST) ----------------

    /**
     * Caso de éxito: se crea correctamente el departamento.
     */
    @Test
    void save_shouldCreateDepartment_whenDataIsValid() {
        DepartmentDto dto = new DepartmentDto("TI", "A");
        Department saved = new Department("TI", "A");
        when(departmentRepository.save(any(Department.class))).thenReturn(saved);

        Department result = departmentService.save(dto);

        assertNotNull(result);
        assertEquals("Educacion y Deporte", result.getDepartmentName());
        assertEquals("A", result.getDepartmentStatus());
        verify(departmentRepository).save(any(Department.class));
    }

    /**
     * Caso de error: nombre nulo.
     */
    @Test
    void save_shouldThrowException_whenNameIsNull() {
        DepartmentDto dto = new DepartmentDto(null, "A");

        GeneralException exception = assertThrows(GeneralException.class, () -> departmentService.save(dto));

        assertEquals("Department name is required", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(departmentRepository, never()).save(any());
    }

    /**
     * Caso de error: estado inválido.
     */
    @Test
    void save_shouldThrowException_whenStatusIsInvalid() {
        DepartmentDto dto = new DepartmentDto("TI", "X");

        GeneralException exception = assertThrows(GeneralException.class, () -> departmentService.save(dto));

        assertEquals("Department status must be 'A' (active) or 'I' (inactive)", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(departmentRepository, never()).save(any());
    }

    // ---------------- ELIMINAR (DELETE) ----------------

    /**
     * Caso de éxito: el departamento existe, no hay empleados activos y se inactiva.
     */
    @Test
    void delete_shouldSetStatusInactive_whenDepartmentExistsAndNoActiveEmployees() {
        int depId = 1;
        Department dept = new Department("Educecion y Deporte", "A");
        // Si tu Department NO tiene setDepartmentId(), comenta o elimina la siguiente línea.
        // dept.setDepartmentId(depId);

        when(employeeRepository.existsByDepartmentIdAndEmployeeStatus(depId, "A")).thenReturn(false);
        when(departmentRepository.findById(depId)).thenReturn(Optional.of(dept));
        when(departmentRepository.save(any(Department.class))).thenReturn(dept);

        departmentService.delete(depId);

        assertEquals("I", dept.getDepartmentStatus());
        verify(departmentRepository).save(dept);
    }

    /**
     * Caso de error: el departamento tiene empleados activos.
     */
    @Test
    void delete_shouldThrowException_whenHasActiveEmployees() {
        int depId = 1;
        when(employeeRepository.existsByDepartmentIdAndEmployeeStatus(depId, "A")).thenReturn(true);

        GeneralException exception = assertThrows(GeneralException.class, () -> departmentService.delete(depId));

        assertEquals("Cannot deactivate department with active employees", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
        verify(departmentRepository, never()).findById(anyInt());
        verify(departmentRepository, never()).save(any());
    }

    /**
     * Caso de error: el departamento no existe.
     */
    @Test
    void delete_shouldThrowException_whenDepartmentNotFound() {
        int depId = 99;
        when(employeeRepository.existsByDepartmentIdAndEmployeeStatus(depId, "A")).thenReturn(false);
        when(departmentRepository.findById(depId)).thenReturn(Optional.empty());

        GeneralException exception = assertThrows(GeneralException.class, () -> departmentService.delete(depId));

        assertEquals("Department with ID 99 not found", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(departmentRepository, never()).save(any());
    }

    /**
     * Caso de error: el departamento ya está inactivo.
     */
    @Test
    void delete_shouldThrowException_whenDepartmentAlreadyInactive() {
        int depId = 2;
        Department dept = new Department("Educacion y Deporte", "I");
        // Si tu Department NO tiene setDepartmentId(), comenta o elimina la siguiente línea.
        // dept.setDepartmentId(depId);

        when(employeeRepository.existsByDepartmentIdAndEmployeeStatus(depId, "A")).thenReturn(false);
        when(departmentRepository.findById(depId)).thenReturn(Optional.of(dept));

        GeneralException exception = assertThrows(GeneralException.class, () -> departmentService.delete(depId));

        assertEquals("Department is already inactive", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(departmentRepository, never()).save(any());
    }
}
