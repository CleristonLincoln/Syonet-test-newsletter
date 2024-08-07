package br.com.syonet.newsletter.api.v1.controller;

import br.com.syonet.newsletter.api.v1.input.NoticiaInput;
import br.com.syonet.newsletter.domain.model.Noticia;
import br.com.syonet.newsletter.domain.service.NoticiaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("noticias")
@RequiredArgsConstructor
public class NoticiaController {

    private final NoticiaService service;

    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<Noticia> create(@RequestBody @Valid NoticiaInput noticiaInput) {

        Noticia noticia = service.save(mapper.map(noticiaInput, Noticia.class));

        log.info("Criando nova noticia: {}", noticia);

        return ResponseEntity.status(HttpStatus.CREATED).body(noticia);

    }

    @GetMapping
    public ResponseEntity<Page<Noticia>> getAll(Pageable pageable) {

        Page<Noticia> page = service.getListPageable(pageable);

        log.info("Lista de noticias total: {}", page.getTotalElements());

        return ResponseEntity.ok(page);
    }
}
