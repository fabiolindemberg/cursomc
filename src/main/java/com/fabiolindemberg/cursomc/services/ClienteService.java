package com.fabiolindemberg.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabiolindemberg.cursomc.domain.Cliente;
import com.fabiolindemberg.cursomc.repositories.ClienteRepository;
import com.fabiolindemberg.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente nao encontrado!"));
	}
}
