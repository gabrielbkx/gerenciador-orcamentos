package com.gerenciadororcamentos.service;

import com.gerenciadororcamentos.entity.Cliente;
import com.gerenciadororcamentos.exception.RegistroDuplicadoException;
import com.gerenciadororcamentos.exception.RegistronaoExisteException;
import com.gerenciadororcamentos.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Object cadastrar(Cliente dados) {

        if (clienteRepository.existsByEmail(dados.getEmail())) {
            return new RegistroDuplicadoException("De acordo com o email fornecido, esse cliente ja existe " +
                    "em nossa basse de dados");
        }
        return clienteRepository.save(dados);
    }


    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            throw new RegistronaoExisteException("Cliente não encontrado com o ID: " + id);
        }

        return cliente.get();
    }
}