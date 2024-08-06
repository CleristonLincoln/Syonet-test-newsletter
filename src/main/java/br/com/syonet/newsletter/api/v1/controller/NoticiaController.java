package br.com.syonet.newsletter.api.v1.controller;

import br.com.syonet.newsletter.api.v1.input.ClienteInput;
import br.com.syonet.newsletter.api.v1.input.NoticiaInput;
import br.com.syonet.newsletter.domain.model.Cliente;
import br.com.syonet.newsletter.domain.model.Noticia;
import br.com.syonet.newsletter.domain.service.NoticiaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("noticias")
@RequiredArgsConstructor
public class NoticiaController {

    private final NoticiaService service;

    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<Noticia> create(@RequestBody NoticiaInput noticiaInput) {

        Noticia noticia = service.save(mapper.map(noticiaInput, Noticia.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(noticia);

    }

    @GetMapping
    public ResponseEntity<Page<Noticia>> getAll(Pageable pageable) {

        Page<Noticia> page = service.getPageable(pageable);

        return ResponseEntity.ok(page);
    }
}
