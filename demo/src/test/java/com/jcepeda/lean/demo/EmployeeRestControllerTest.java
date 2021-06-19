package com.jcepeda.lean.demo;

import com.jcepeda.lean.demo.models.Employee;
import com.jcepeda.lean.demo.models.Person;
import com.jcepeda.lean.demo.models.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeRestControllerTest {

    @Autowired
     private TestRestTemplate restTemplate;

     @LocalServerPort
     private int port;

     private String getRootUrl() {
         return "http://localhost:" + port;
     }

     @Test
     public void contextLoads() {
     }

     @Test
     public void testGetAllEmployees() {
     HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employees",
        HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/1", Employee.class);
        System.out.println(employee.getSalary());
        assertNotNull(employee);
    }

    @Test
    public void testUpdateEmployee() {
        int id = 1;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);

        Person person = new Person();
        person.setName("Juan");
        person.setLastName("Ruiz");
        person.setAddress("Cll 1");
        person.setCellphone("23489404");
        person.setCityName("Cali");

        Position position = new Position();
        position.setName("QA");

        employee.setPerson(person);
        employee.setPosition(position);
        employee.setSalary(2000L);

        restTemplate.put(getRootUrl() + "/employees/" + id, employee);
        Employee updatedEmployee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
        assertNotNull(updatedEmployee);
    }

    @Test
    public void testDeleteEmployee() {
         int id = 2;
         Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
         assertNotNull(employee);
         restTemplate.delete(getRootUrl() + "/employees/" + id);
         try {
              employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }
}
