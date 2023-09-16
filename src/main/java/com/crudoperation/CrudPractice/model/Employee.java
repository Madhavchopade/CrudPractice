package com.crudoperation.CrudPractice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message="please enter the name")
//	@Pattern(regexp = "^[a-z]*$",message = "name should be in lowercase")
	private String name;
	//@Email
	private String email;
	@Pattern(regexp = "^[0-9]*$" , message = "please enter valid mobile number")
	private String mobileno;

	@Pattern(regexp = "^[A-Z]*$",message = "status should be in uppercase")
	private String address;
	
	@Pattern(regexp = "^[a-z]*$",message = "status should be in lowercase")
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toUpperCase();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public Employee() {
		super();
	}

}
