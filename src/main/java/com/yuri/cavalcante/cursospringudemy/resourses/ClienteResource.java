package com.yuri.cavalcante.cursospringudemy.resourses;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yuri.cavalcante.cursospringudemy.domain.Cliente;
import com.yuri.cavalcante.cursospringudemy.dto.ClienteDTO;
import com.yuri.cavalcante.cursospringudemy.dto.ClienteNewDTO;
import com.yuri.cavalcante.cursospringudemy.services.ClienteService;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		List<Cliente> clientes = new ArrayList<>();
		clientes = clienteService.findAll();
		
		List<ClienteDTO> clientesDTO = clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(clientesDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {

		Cliente cliente = clienteService.find(id);
		return ResponseEntity.ok(cliente);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteNewDTO){

		Cliente cliente = clienteService.fromDTO(clienteNewDTO);
		cliente = clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cliente.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
		
		clienteDTO.setId(id);
		Cliente cliente = clienteService.fromDTO(clienteDTO);
		
		cliente = clienteService.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
	
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue="0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value = "direction", defaultValue="ASC") String direction) {
		
		Page<Cliente> clientes = clienteService.findPage(page, linesPerPage, orderBy, direction);
		
		Page<ClienteDTO> clientesDTO = clientes.map(cliente -> new ClienteDTO(cliente));
		
		return ResponseEntity.ok().body(clientesDTO);
	}
}
