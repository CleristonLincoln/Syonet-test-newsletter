package br.com.syonet.newsletter.domain.repository;

import br.com.syonet.newsletter.domain.model.ControleEnvio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControleEnvioRepository extends JpaRepository<ControleEnvio, Long> {
}
