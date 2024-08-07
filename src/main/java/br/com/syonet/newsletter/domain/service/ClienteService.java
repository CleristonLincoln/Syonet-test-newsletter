package br.com.syonet.newsletter.domain.service;

import br.com.syonet.newsletter.domain.model.Cliente;

import java.util.List;

public interface ClienteService extends BaseService<Cliente> {
    List<Cliente> findAll();
}
