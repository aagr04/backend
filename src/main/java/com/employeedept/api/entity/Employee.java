package com.employeedept.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("department_id")
    private int departmentId;

    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("employee_lastname")
    private String employeeLastName;

    @JsonProperty("age")
    private int age;

    @Column(nullable = true)
    @JsonProperty("salary")
    private BigDecimal salary;

    @Column(nullable = true)
    @JsonProperty("init_date")
    private LocalDate initDate;

    @Column(nullable = true)
    @JsonProperty("end_date")
    private LocalDate endDate;

    @JsonProperty("employee_status")
    private String employeeStatus;

    public Employee(){

    }

    public Employee(int departmentId,
                    String employeeName, String employeeLastName,
                    int age, BigDecimal salary, LocalDate initDate, LocalDate endDate,
                    String employeeStatus) {
        this.departmentId = departmentId;
        this.employeeName = employeeName;
        this.employeeLastName = employeeLastName;
        this.age = age;
        this.salary = salary;
        this.initDate = initDate;
        this.endDate = endDate;
        this.employeeStatus = employeeStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getInitDate() {
        return initDate;
    }

    public void setInitDate(LocalDate initDate) {
        this.initDate = initDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

}
