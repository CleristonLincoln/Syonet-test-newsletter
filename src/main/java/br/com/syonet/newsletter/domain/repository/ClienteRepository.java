package br.com.syonet.newsletter.domain.repository;

import br.com.syonet.newsletter.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
