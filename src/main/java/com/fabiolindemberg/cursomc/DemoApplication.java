package com.fabiolindemberg.cursomc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fabiolindemberg.cursomc.domain.Categoria;
import com.fabiolindemberg.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria escritorio = new Categoria(null, "Escritorio");
		Categoria informatica = new Categoria(null, "Informatica");
		
		List<Categoria> categorias = new ArrayList<>();
		categorias.add(escritorio);
		categorias.add(informatica);
	
		repo.saveAll(categorias);
	}
}
