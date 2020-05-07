package com.jrd.so.exceptionHandler;



import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jrd.so.exception.BusinessException;
import com.jrd.so.exception.EntityNotFoundExceptionn;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;	
	
	@ExceptionHandler(EntityNotFoundExceptionn.class)
	public ResponseEntity<Object> handleEntityNotFoundExceptionn(BusinessException ex, WebRequest request) {
		var status = HttpStatus.NOT_FOUND;
		
		var problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitle(ex.getMessage());
		problem.setDateHour(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}	
		
			
			@ExceptionHandler(BusinessException.class)
			public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request) {
				var status = HttpStatus.BAD_REQUEST;
				
				var problem = new Problem();
				problem.setStatus(status.value());
				problem.setTitle(ex.getMessage());
				problem.setDateHour(OffsetDateTime.now());
				
				return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
			}	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var fields = new ArrayList<Problem.field>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			
			String nameField = ((FieldError)error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			fields.add(new Problem.field(nameField, message));
		}
		
		var problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitle("Um ou mais campos estão inválidos. Preencha corretamente e tente novamente");
	    problem.setDateHour(OffsetDateTime.now());
	    problem.setFields(fields);
	    
		return super.handleExceptionInternal(ex, problem, headers, status, request);
	}

}
