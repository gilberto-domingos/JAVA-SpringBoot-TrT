package com.jrd.so.RPmodels;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.jrd.so.models.ClientResumeModel;
import com.jrd.so.models.StatusServiceOrder;

//essa é uma classe espcífica para trabalhar com Representation Model

public class ServiceOrderRPmodel {
    private Long id;   
    private ClientResumeModel client;
    private String description;
    private BigDecimal price;
    private StatusServiceOrder status;
    private OffsetDateTime openedDate;
    private OffsetDateTime closedDate;
	
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
	public ClientResumeModel getClient() {
		return client;
	}

	public void setClient(ClientResumeModel client) {
		this.client = client;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public StatusServiceOrder getStatus() {
		return status;
	}

	public void setStatus(StatusServiceOrder status) {
		this.status = status;
	}

	public OffsetDateTime getOpenedDate() {
		return openedDate;
	}

	public void setOpenedDate(OffsetDateTime openedDate) {
		this.openedDate = openedDate;
	}

	public OffsetDateTime getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(OffsetDateTime closedDate) {
		this.closedDate = closedDate;
	}
    
    
    
    
    
    
}
