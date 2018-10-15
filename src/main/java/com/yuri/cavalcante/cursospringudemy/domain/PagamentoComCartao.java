package com.yuri.cavalcante.cursospringudemy.domain;

import javax.persistence.Entity;

import com.yuri.cavalcante.cursospringudemy.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{

	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
		// TODO Auto-generated constructor stub
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	

	
}


