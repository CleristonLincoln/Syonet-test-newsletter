package br.com.syonet.newsletter.domain.service.impl;

import br.com.syonet.newsletter.domain.exception.LinkInvalidException;
import br.com.syonet.newsletter.domain.model.Noticia;
import br.com.syonet.newsletter.domain.repository.NoticiaRepository;
import br.com.syonet.newsletter.domain.service.NoticiaService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NoticiaServiceImpl implements NoticiaService {

    private final NoticiaRepository repository;

    @Override
    public Page<Noticia> getListPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Noticia save(Noticia noticia) {
        validarLink(noticia.getLink());
        return repository.save(noticia);
    }

    private void validarLink(String link) {
        UrlValidator urlValidator = new UrlValidator();

        if (Objects.nonNull(link) && !urlValidator.isValid(link))
            throw new LinkInvalidException();
    }

    @Override
    public List<Noticia> noticiasNaoEnviadas() {
        return repository.listarNoticiasNaoEnviadas();
    }
}
