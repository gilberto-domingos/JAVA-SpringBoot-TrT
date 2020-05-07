package com.jrd.so.controllers;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrd.so.RPmodels.ServiceOrderRPmodel;
import com.jrd.so.models.ServiceOrder;
import com.jrd.so.models.ServiceOrderInput;
import com.jrd.so.repository.ServiceOrderRepository;
import com.jrd.so.services.ServiceOrderService;


@RestController
@RequestMapping("/ordens-servico")
public class ServiceOrderController {
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServiceOrderRPmodel create(@Valid @RequestBody ServiceOrderInput serviceOrderInput) {
		ServiceOrder serviceOrder = toEntity(serviceOrderInput);
		return toModel(serviceOrderService.create(serviceOrder));
	}
	
	
	@GetMapping
	public List<ServiceOrderRPmodel> list(){
		return toCollectionModel(serviceOrderRepository.findAll());
	}
	
	
	@GetMapping("/{serviceOrderId}")
	public ResponseEntity<ServiceOrderRPmodel> findid(@PathVariable Long serviceOrderId){
		Optional<ServiceOrder>serviceOrder = serviceOrderRepository.findById(serviceOrderId);
		
		if(serviceOrder.isPresent()) {
			ServiceOrderRPmodel serviceOrderRPmodel = toModel(serviceOrder.get());
			return ResponseEntity.ok(serviceOrderRPmodel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{serviceOrderId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finish(@PathVariable Long serviceOrderId) {
		serviceOrderService.finish(serviceOrderId);
	}
	
	
	
	
	private ServiceOrderRPmodel toModel(ServiceOrder serviceOrder) {
		return modelMapper.map(serviceOrder, ServiceOrderRPmodel.class);
	}
	
	
	private List<ServiceOrderRPmodel>toCollectionModel(List<ServiceOrder>serviceOrders){
		return serviceOrders.stream()
				.map(serviceOrder -> toModel(serviceOrder))
				.collect(Collectors.toList());
		
	}
	
	
	private ServiceOrder toEntity(ServiceOrderInput serviceOrderInput) {
		return modelMapper.map(serviceOrderInput, ServiceOrder.class);
	}
	
	
   
}



















