package br.cesed.usearch.service;

import java.util.List;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import br.cesed.usearch.domain.Venda;
import br.cesed.usearch.exceptions.EntityAlreadyExistsException;
import br.cesed.usearch.repository.VendaRepository;

@Service
@Validated
public class VendaService {

    private final VendaRepository repository;

    @Autowired
    public VendaService(final VendaRepository repository) {
        this.repository = repository;
    }
    
    public Venda getById(String id) {    
    	return repository.findOne(id);
    }
    
	public List<Venda> listAllUsers() {
		return repository.findAll();
	}
	
    @Transactional
    public Venda save(@NotNull @Valid final Venda venda) {
    	
    	Venda existing = repository.findOne(venda.getId());

        if (existing != null) {
            throw new EntityAlreadyExistsException();
        }
        
        return repository.save(venda);
    }

	public VendaRepository getRepository() {
		return repository;
	}
}
