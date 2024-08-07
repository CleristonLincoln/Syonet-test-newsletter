package br.com.syonet.newsletter.api.v1.openApi;

import br.com.syonet.newsletter.api.v1.input.NoticiaInput;
import br.com.syonet.newsletter.api.v1.model.NoticiaModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Tag(name = "Noticias")
public interface NoticiaControllerOpenApi {

    ResponseEntity<NoticiaModel> create(NoticiaInput noticiaInput);

    ResponseEntity<Page<NoticiaModel>> getAll(Pageable pageable);
}
