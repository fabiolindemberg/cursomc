package com.fabiolindemberg.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabiolindemberg.cursomc.domain.Pedido;
import com.fabiolindemberg.cursomc.repositories.PedidoRepository;
import com.fabiolindemberg.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido nao encontrado!"));
	}
}
