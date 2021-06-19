package com.jcepeda.lean.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="cellphone", nullable = false)
    private String cellphone;

    @Column(name="city_name", nullable = false)
    private String cityName;

    @OneToOne(mappedBy = "person")
    @JsonIgnore
    private Employee employee;


    public Person() {
    }

    public Person(String name, String lastName, String address, String cellphone, String cityName) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.cellphone = cellphone;
        this.cityName = cityName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
