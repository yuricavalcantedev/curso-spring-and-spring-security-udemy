package com.yuri.cavalcante.cursospringudemy;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yuri.cavalcante.cursospringudemy.domain.Categoria;
import com.yuri.cavalcante.cursospringudemy.domain.Produto;
import com.yuri.cavalcante.cursospringudemy.repositories.CategoriaRepository;
import com.yuri.cavalcante.cursospringudemy.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoSpringUdemyApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;	
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringUdemyApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2.000);
		Produto p2 = new Produto(null, "Impressora",800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3)); // esse 'getProdutos' não é uma boa, pq ele da lista em si, deveria dar uma réplica da lista, para não mexer com os dados reais. 
		cat2.getProdutos().addAll(Arrays.asList(p1,p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
	}
	
	
}
