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
import org.springframework.web.client.RestTemplate;
import br.cesed.usearch.domain.Pedido;
import br.cesed.usearch.domain.Produto;
import br.cesed.usearch.domain.Usuario;
import br.cesed.usearch.service.PedidoService;

/**
 * 
 * @author vinic
 *
 */
@RestController
public class PedidoController {

	private final PedidoService pedidoService;

	@Autowired
	public PedidoController(final PedidoService pedidoService) {
		this.pedidoService = pedidoService;
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
	public String getPedidoDescricao(@PathVariable String id) {
		RestTemplate restTemplate1 = new RestTemplate();
		RestTemplate restTemplate2 = new RestTemplate();
		Pedido pedido = pedidoService.getById(id);
		Usuario usuario = restTemplate1.getForObject("http://localhost:8082/usuario/"+pedido.getIdUsuario(), Usuario.class);
		Produto produto = restTemplate2.getForObject("http://localhost:8085/produto/"+pedido.getIdProduto(), Produto.class);
		return " Codigo do Produto:"+pedido.getId()
		+ "\n Nome do Usuario:"+usuario.getNome()
		+"\n Descrição do Pedido: "+produto.getDescricao();
		
	}
	
	// Procura via rest se o id passado no pedido existe no serviço de usuario
	public boolean validaUsuario(Pedido pedido){
		
		RestTemplate restTemplate = new RestTemplate();
		Usuario usuario = restTemplate.getForObject("http://localhost:8082/usuario/"+pedido.getIdUsuario(), Usuario.class);
		if(usuario != null)
		{return true;
		}
		return false;
	}
	
	// Procura via rest se o id passado no pedido existe no serviço de produto
		public boolean validaProduto(Pedido pedido){
			
			RestTemplate restTemplate = new RestTemplate();
			Produto produto = restTemplate.getForObject("http://localhost:8085/produto/"+pedido.getIdProduto(), Produto.class);
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
