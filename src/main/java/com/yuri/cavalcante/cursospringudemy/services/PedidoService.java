package com.yuri.cavalcante.cursospringudemy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.cavalcante.cursospringudemy.domain.Pedido;
import com.yuri.cavalcante.cursospringudemy.repositories.PedidoRepository;
import com.yuri.cavalcante.cursospringudemy.resourses.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id){
		Optional<Pedido> cliente = pedidoRepository.findById(id); 
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + cliente.get().getId()));  
	}
	
}
