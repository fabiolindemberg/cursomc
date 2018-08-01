package com.fabiolindemberg.cursomc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fabiolindemberg.cursomc.domain.Categoria;
import com.fabiolindemberg.cursomc.domain.Cidade;
import com.fabiolindemberg.cursomc.domain.Estado;
import com.fabiolindemberg.cursomc.domain.Produto;
import com.fabiolindemberg.cursomc.repositories.CategoriaRepository;
import com.fabiolindemberg.cursomc.repositories.CidadeRepository;
import com.fabiolindemberg.cursomc.repositories.EstadoRepository;
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
	
		categoriaRepo.saveAll(categorias);
		produtoRepo.saveAll(produtos);
		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(c1,c2,c3));
	}
}
