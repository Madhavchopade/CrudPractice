package com.crudoperation.CrudPractice.Globalexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.http.HttpStatusCode;
import java.util.*;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity NoSuchElementException(NoSuchElementException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity TransactionSystemException(TransactionSystemException e)
	{
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity CustomException(CustomException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> MethodArgumentNotValidException(MethodArgumentNotValidException e){
		
		List<ObjectError> allerrors=e.getBindingResult().getAllErrors();
		
		Map<String,String> m=new HashMap<>();
		
		for(ObjectError o:allerrors) {
			
			String field=((FieldError)o).getField();
			
			String mes=o.getDefaultMessage();
			
			m.put(field, mes);
		}
		return new ResponseEntity<>(m,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(EmailValidation.class)
	public ResponseEntity EmailValidation(EmailValidation e) {
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e) {
//		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//
//	}
}
