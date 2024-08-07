package br.com.syonet.newsletter.domain.service.impl;

import br.com.syonet.newsletter.domain.model.Cliente;
import br.com.syonet.newsletter.domain.repository.ClienteRepository;
import br.com.syonet.newsletter.domain.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    @Override
    public Page<Cliente> getListPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }


    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }
}
