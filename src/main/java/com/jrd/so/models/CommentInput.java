package com.jrd.so.models;

import javax.validation.constraints.NotBlank;

public class CommentInput {
	
	@NotBlank
   private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
