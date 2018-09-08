package com.fabiolindemberg.cursomc.categoria;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.fabiolindemberg.cursomc.domain.Categoria;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CategoriaTest {
	
	@LocalServerPort
	private String port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	private static final String url = "http://localhost:";
	
	@Test
	public void testGetCategoriaList() throws Exception{
		Categoria categorias = this.restTemplate.getForEntity("http://localhost:" + port + "/categorias/1", Categoria.class).getBody();
		
	}
}
