package com.yuri.cavalcante.cursospringudemy.resourses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.cavalcante.cursospringudemy.domain.Categoria;
import com.yuri.cavalcante.cursospringudemy.services.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Categoria categoria = categoriaService.buscar(id);
		return ResponseEntity.ok(categoria);
	}
}
