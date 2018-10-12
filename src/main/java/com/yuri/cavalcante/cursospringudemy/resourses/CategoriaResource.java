package com.yuri.cavalcante.cursospringudemy.resourses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.cavalcante.cursospringudemy.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@GetMapping
	public List<Categoria> listar() {
		
		Categoria categoria = new Categoria(1,"Informática");
		Categoria categoria2 = new Categoria(2,"Escritório");
		
		List<Categoria> listCategoria = new ArrayList<Categoria>();
		listCategoria.add(categoria);
		listCategoria.add(categoria2);
		
		return listCategoria;
	}
}
