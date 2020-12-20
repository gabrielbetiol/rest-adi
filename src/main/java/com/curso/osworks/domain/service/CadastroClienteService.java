package com.curso.osworks.domain.service;

import com.curso.osworks.api.model.ClienteLogin;
import com.curso.osworks.domain.exception.NegocioException;
import com.curso.osworks.domain.model.Cliente;
import com.curso.osworks.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            throw new NegocioException("Já existe um cliente cadastrado com esse e-mail");
        }
        return clienteRepository.save(cliente);
    }

    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscar(Long clienteId) {
        return Optional.ofNullable(clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente nao encontrado")));
    }

    public Optional<Cliente> buscarPorEmail(String clienteEmail) {
        return Optional.ofNullable(clienteRepository.findByEmail(clienteEmail));
    }

    public boolean buscarPorId(Long clienteId) {
        return clienteRepository.existsById(clienteId);
    }
}
