package com.jcepeda.lean.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="positions")
public class Position {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    private Employee employee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "position")
    @JsonIgnoreProperties("position")
    @OrderBy("salary DESC")
    private List<Employee> employees;

    public Position(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Position() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
