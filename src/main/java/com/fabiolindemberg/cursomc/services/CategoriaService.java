package com.fabiolindemberg.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fabiolindemberg.cursomc.domain.Categoria;
import com.fabiolindemberg.cursomc.domain.Cliente;
import com.fabiolindemberg.cursomc.dto.CategoriaDTO;
import com.fabiolindemberg.cursomc.repositories.CategoriaRepository;
import com.fabiolindemberg.cursomc.services.exceptions.DataIntegrityException;
import com.fabiolindemberg.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Categoria nao encontrada!"));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repo.save(categoria);
	}

	public Categoria update(Categoria obj) {
		Categoria oldObj = find(obj.getId());
		updateData(oldObj, obj);
		return repo.save(oldObj);
	}

	private void updateData(Categoria oldObj, Categoria obj) {
		oldObj.setNome(obj.getNome());		
	}

	public void delete(Integer id) {
		find(id);
		try
		{
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possua produtos!");
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		return repo.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
	}

	public Categoria fromDto(CategoriaDTO categoriaDto) {
		return new Categoria(categoriaDto.getId(), categoriaDto.getNome());
	}
	
}
