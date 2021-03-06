package com.yuri.cavalcante.cursospringudemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.cavalcante.cursospringudemy.domain.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
