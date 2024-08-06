package br.com.syonet.newsletter.domain.repository;

import br.com.syonet.newsletter.domain.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
}
