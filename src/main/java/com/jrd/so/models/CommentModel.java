package com.jrd.so.models;

import java.time.OffsetDateTime;

public class CommentModel {
   private Long id;
   private String description;
   private OffsetDateTime sentDate;
   
   
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public OffsetDateTime getSentDate() {
	return sentDate;
}
public void setSentDate(OffsetDateTime sentDate) {
	this.sentDate = sentDate;
}
   
   
   
   
}
