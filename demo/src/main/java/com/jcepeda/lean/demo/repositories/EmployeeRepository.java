package com.jcepeda.lean.demo.repositories;

import com.jcepeda.lean.demo.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByPerson_NameOrPosition_Name( String name, String position );
}
