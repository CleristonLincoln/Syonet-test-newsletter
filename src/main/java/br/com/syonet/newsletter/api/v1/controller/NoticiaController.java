package br.com.syonet.newsletter.api.v1.controller;

import br.com.syonet.newsletter.api.v1.input.NoticiaInput;
import br.com.syonet.newsletter.api.v1.model.NoticiaModel;
import br.com.syonet.newsletter.domain.model.Noticia;
import br.com.syonet.newsletter.domain.service.NoticiaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("noticias")
@RequiredArgsConstructor
public class NoticiaController {

    private final NoticiaService service;
    private final ModelMapper mapper;


    @GetMapping
    public ResponseEntity<Page<NoticiaModel>> getAll(Pageable pageable) {

        Page<Noticia> page = service.getListPageable(pageable);

        log.info("Lista de noticias total: {}", page.getTotalElements());

        List<NoticiaModel> noticiaModel = new ArrayList<>();

        page.getContent().forEach(cliente -> noticiaModel.add(mapper.map(cliente, NoticiaModel.class)));

        Page<NoticiaModel> newPage = new PageImpl<>(noticiaModel, pageable, page.getSize());

        return ResponseEntity.ok(newPage);
    }

    @PostMapping
    public ResponseEntity<NoticiaModel> create(@RequestBody @Valid NoticiaInput noticiaInput) {

        Noticia noticia = service.save(mapper.map(noticiaInput, Noticia.class));

        log.info("Criando nova noticia: {}", noticia);

        NoticiaModel noticiaModel = mapper.map(noticia, NoticiaModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(noticiaModel);
    }


    @GetMapping("nao-enviadas")
    public ResponseEntity<List<Noticia>> listarNoticiasNaoEnviadas(){

        List<Noticia> noticiasNaoEnviadas = service.noticiasNaoEnviadas();
        log.info("Listando noticias não enviadas");

        return ResponseEntity.ok(noticiasNaoEnviadas);
    }
}
