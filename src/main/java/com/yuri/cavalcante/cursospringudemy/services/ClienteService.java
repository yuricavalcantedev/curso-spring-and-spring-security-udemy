package com.yuri.cavalcante.cursospringudemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.cavalcante.cursospringudemy.domain.Cidade;
import com.yuri.cavalcante.cursospringudemy.domain.Cliente;
import com.yuri.cavalcante.cursospringudemy.domain.Endereco;
import com.yuri.cavalcante.cursospringudemy.domain.enums.TipoCliente;
import com.yuri.cavalcante.cursospringudemy.dto.ClienteDTO;
import com.yuri.cavalcante.cursospringudemy.dto.ClienteNewDTO;
import com.yuri.cavalcante.cursospringudemy.repositories.ClienteRepository;
import com.yuri.cavalcante.cursospringudemy.repositories.EnderecoRepository;
import com.yuri.cavalcante.cursospringudemy.resourses.exceptions.DataIntegrityException;
import com.yuri.cavalcante.cursospringudemy.resourses.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<Cliente> findAll(){

		return clienteRepository.findAll();		
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);		
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente find(Integer id){
		Optional<Cliente> cliente = clienteRepository.findById(id); 
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + "! Tipo : " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	public Cliente update(Cliente cliente) {
		Cliente newCliente = find(cliente.getId()); //se não der certo, ele já vai lançar uma exceção
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);
	}

	private void updateData(Cliente newCliente, Cliente oldCliente) {
		newCliente.setNome(oldCliente.getNome());
		newCliente.setEmail(oldCliente.getEmail());
	}
	
	public void delete(Integer id) {		 

		find(id); //se não der certo, ele já vai lançar uma exceção
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
		}

	}

	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(),clienteDTO.getNome(),clienteDTO.getEmail(), null, null);
		
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
		Cliente cliente = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipo()));
		Cidade cidade = new Cidade(clienteNewDTO.getCidadeId(), null, null);		
		Endereco endereco = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cliente, cidade);
		
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteNewDTO.getTelefone1());
		
		if(clienteNewDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone2());
		
		}if(clienteNewDTO.getTelefone3() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone3());
		}
		
		return cliente;
	}

}
