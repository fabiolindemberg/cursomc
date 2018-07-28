package com.fabiolindemberg.cursomc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fabiolindemberg.cursomc.domain.Categoria;
import com.fabiolindemberg.cursomc.domain.Produto;
import com.fabiolindemberg.cursomc.repositories.CategoriaRepository;
import com.fabiolindemberg.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;

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
	
		categoriaRepo.saveAll(categorias);
		produtoRepo.saveAll(produtos);
	}
}
