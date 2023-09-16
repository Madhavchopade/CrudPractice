package com.crudoperation.CrudPractice.serviceimpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;
import java.util.Optional;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.crudoperation.CrudPractice.Globalexceptions.CustomException;
import com.crudoperation.CrudPractice.model.Employee;
import com.crudoperation.CrudPractice.model.Student;
import com.crudoperation.CrudPractice.repo.Repository;
import com.crudoperation.CrudPractice.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Serviceimpl implements EmployeeService {

	@Autowired
	Repository repository;

	@Override
	public Employee saveemployee(Employee employee) {

		return repository.save(employee);
	}

	public List<Employee> getemployee(int pageno, int pagesize, String sortby, String sortorder) {
		Sort sort;
		if (sortorder.equalsIgnoreCase("asc")) {
			sort = Sort.by("name").ascending();
		} else {
			sort = Sort.by("name").descending();
		}

		PageRequest pagable = PageRequest.of(pageno, pagesize, sort);
		Page<Employee> page = repository.findAll(pagable);
		List<Employee> employee = page.getContent();
		return employee;

	}

	@Override
	public Boolean deleteemployee(int id) {
		if (true) {
			repository.deleteById(id);
			return true;
		}
		return false;

	}

	@Override
	public Employee update(Employee employee) {

		return repository.save(employee);
	}

	@Override
	public Employee getemployeebyid(int id) throws CustomException {

		Employee employee1 = repository.findById(id)
				.orElseThrow(() -> new CustomException("please enter valid id " + id));
		return employee1;

	}

	@Override
	public List<Employee> findbyname(String name) {

		return repository.findByName(name);
	}

	@Override
	public List<Employee> findallemployee() {

		return repository.findAll();
	}

	@Override
	public List<Employee> findbyemail(String eamil) {

		return repository.findByEmail(eamil);
	}

	@Override
	public List<Employee> findbyaddress(String address) {

		return repository.findByAddress(address);
	}

	@Override
	public List<Employee> getempbynamelike(String name) {

		return repository.getempbynamelike(name);
	}

	@Override
	public List<Employee> findByStartsWith(String name) {

		return repository.findByStartsWith(name);
	}

	@Override
	public String callAnotherService() {
		String url = "http://localhost:8086/servicecalling";

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuilder responseContent = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				responseContent.append(line);
			}
			return responseContent.toString();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Student> getAllStudentUsingAnotherService() {
		String url = "http://localhost:8086/allstudent";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);

		try {
			CloseableHttpResponse response = httpClient.execute(httpget);

			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuilder responseContent = new StringBuilder();
			String s;
			while ((s = reader.readLine()) != null) {
				responseContent.append(s);
			}
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(responseContent.toString(), new TypeReference<List<Student>>() {
			});

		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addStudent(Student student) {
		String url = "http://localhost:8086/savestudent";
	
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		
		ObjectMapper objectmapper = new ObjectMapper();
		StringEntity data;
		try {
			data = new StringEntity(objectmapper.writeValueAsString(student), ContentType.APPLICATION_JSON);
			httppost.setEntity(data);
			CloseableHttpResponse response = httpclient.execute(httppost);
		} catch (UnsupportedCharsetException | IOException e1) {

			e1.printStackTrace();
		}

	}

	@Override
	public void deletestudent(int id) {
		String url="http://localhost:8086/delete/"+id;
		
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpDelete httpdelete=new HttpDelete(url);
		
		try {
			CloseableHttpResponse response=httpclient.execute(httpdelete);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
