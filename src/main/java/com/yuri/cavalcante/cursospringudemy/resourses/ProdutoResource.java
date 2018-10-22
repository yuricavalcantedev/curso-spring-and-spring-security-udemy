package com.yuri.cavalcante.cursospringudemy.resourses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.cavalcante.cursospringudemy.domain.Produto;
import com.yuri.cavalcante.cursospringudemy.dto.ProdutoDTO;
import com.yuri.cavalcante.cursospringudemy.resourses.util.URL;
import com.yuri.cavalcante.cursospringudemy.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping(value="/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {

		Produto produto = produtoService.find(id);
		return ResponseEntity.ok(produto);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue="") String nome,
			@RequestParam(value = "categorias", defaultValue="") String categorias,
			@RequestParam(value = "page", defaultValue="0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value = "direction", defaultValue="ASC") String direction) {
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> categoriasList = URL.decodeIntList(categorias);
		
		Page<Produto> produtosList = produtoService.search(nomeDecoded, categoriasList, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> produtosDTO = produtosList.map(produto -> new ProdutoDTO(produto));
		
		return ResponseEntity.ok().body(produtosDTO);
	}
}
