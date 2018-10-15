package com.yuri.cavalcante.cursospringudemy.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.cavalcante.cursospringudemy.domain.Pagamento;


@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Serializable>{

}
