package br.com.syonet.newsletter.api.v1.controller;

import br.com.syonet.newsletter.api.v1.input.NoticiaInput;
import br.com.syonet.newsletter.api.v1.model.NoticiaModel;
import br.com.syonet.newsletter.domain.model.Noticia;
import br.com.syonet.newsletter.domain.service.NoticiaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("noticias")
@RequiredArgsConstructor
public class NoticiaController {

    private final NoticiaService service;
    private final ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<Noticia>> getNotiviasNaoEnviadas() {

        List<Noticia> noticiasNaoEnviadas = service.noticiasNaoEnviadas();
        log.info("Listando noticias não enviadas");

        return ResponseEntity.ok(noticiasNaoEnviadas);
    }

    @PostMapping
    public ResponseEntity<NoticiaModel> create(@RequestBody @Valid NoticiaInput noticiaInput) {

        Noticia noticia = service.save(mapper.map(noticiaInput, Noticia.class));

        log.info("Criando nova noticia: {}", noticia);

        NoticiaModel noticiaModel = mapper.map(noticia, NoticiaModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(noticiaModel);
    }

}
