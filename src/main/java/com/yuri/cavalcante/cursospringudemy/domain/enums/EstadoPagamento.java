package com.yuri.cavalcante.cursospringudemy.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1,"Pendente"),
	QUITADADO(2,"Quitado"),
	CANCELAD(3,"Cancelado");

	
	private Integer cod;
	private String descricao;
	
	
	private EstadoPagamento(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}


	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public static EstadoPagamento toEnum(Integer cod) {

		if(cod == null)
			return null;
		for(EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
			if(cod.equals(estadoPagamento.getCod()))
				return estadoPagamento;
		}
		
		throw new IllegalArgumentException("Id inválido:" + cod);
	}
}
