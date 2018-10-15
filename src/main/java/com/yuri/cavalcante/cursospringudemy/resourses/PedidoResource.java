package com.yuri.cavalcante.cursospringudemy.resourses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.cavalcante.cursospringudemy.domain.Pedido;
import com.yuri.cavalcante.cursospringudemy.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService categoriaService;

	@GetMapping(value="/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {

		Pedido categoria = categoriaService.find(id);
		return ResponseEntity.ok(categoria);
	}
}
