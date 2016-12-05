package br.cesed.usearch.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.cesed.usearch.domain.Usuario;
import br.cesed.usearch.exceptions.EntityAlreadyExistsException;
import br.cesed.usearch.repository.UsuarioRepository;

@Service
@Validated
public class UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(final UsuarioRepository repository) {
        this.repository = repository;
    }
    
    public Usuario getById(String id) {    
    	return repository.findOne(id);
    }
    
	public List<Usuario> listAllUsers() {
		return repository.findAll();
	}
	
    @Transactional
    public Usuario save(@NotNull @Valid final Usuario participant) {
    	
        Usuario existing = repository.findOne(participant.getNome());

        if (existing != null) {
            throw new EntityAlreadyExistsException();
        }
        
        return repository.save(participant);
    }

	public UsuarioRepository getRepository() {
		return repository;
	}
}
