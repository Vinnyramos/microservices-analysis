package br.cesed.usearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cesed.usearch.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	public Usuario findByNome(String nome);
	
}
