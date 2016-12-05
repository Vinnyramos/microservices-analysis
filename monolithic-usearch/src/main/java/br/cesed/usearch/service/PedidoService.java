package br.cesed.usearch.service;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.cesed.usearch.domain.Pedido;
import br.cesed.usearch.exceptions.EntityAlreadyExistsException;
import br.cesed.usearch.repository.PedidoRepository;

@Service
@Validated
public class PedidoService {

    private final PedidoRepository repository;
    
    @Autowired
    public PedidoService(final PedidoRepository repository) {
        this.repository = repository;
    }
    
    public Pedido getById(String id) {    
    	return repository.findOne(id);
    }
    
	public List<Pedido> listAllUsers() {
		return repository.findAll();
	}
	
    @Transactional
    public Pedido save(@NotNull @Valid final Pedido pedido) {
    	
    	Pedido existing = repository.findOne(pedido.getId());

        if (existing != null) {
            throw new EntityAlreadyExistsException();
        }
        
        return repository.save(pedido);
    }

	public PedidoRepository getRepository() {
		return repository;
	}
}
