package br.com.syonet.newsletter.domain.service.impl;

import br.com.syonet.newsletter.domain.exception.LinkInvalidException;
import br.com.syonet.newsletter.domain.model.Noticia;
import br.com.syonet.newsletter.domain.repository.NoticiaRepository;
import br.com.syonet.newsletter.domain.service.NoticiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class NoticiaServiceImpl implements NoticiaService {

    private final NoticiaRepository repository;

    @Override
    public Page<Noticia> getPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Noticia save(Noticia noticia) {
      //  validLink(noticia.getLink());
        return repository.save(noticia);
    }

    private void validLink(String link) {
        try {
            new URL(link).toURI();

        } catch (MalformedURLException | URISyntaxException e) {
            throw new LinkInvalidException();
        }
    }
}
