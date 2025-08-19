package com.gerenciadororcamentos.controller;

import com.gerenciadororcamentos.dto.DadosAtualizacaoCliente;
import com.gerenciadororcamentos.dto.DadosCadastroCliente;
import com.gerenciadororcamentos.dto.DadosDetalhamentoCliente;
import com.gerenciadororcamentos.dto.DadosListagemDeClientes;
import com.gerenciadororcamentos.entity.Cliente;
import com.gerenciadororcamentos.repository.ClienteRepository;
import com.gerenciadororcamentos.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.net.URI;


@RequestMapping("/clientes")
@RestController
public class ClienteController {


    private ClienteService service;
    private ClienteRepository clienteRepository;

    public ClienteController(ClienteService service, ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.service = service;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody DadosCadastroCliente dados) {
        var cliente = new Cliente(dados);
        service.cadastrar(cliente);
        return ResponseEntity.created(URI.create("/clientes/" + cliente.getId()))
                .body(new DadosDetalhamentoCliente(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        var cliente = clienteRepository.findById(id).get();
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente.getId(),
                cliente.getNome(), cliente.getEmail(), cliente.getTelefone()));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemDeClientes>> listar(@PageableDefault(size = 10) Pageable paginacao) {
        var page = clienteRepository.findAll(paginacao).map(Cliente -> new DadosListagemDeClientes(
                Cliente.getNome(), Cliente.getEmail(), Cliente.getTelefone()
        ));

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //Regra de negocio: O cliente só pode ter o email e telefone atualizados, o nome não pode ser atualizado.
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPorId(@RequestBody DadosAtualizacaoCliente dados, @RequestParam Long id) {

        if (!clienteRepository.existsById(id)) {
            ResponseEntity.badRequest().build();
        }
        var clienteAtualizado = clienteRepository.findById(id).get();
        clienteAtualizado.setEmail(dados.email());
        clienteAtualizado.setTelefone(dados.telefone());
        clienteRepository.save(clienteAtualizado);
        return ResponseEntity.ok().build();

    }
}
