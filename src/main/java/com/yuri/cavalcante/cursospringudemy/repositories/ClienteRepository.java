package com.yuri.cavalcante.cursospringudemy.repositories;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.cavalcante.cursospringudemy.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Serializable>{

	@Transactional
	Cliente findByEmail(String email);
	
	Cliente findByCpfOuCnpj(String cpfOuCnpj);
	
}
