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
import br.cesed.usearch.domain.Pedido;
import br.cesed.usearch.domain.Produto;
import br.cesed.usearch.domain.Usuario;
import br.cesed.usearch.service.PedidoService;
import br.cesed.usearch.service.ProdutoService;
import br.cesed.usearch.service.UsuarioService;

/**
 * 
 * @author vinic
 *
 */
@RestController
public class PedidoController {

	private final PedidoService pedidoService;
	private final UsuarioService usuarioService;
	private final ProdutoService produtoService;

	@Autowired
	public PedidoController(final PedidoService pedidoService, final UsuarioService usuarioService, final ProdutoService produtoService) {
		this.pedidoService = pedidoService;
		this.usuarioService = usuarioService;
		this.produtoService = produtoService;
	}

	@RequestMapping(value="/pedido", method = RequestMethod.GET)
	public ResponseEntity< List<Pedido> > listAllUsers() {		
		return new ResponseEntity< List<Pedido> >
		(pedidoService.listAllUsers(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/pedido/{id}",method = RequestMethod.GET)
	public ResponseEntity<Pedido> getPedidoId(@PathVariable String id) {
		
		Pedido pedido= pedidoService.getById(id);
		
		return pedido == null ? 
				new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND) : 
					new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}
	
	

	@RequestMapping(value = "/pedido/descricao/{id}",method = RequestMethod.GET)
	public String getUser(@PathVariable String id) {
		
		Pedido pedido = pedidoService.getById(id);
		Usuario usuario = usuarioService.getById(pedido.getIdUsuario());
		Produto produto = produtoService.getById(pedido.getIdProduto());
		
		return " Codigo do Produto:"+pedido.getId()
		+ "\n Nome do Usuario:"+usuario.getNome()
		+"\n Descricao do Pedido: "+produto.getDescricao();
		
	}
	
	
	public boolean validaUsuario(Pedido pedido){
		
		Usuario usuario = usuarioService.getById(pedido.getIdUsuario());
		if(usuario != null)
		{return true;
		}
		return false;
	}
	
	
		public boolean validaProduto(Pedido pedido){
			
			Produto produto = produtoService.getById(pedido.getIdProduto());
			if(produto != null)
			{return true;
			}
			return false;
		}
	
	@RequestMapping(value="/pedido", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody Pedido pedido) {
		
		         
        if(validaUsuario(pedido) == true && validaUsuario(pedido) == true) {
        	pedidoService.save(pedido);
			return new ResponseEntity<String>(HttpStatus.CREATED); 	 
        }
		else{
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);	
	    }
	}

	public PedidoService getUserService() {
		return pedidoService;
	}
}
