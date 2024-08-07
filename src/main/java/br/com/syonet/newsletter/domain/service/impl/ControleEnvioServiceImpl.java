package br.com.syonet.newsletter.domain.service.impl;

import br.com.syonet.newsletter.domain.model.ControleEnvio;
import br.com.syonet.newsletter.domain.repository.ControleEnvioRepository;
import br.com.syonet.newsletter.domain.service.ControleEnvioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ControleEnvioServiceImpl implements ControleEnvioService {

    private final ControleEnvioRepository repository;
    @Override
    public Page<ControleEnvio> getListPageable(Pageable pageable) {
        return null;
    }

    @Override
    public ControleEnvio save(ControleEnvio controleEnvio) {
        return repository.save(controleEnvio);
    }
}
