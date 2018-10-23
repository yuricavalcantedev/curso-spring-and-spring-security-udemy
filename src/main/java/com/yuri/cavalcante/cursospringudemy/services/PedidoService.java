package com.yuri.cavalcante.cursospringudemy.services;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.cavalcante.cursospringudemy.domain.ItemPedido;
import com.yuri.cavalcante.cursospringudemy.domain.PagamentoComBoleto;
import com.yuri.cavalcante.cursospringudemy.domain.Pedido;
import com.yuri.cavalcante.cursospringudemy.domain.enums.EstadoPagamento;
import com.yuri.cavalcante.cursospringudemy.repositories.ItemPedidoRepository;
import com.yuri.cavalcante.cursospringudemy.repositories.PagamentoRepository;
import com.yuri.cavalcante.cursospringudemy.repositories.PedidoRepository;
import com.yuri.cavalcante.cursospringudemy.resourses.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Pedido find(Integer id){
		Optional<Pedido> pedido = pedidoRepository.findById(id); 
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + pedido.get().getId()));  
	}

	@Transactional
	public Pedido insert(Pedido pedido) {
		
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamento = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagamento, pedido.getInstante());
		}
		
		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		
		for(ItemPedido item : pedido.getItems()) {
			item.setDesconto(0.0);			
			item.setPreco(produtoService.find(item.getProduto().getId()).getPreco());
			item.setPedido(pedido);
		}
		
		itemPedidoRepository.saveAll(pedido.getItems());
		return pedido;
	}
	
}
