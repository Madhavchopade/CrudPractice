package com.crudoperation.CrudPractice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crudoperation.CrudPractice.model.Employee;

public interface Repository extends JpaRepository<Employee, Integer> {

	public List<Employee> findByName(String name);

	public List<Employee> findByEmail(String eamil);

	public List<Employee> findByAddress(String address);

	@Query(value = "select * from employee", nativeQuery = true)
	public List<Employee> getemployee();

	@Query(value = "select * from employee where name like ? ", nativeQuery = true)
	public List<Employee> getempbynamelike(String name);

	@Query(value = "SELECT * FROM employee WHERE name LIKE %:name%", nativeQuery = true)
	public List<Employee> findByStartsWith(String name);

}
