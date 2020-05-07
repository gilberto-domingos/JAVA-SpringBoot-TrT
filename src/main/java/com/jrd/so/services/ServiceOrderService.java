package com.jrd.so.services;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrd.so.exception.BusinessException;
import com.jrd.so.exception.EntityNotFoundExceptionn;
import com.jrd.so.models.Client;
import com.jrd.so.models.Comment;
import com.jrd.so.models.ServiceOrder;
import com.jrd.so.models.StatusServiceOrder;
import com.jrd.so.repository.ClientRepository;
import com.jrd.so.repository.ServiceOrderRepository;

@Service
public class ServiceOrderService {
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private com.jrd.so.repository.commentRepository commentRepository;
   
	public ServiceOrder create(ServiceOrder serviceOrder) {
		Client client = clientRepository.findById(serviceOrder.getClient().getId())
				.orElseThrow(() -> new BusinessException("Cliente não encontrado"));
		
		serviceOrder.setClient(client); 
		serviceOrder.setStatus(StatusServiceOrder.ABERTA);
		serviceOrder.setOpenedDate(OffsetDateTime.now());
		
		return serviceOrderRepository.save(serviceOrder);
	}
	
	
	public void finish(Long serviceOrderId) {
		ServiceOrder serviceOrder = find(serviceOrderId);
		
		serviceOrder.finish();
		
		serviceOrderRepository.save(serviceOrder);
	}
	
	  public Comment addcommentt(Long serviceOrderId, String description) {
		  ServiceOrder serviceOrder = find(serviceOrderId);		  
		  
		  Comment comment  = new Comment();
		  comment.setSentDate(OffsetDateTime.now());
		  comment.setDescription(description);
		  comment.setServiceOrder(serviceOrder);
		  
		  return commentRepository.save(comment);		  
	  }	  
	  
	  private ServiceOrder find(Long serviceOrderId) {
			return serviceOrderRepository.findById(serviceOrderId)
					  .orElseThrow(()-> new EntityNotFoundExceptionn("Ordem de Serviço não encontrado"));
		}
	
}










