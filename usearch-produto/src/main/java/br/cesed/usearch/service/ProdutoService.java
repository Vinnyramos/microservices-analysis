package br.cesed.usearch.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.cesed.usearch.domain.Produto;
import br.cesed.usearch.exceptions.EntityAlreadyExistsException;
import br.cesed.usearch.repository.ProdutoRepository;

@Service
@Validated
public class ProdutoService {

    private final ProdutoRepository repository;

    @Autowired
    public ProdutoService(final ProdutoRepository repository) {
        this.repository = repository;
    }
    
    public Produto getById(String id) {    
    	return repository.findOne(id);
    }
    
	public List<Produto> listAllUsers() {
		return repository.findAll();
	}
	
    @Transactional
    public Produto save(@NotNull @Valid final Produto produto) {
    	
        Produto existing = repository.findOne(produto.getId());
       
        if (existing != null) {
            throw new EntityAlreadyExistsException();
        }
        
        return repository.save(produto);
    }

	public ProdutoRepository getRepository() {
		return repository;
	}
}
