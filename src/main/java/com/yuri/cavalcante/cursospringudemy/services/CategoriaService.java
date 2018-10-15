package com.yuri.cavalcante.cursospringudemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yuri.cavalcante.cursospringudemy.domain.Categoria;
import com.yuri.cavalcante.cursospringudemy.repositories.CategoriaRepository;
import com.yuri.cavalcante.cursospringudemy.resourses.exceptions.DataIntegrityException;
import com.yuri.cavalcante.cursospringudemy.resourses.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll(){
		
		return categoriaRepository.findAll();		
	}
	
	public Categoria find(Integer id){
		Optional<Categoria> categoria = categoriaRepository.findById(id); 
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + "! Tipo : " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}
	
	public Categoria update(Categoria categoria) {
		find(categoria.getId()); //se não der certo, ele já vai lançar uma exceção
		return categoriaRepository.save(categoria);
	}
	
	public void delete(Integer id) {		 
		
		find(id); //se não der certo, ele já vai lançar uma exceção
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
		}
		
	}
}
