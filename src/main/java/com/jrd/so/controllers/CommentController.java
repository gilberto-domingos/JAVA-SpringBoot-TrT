package com.jrd.so.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrd.so.exception.EntityNotFoundExceptionn;
import com.jrd.so.models.Comment;
import com.jrd.so.models.CommentInput;
import com.jrd.so.models.CommentModel;
import com.jrd.so.models.ServiceOrder;
import com.jrd.so.repository.ServiceOrderRepository;
import com.jrd.so.services.ServiceOrderService;

@RestController
@RequestMapping("/ordens-servico/{serviceOrderId}/comentarios")
public class CommentController {
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ModelMapper modelMapper;	
	
	@GetMapping
	public List<CommentModel> list(@PathVariable  Long serviceOrderId){
		ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
				.orElseThrow(() -> new EntityNotFoundExceptionn("Ordem de serviço não encontrada"));
		
		return toCollectionModel (serviceOrder.getComments());
	};	
	
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	public CommentModel add(@PathVariable Long serviceOrderId,
			                @Valid @RequestBody CommentInput commentInput) {
    	
        Comment comment =  serviceOrderService.addcommentt(serviceOrderId, 
        		           commentInput.getDescription());        
        return toModel(comment);		 
	}    
    
      private CommentModel toModel (Comment comment) {
    	   return modelMapper.map(comment, CommentModel.class); 
      }      
      
      private List<CommentModel> toCollectionModel(List<Comment>comments){
    	  return comments.stream()
    			  .map(comment -> toModel(comment))
    			  .collect(Collectors.toList());		  
      }
      
      
}







