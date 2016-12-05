package br.cesed.usearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cesed.usearch.domain.Produto;
import br.cesed.usearch.domain.Usuario;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

	public Usuario findByDescricao(String descricao);
	
}
