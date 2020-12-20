package com.curso.osworks.api.controller;

import com.curso.osworks.api.model.ClienteLogin;
import com.curso.osworks.domain.model.Cliente;
import com.curso.osworks.domain.service.CadastroClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private CadastroClienteService cadastroClienteService;

    @GetMapping
    public List<Cliente> listar() {
        return cadastroClienteService.listar();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = cadastroClienteService.buscar(clienteId);

        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/login/{clienteEmail}")
    public ResponseEntity<Cliente> login(@PathVariable String clienteEmail) {
        Optional<Cliente> cliente = cadastroClienteService.buscarPorEmail(clienteEmail);

        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return cadastroClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId,
                                             @RequestBody Cliente cliente) {
        if (!cadastroClienteService.buscarPorId(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        cliente = cadastroClienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
        if (!cadastroClienteService.buscarPorId(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        cadastroClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }
}
