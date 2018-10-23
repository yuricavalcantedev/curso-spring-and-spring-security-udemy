package com.yuri.cavalcante.cursospringudemy.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.yuri.cavalcante.cursospringudemy.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamento, Date instantePedido) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantePedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagamento.setDataVencimento(cal.getTime());
	}

}
