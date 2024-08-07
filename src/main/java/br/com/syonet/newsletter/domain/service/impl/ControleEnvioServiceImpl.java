package br.com.syonet.newsletter.domain.service.impl;

import br.com.syonet.newsletter.domain.model.ControleEnvio;
import br.com.syonet.newsletter.domain.repository.ControleEnvioRepository;
import br.com.syonet.newsletter.domain.service.ControleEnvioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ControleEnvioServiceImpl implements ControleEnvioService {

    private final ControleEnvioRepository repository;

    public void save(ControleEnvio controleEnvio) {
        repository.save(controleEnvio);
    }
}
