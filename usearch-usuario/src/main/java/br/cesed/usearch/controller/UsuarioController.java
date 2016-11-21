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
import br.cesed.usearch.domain.Produto;
import br.cesed.usearch.domain.Usuario;
import br.cesed.usearch.service.UsuarioService;

/**
 * 
 * @author vinic
 *
 */
@RestController
public class UsuarioController {

	private final UsuarioService usuarioService;
	

	

	@Autowired
	public UsuarioController(final UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	
	@RequestMapping(value="/usuario", method = RequestMethod.GET)
	public ResponseEntity< List<Usuario> > listAllUsers() {
		return new ResponseEntity< List<Usuario> >
		(usuarioService.listAllUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/{id}",method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUser(@PathVariable String id) {
		
		Usuario usuario = usuarioService.getById(id);
		
		return usuario == null ? 
				new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND) : 
					new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@RequestMapping(value="/usuario", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody Usuario usuario) {

		try {
			usuarioService.save(usuario);
			return new ResponseEntity<String>(HttpStatus.CREATED);

		 } catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value="/listaprodutos", method = RequestMethod.GET)
	public ResponseEntity<Produto> listAllProdutos() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForEntity("http://localhost:8052/produto", Produto.class);
		
		
	
		
		
		
		//"http://localhost:8052/produto"
	}
		
        
        
	public UsuarioService getUserService() {
		return usuarioService;
	}
}
