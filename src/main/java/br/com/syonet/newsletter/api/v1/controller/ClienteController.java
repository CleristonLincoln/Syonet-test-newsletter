package br.com.syonet.newsletter.api.v1.controller;

import br.com.syonet.newsletter.api.v1.input.ClienteInput;
import br.com.syonet.newsletter.domain.model.Cliente;
import br.com.syonet.newsletter.domain.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody ClienteInput clienteInput) {

        Cliente cliente = service.save(mapper.map(clienteInput, Cliente.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);

    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> getAll(Pageable pageable) {

        Page<Cliente> page = service.getPageable(pageable);

        return ResponseEntity.ok(page);
    }
}
