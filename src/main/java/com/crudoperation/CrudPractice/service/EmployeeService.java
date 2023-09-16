package com.crudoperation.CrudPractice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crudoperation.CrudPractice.Globalexceptions.CustomException;
import com.crudoperation.CrudPractice.Globalexceptions.EmailValidation;
import com.crudoperation.CrudPractice.model.Employee;
import com.crudoperation.CrudPractice.model.Student;

@Service
public interface EmployeeService {

	// Crud operations

	public Employee saveemployee(Employee employee);

	public List<Employee> getemployee(int pageno,int pagesize,String sortby,String sortorder);

	public Boolean deleteemployee(int id);

	public Employee update(Employee employee);

	// find employees by their name,id,email,address

	public Employee getemployeebyid(int id) throws CustomException;

	public List<Employee> findbyname(String name);

	public List<Employee> findbyemail(String eamil);

	public List<Employee> findbyaddress(String address);

	// customs Queries

	public List<Employee> findallemployee();

	public List<Employee> getempbynamelike(String name);

	public List<Employee> findByStartsWith(String name);
	
	// Email validation
	
	public static void validateEmail(String email) throws EmailValidation{
		
		if(!isvalid(email)) {
			
			throw new EmailValidation("please enter valid email "+email);
		}
		
	}
	
	public static boolean isvalid(String email) {
		
		String s="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		
		boolean result=email.matches(s);
		
		return result;
		
		
	}
	
	
	

	
	
	// calling api from another service
	public String callAnotherService();
	
	public List<Student> getAllStudentUsingAnotherService();
	
	public void addStudent(Student student);
	
	public void deletestudent(int id);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
