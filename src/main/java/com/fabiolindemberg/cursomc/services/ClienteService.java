package com.fabiolindemberg.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fabiolindemberg.cursomc.domain.Cliente;
import com.fabiolindemberg.cursomc.dto.ClienteDTO;
import com.fabiolindemberg.cursomc.repositories.ClienteRepository;
import com.fabiolindemberg.cursomc.services.exceptions.DataIntegrityException;
import com.fabiolindemberg.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente nao encontrado!"));
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return repo.save(cliente);
	}

	public Cliente update(Cliente cliente) {
		Cliente oldObj = find(cliente.getId());
		updateData(oldObj, cliente);
		return repo.save(oldObj);
	}

	private void updateData(Cliente oldObj, Cliente cliente) {
		oldObj.setNome(cliente.getNome());
		oldObj.setEmail(cliente.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try
		{
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir por que ha entidades relacionadas!");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		return repo.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
	}

	public Cliente fromDto(ClienteDTO clienteDto) {
		return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);
	}
	
	
}
