package com.jrd.so.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrd.so.exception.BusinessException;
import com.jrd.so.models.Client;
import com.jrd.so.repository.ClientRepository;

@Service
public class RegisterClientService {
   
	@Autowired
	private ClientRepository clientRepository; 	
	
	
	public Client save(Client client) {
		Client clientExisting = clientRepository.findByEmail(client.getEmail());
		
		if(clientExisting != null && !clientExisting.equals(client)) {
			throw new BusinessException("Já existe cliente com esse email");
		}
		
		return clientRepository.save(client);
	}
	
	public void delete(Long clientId) {
		clientRepository.deleteById(clientId);
	}
	
	
	
	
	
	
}
