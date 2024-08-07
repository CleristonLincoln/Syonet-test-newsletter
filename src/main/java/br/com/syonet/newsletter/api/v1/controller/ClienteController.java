package br.com.syonet.newsletter.api.v1.controller;

import br.com.syonet.newsletter.api.v1.input.ClienteInput;
import br.com.syonet.newsletter.api.v1.model.ClienteModel;
import br.com.syonet.newsletter.domain.model.Cliente;
import br.com.syonet.newsletter.domain.service.ClienteService;
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
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<ClienteModel> create(@RequestBody @Valid ClienteInput clienteInput) {

        Cliente cliente = service.save(mapper.map(clienteInput, Cliente.class));

        log.info("Criando novo cliente: {}", cliente);

        ClienteModel clienteModel = mapper.map(cliente, ClienteModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteModel);

    }

    @GetMapping
    public ResponseEntity<Page<ClienteModel>> getAll(Pageable pageable) {

        Page<Cliente> page = service.getListPageable(pageable);

        log.info("Lista de clientes total: {}", page.getTotalElements());


        List<ClienteModel> clienteModelList = new ArrayList<>();

        page.getContent().forEach(cliente -> clienteModelList.add(mapper.map(cliente, ClienteModel.class)));



        Page<ClienteModel> newPage = new PageImpl<>(clienteModelList, pageable, page.getSize());


        return ResponseEntity.ok(newPage);
    }
}
