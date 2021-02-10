package com.khoza.springbootpostgresqlhibernatecrud.repository;

import com.khoza.springbootpostgresqlhibernatecrud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
