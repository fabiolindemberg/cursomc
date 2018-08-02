package com.fabiolindemberg.cursomc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fabiolindemberg.cursomc.domain.Categoria;
import com.fabiolindemberg.cursomc.domain.Cidade;
import com.fabiolindemberg.cursomc.domain.Cliente;
import com.fabiolindemberg.cursomc.domain.Endereco;
import com.fabiolindemberg.cursomc.domain.Estado;
import com.fabiolindemberg.cursomc.domain.Pagamento;
import com.fabiolindemberg.cursomc.domain.PagamentoComBoleto;
import com.fabiolindemberg.cursomc.domain.PagamentoComCartao;
import com.fabiolindemberg.cursomc.domain.Pedido;
import com.fabiolindemberg.cursomc.domain.Produto;
import com.fabiolindemberg.cursomc.domain.enums.EstadoPagamento;
import com.fabiolindemberg.cursomc.domain.enums.TipoCliente;
import com.fabiolindemberg.cursomc.repositories.CategoriaRepository;
import com.fabiolindemberg.cursomc.repositories.CidadeRepository;
import com.fabiolindemberg.cursomc.repositories.ClienteRepository;
import com.fabiolindemberg.cursomc.repositories.EnderecoRepository;
import com.fabiolindemberg.cursomc.repositories.EstadoRepository;
import com.fabiolindemberg.cursomc.repositories.PagamentoRepository;
import com.fabiolindemberg.cursomc.repositories.PedidoRepository;
import com.fabiolindemberg.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;

	@Autowired
	private PedidoRepository pedidoRepo;

	@Autowired
	private PagamentoRepository pagamentoRepo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria escritorio = new Categoria(null, "Escritorio");
		Categoria informatica = new Categoria(null, "Informatica");
		
		List<Categoria> categorias = new ArrayList<>();
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		p1.getCategorias().add(escritorio);
		p2.getCategorias().add(escritorio);
		p3.getCategorias().add(escritorio);
		
		List<Produto> produtos = new ArrayList<>();
		produtos.add(p1);
		produtos.add(p2);
		produtos.add(p3);
		
		escritorio.getProdutos().addAll(produtos);	

		categorias.add(escritorio);
		categorias.add(informatica);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Minas Gerais", est2);
	
		Cliente cli1 = new Cliente(null,"Maria Silva", "maria@gmail.com", "00000000000", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("25254254","52487589"));
		
		Endereco end1 = new Endereco(null, "Rua Flores","300","Apto 203", "Jardim", "52455875", cli1, c1);
		Endereco end2 = new Endereco(null, "Avenida Matos","105","Sala 800", "Centro", "57855875", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PEDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		categoriaRepo.saveAll(categorias);
		produtoRepo.saveAll(produtos);
		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepo.save(cli1);
		enderecoRepo.saveAll(Arrays.asList(end1, end2));
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1, pagto2));
		

	}
}
