package com.jrd.so.models;

import javax.validation.constraints.NotNull;

//Classe só para referenciar o ID

public class ClientIdInput {
   
	@NotNull
	private Long id;
   

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}
   
   
   
}
