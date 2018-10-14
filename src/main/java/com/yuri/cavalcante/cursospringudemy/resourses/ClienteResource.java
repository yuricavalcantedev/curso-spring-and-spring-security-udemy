package com.yuri.cavalcante.cursospringudemy.resourses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.cavalcante.cursospringudemy.domain.Cliente;
import com.yuri.cavalcante.cursospringudemy.services.ClienteService;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Cliente cliente = clienteService.buscar(id);
		return ResponseEntity.ok(cliente);
	}
}
