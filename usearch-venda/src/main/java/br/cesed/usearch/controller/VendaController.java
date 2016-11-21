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
import br.cesed.usearch.domain.Venda;
import br.cesed.usearch.service.VendaService;

/**
 * 
 * @author vinic
 *
 */
@RestController
public class VendaController {

	private final VendaService vendaService;

	@Autowired
	public VendaController(final VendaService vendaService) {
		this.vendaService = vendaService;
	}

	@RequestMapping(value="/venda", method = RequestMethod.GET)
	public ResponseEntity< List<Venda> > listAllUsers() {
		return new ResponseEntity< List<Venda> >
		(vendaService.listAllUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/venda/{id}",method = RequestMethod.GET)
	public ResponseEntity<Venda> getUser(@PathVariable String id) {
		
		Venda venda = vendaService.getById(id);
		
		return venda == null ? 
				new ResponseEntity<Venda>(HttpStatus.NOT_FOUND) : 
					new ResponseEntity<Venda>(venda, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/venda/descricao/{id}",method = RequestMethod.GET)
	public String getVenda(@PathVariable String id) {
		RestTemplate restTemplate1 = new RestTemplate();
		RestTemplate restTemplate2 = new RestTemplate();
		RestTemplate restTemplate3 = new RestTemplate();
		Venda venda = vendaService.getById(id);
		Pedido pedido = restTemplate1.getForObject("http://localhost:8090/pedido/"+venda.getIdPedido(), Pedido.class);
		Produto produto = restTemplate2.getForObject("http://localhost:8085/produto/"+pedido.getIdProduto(), Produto.class);
		Usuario usuario = restTemplate3.getForObject("http://localhost:8082/usuario/"+pedido.getIdUsuario(), Usuario.class);
		/*return pedido == null ? 
				new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND) : 
					new ResponseEntity<Pedido>(pedido, HttpStatus.OK);*/
		return " Codigo do da venda:"+venda.getId()
		+ "\n Nome do Usuario:"+usuario.getNome()
		+ "\n Endereço:"+usuario.getEndereco()
		+ "\n Produto:"+produto.getDescricao()
		+"\n Valor: "+produto.getPreco();
		//return "Retornou uma String Urruuuuu!!!!";
	}
	
	
	
	// Procura via rest se o id passado no pedido existe no serviço de usuario
		public boolean validaPedido(Venda venda){
			
			RestTemplate restTemplate = new RestTemplate();
			Pedido pedido= restTemplate.getForObject("http://localhost:8090/pedido/"+venda.getIdPedido(), Pedido.class);
			if(pedido != null)
			{	return true;
			}
			return false;
		}
	
	@RequestMapping(value="/venda", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody Venda venda) {

		if(validaPedido(venda) == true) {
			vendaService.save(venda);
			return new ResponseEntity<String>(HttpStatus.CREATED); 	 
        }
		else{
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);	
	    }
	}

	public VendaService getUserService() {
		return vendaService;
	}
}
