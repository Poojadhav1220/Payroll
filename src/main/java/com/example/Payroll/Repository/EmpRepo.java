package com.example.Payroll.Repository;

import com.example.Payroll.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface EmpRepo extends JpaRepository<Employee,Long> {

    List<Employee> findByName(String name);
    List<Employee> findByIsAdmin(boolean isAdmin);
    Employee getByEmail(String email);

    List<Employee> findBySalary(Double salary);
    List<Employee> findByNameStartsWith(String name);
    List<Employee> findByNameEndsWith(String name);
    List<Employee> findBySalaryGreaterThan(Double sal);
}
