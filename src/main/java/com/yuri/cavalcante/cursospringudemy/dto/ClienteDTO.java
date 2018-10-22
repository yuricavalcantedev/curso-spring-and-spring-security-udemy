package com.yuri.cavalcante.cursospringudemy.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.yuri.cavalcante.cursospringudemy.domain.Cliente;
import com.yuri.cavalcante.cursospringudemy.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min = 5, max = 120, message= "O nome deve ter ser entre 5 e 120 caracteres")
	private String nome;
	
	@Email
	@NotEmpty(message="Preenchimento obrigatório")
	private String email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente cliente) {
		this.setId(cliente.getId());
		this.setNome(cliente.getNome());
		this.setEmail(cliente.getEmail());
	}
	
	public ClienteDTO(Integer id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
		
}
