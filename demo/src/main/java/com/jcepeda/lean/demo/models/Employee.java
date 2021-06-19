package com.jcepeda.lean.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @JsonIgnoreProperties("employee")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"employee", "employees"})
    private Position position;

    @Column(name = "salary", nullable = false)
    private Long salary;

    public Employee(Person person, Position position, Long salary) {
        this.person = person;
        this.position = position;
        this.salary = salary;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
