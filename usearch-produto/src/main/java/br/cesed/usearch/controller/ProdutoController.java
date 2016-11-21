package br.cesed.usearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.cesed.usearch.domain.Produto;
import br.cesed.usearch.service.ProdutoService;

/**
 * 
 * @author vinic
 *
 */
@RestController
public class ProdutoController {

	private final ProdutoService produtoService;

	@Autowired
	public ProdutoController(final ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@RequestMapping(value="/produto", method = RequestMethod.GET)
	public ResponseEntity< List<Produto> > listAllUsers() {
		return new ResponseEntity< List<Produto> >
		(produtoService.listAllUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/produto/{id}",method = RequestMethod.GET)
	public ResponseEntity<Produto> getUser(@PathVariable String id) {
		
		Produto produto = produtoService.getById(id);
		
		return produto == null ? 
				new ResponseEntity<Produto>(HttpStatus.NOT_FOUND) : 
					new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}
	
	@RequestMapping(value="/produto", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody Produto produto) {

		try {
			produtoService.save(produto);
			return new ResponseEntity<String>(HttpStatus.CREATED);

		 } catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ProdutoService getUserService() {
		return produtoService;
	}
}
