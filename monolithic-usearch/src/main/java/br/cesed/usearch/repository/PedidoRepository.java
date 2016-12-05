package br.cesed.usearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cesed.usearch.domain.Pedido;
import br.cesed.usearch.domain.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, String> {

}
