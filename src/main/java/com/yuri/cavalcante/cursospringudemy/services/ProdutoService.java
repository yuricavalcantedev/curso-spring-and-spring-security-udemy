package com.yuri.cavalcante.cursospringudemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yuri.cavalcante.cursospringudemy.domain.Categoria;
import com.yuri.cavalcante.cursospringudemy.domain.Produto;
import com.yuri.cavalcante.cursospringudemy.repositories.CategoriaRepository;
import com.yuri.cavalcante.cursospringudemy.repositories.ProdutoRepository;
import com.yuri.cavalcante.cursospringudemy.resourses.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id){
		Optional<Produto> produto = produtoRepository.findById(id); 
		return produto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + produto.get().getId()));  
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias =  categoriaRepository.findAllById(ids);
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias,pageRequest);
	}
}
