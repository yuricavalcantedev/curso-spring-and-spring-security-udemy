package com.yuri.cavalcante.cursospringudemy.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.yuri.cavalcante.cursospringudemy.domain.Cliente;
import com.yuri.cavalcante.cursospringudemy.domain.enums.TipoCliente;
import com.yuri.cavalcante.cursospringudemy.dto.ClienteNewDTO;
import com.yuri.cavalcante.cursospringudemy.repositories.ClienteRepository;
import com.yuri.cavalcante.cursospringudemy.resourses.exceptions.FieldMessage;
import com.yuri.cavalcante.cursospringudemy.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDTO.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
		}
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CNPJ inválido"));
		}
		
		Cliente clienteAux = clienteRepository.findByEmail(objDTO.getEmail());
		if(clienteAux != null) {
			list.add(new FieldMessage("email","Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}