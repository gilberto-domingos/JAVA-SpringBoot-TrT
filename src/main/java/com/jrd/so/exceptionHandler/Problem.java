package com.jrd.so.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problem {
   private Integer status;
   private OffsetDateTime dateHour;
   private String title;
   private List<field>fields;
   
   public static class field{
	   
	   private String nameField;
	   private String message;
	   
	public field() { }
	
	public field(String nameField, String message) {
		super();
		this.nameField = nameField;
		this.message = message;
	}



	public String getNameField() {
		return nameField;
	}
	public void setNameField(String nameField) {
		this.nameField = nameField;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	   
	   
   }
   
   
   
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public OffsetDateTime getDateHour() {
	return dateHour;
}
public void setDateHour(OffsetDateTime dateHour) {
	this.dateHour = dateHour;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public List<field> getFields() {
	return fields;
}
public void setFields(List<field> fields) {
	this.fields = fields;
}

   
   
   
}
