package com.yuri.cavalcante.cursospringudemy.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.cavalcante.cursospringudemy.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Serializable>{

}
