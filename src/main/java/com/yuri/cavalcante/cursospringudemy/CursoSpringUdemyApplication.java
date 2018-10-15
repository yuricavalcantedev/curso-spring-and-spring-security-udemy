package com.yuri.cavalcante.cursospringudemy;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yuri.cavalcante.cursospringudemy.domain.Categoria;
import com.yuri.cavalcante.cursospringudemy.domain.Cidade;
import com.yuri.cavalcante.cursospringudemy.domain.Cliente;
import com.yuri.cavalcante.cursospringudemy.domain.Endereco;
import com.yuri.cavalcante.cursospringudemy.domain.Estado;
import com.yuri.cavalcante.cursospringudemy.domain.ItemPedido;
import com.yuri.cavalcante.cursospringudemy.domain.Pagamento;
import com.yuri.cavalcante.cursospringudemy.domain.PagamentoComBoleto;
import com.yuri.cavalcante.cursospringudemy.domain.PagamentoComCartao;
import com.yuri.cavalcante.cursospringudemy.domain.Pedido;
import com.yuri.cavalcante.cursospringudemy.domain.Produto;
import com.yuri.cavalcante.cursospringudemy.domain.enums.EstadoPagamento;
import com.yuri.cavalcante.cursospringudemy.domain.enums.TipoCliente;
import com.yuri.cavalcante.cursospringudemy.repositories.CategoriaRepository;
import com.yuri.cavalcante.cursospringudemy.repositories.CidadeRepository;
import com.yuri.cavalcante.cursospringudemy.repositories.ClienteRepository;
import com.yuri.cavalcante.cursospringudemy.repositories.EnderecoRepository;
import com.yuri.cavalcante.cursospringudemy.repositories.EstadoRepository;
import com.yuri.cavalcante.cursospringudemy.repositories.ItemPedidoRepository;
import com.yuri.cavalcante.cursospringudemy.repositories.PagamentoRepository;
import com.yuri.cavalcante.cursospringudemy.repositories.PedidoRepository;
import com.yuri.cavalcante.cursospringudemy.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoSpringUdemyApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;	
	

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringUdemyApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2.000);
		Produto p2 = new Produto(null, "Impressora",800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3)); // esse 'getProdutos' não é uma boa, pq ele da lista em si, deveria dar uma réplica da lista, para não mexer com os dados reais. 
		cat2.getProdutos().addAll(Arrays.asList(p1,p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
			
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "00033388802", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("3222-8900","3300-8788"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Ap 203", "Bairro x ", "3622000", cli1, cid1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "3622000", cli1, cid2);
		
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1,e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:35"), cli1,e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADADO, ped1,6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.000, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p1, 0.000, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		
		ped1.getItems().addAll(Arrays.asList(ip1,ip2));
		ped2.getItems().addAll(Arrays.asList(ip3));
		
		
		p1.getItems().addAll(Arrays.asList(ip1));
		p2.getItems().addAll(Arrays.asList(ip3));
		p3.getItems().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}
	
	
}
