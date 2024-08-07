package br.com.syonet.newsletter.domain.repository;

import br.com.syonet.newsletter.domain.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

    @Transactional(readOnly = true)
    @Query(value = "select n.* \n" +
            "from noticia n \n" +
            "where n.id not in (select nc.id_noticia from noticia_x_cliente nc)", nativeQuery = true)
    List<Noticia> listNoticiaNotSend();


}
