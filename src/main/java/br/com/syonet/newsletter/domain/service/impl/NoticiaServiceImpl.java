package br.com.syonet.newsletter.domain.service.impl;

import br.com.syonet.newsletter.domain.model.Noticia;
import br.com.syonet.newsletter.domain.repository.NoticiaRepository;
import br.com.syonet.newsletter.domain.service.NoticiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticiaServiceImpl implements NoticiaService {

    private final NoticiaRepository repository;

    @Override
    public Page<Noticia> getPageable(Pageable pageable) {
        return null;
    }

    @Override
    public Noticia save(Noticia noticia) {
        return null;
    }
}
