package com.crudoperation.CrudPractice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crudoperation.CrudPractice.Globalexceptions.CustomException;
import com.crudoperation.CrudPractice.Globalexceptions.EmailValidation;
import com.crudoperation.CrudPractice.model.Employee;
import com.crudoperation.CrudPractice.model.Student;
import com.crudoperation.CrudPractice.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@PostMapping("saveemployee")
	public Employee saveemployee(@Valid @RequestBody Employee employee) throws EmailValidation{
		
		EmployeeService.validateEmail(employee.getEmail());
		Employee employee1 = service.saveemployee(employee);
		
		return employee1;
	}

	// paging
	@GetMapping(value = "getemployee", produces = { "Application/xml", "Application/json" })
	public List<Employee> getemployee(@RequestParam(value = "pageno", defaultValue = "0", required = false) int pageno,
			@RequestParam(value = "pagesize", defaultValue = "1", required = false) int pagesize,
			@RequestParam(value = "sortby", defaultValue = "name", required = false) String sortby,
			@RequestParam(value = "sortorder", defaultValue = "asc", required = false) String sortorder

	) {
		return service.getemployee(pagesize, pagesize, sortby, sortorder);
	}

	@DeleteMapping("deleteemployeeid/{id}")
	public void deleteemployee(@PathVariable("id") int id) {
		service.deleteemployee(id);

	}

	@PutMapping("updateemployee")
	public Employee updateemployee(@RequestBody Employee employee) {
		return service.update(employee);

	}

	@DeleteMapping("deletebyid/{id}")
	public Boolean deletebyid(@PathVariable int id) {
		if (id != id) {
			service.deleteemployee(id);
			return true;
		}
		return false;

	}

	@GetMapping("getemployeebyid/{id}")
	public Employee getemployeebyid(@PathVariable("id") int id) throws CustomException {
		return service.getemployeebyid(id);

	}

	@GetMapping("findbyname/{name}")
	public List<Employee> findbyname(@PathVariable("name") String name) {
		return service.findbyname(name);

	}

	@GetMapping("findbyemail/{email}")
	public List<Employee> findbyemail(@PathVariable("email") String email) {
		return service.findbyname(email);

	}
//	@GetMapping("findallemployee")
//	public List<Employee> findallemployee() {
//		return service.getemployee();
//	}

	@GetMapping("findbyaddress/{address}")
	public List<Employee> findbyaddress(@PathVariable("address") String address) {
		return service.findbyaddress(address);
	}

	@GetMapping("getempbynamelike/{name}")
	public List<Employee> getempbynamelike(@PathVariable("name") String name) {
		return service.getempbynamelike(name);
	}

	@GetMapping("employeenamelike/{name}")
	@ResponseBody
	public List<Employee> findByStartsWith(@Param("name") String name) {
		return service.findByStartsWith(name);
	}
	
	
	@GetMapping("callapi")
	 public String callService() {
       
		
		return service.callAnotherService();
		
	}
	
	@GetMapping("getall")
	public List<Student> getAllStudentUsingAnotherService(){
		return service.getAllStudentUsingAnotherService();
	}
	
	
	@PostMapping("addStudents")
	public void addStudent(@RequestBody Student student) {
		
		service.addStudent(student);
		
	}
	
	@DeleteMapping("deletestudent/{id}")
	public void deletestudent(@PathVariable int id) {
		
		service.deletestudent(id);
		
	}

}
