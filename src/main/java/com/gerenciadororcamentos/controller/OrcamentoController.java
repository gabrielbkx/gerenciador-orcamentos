package com.gerenciadororcamentos.controller;


import com.gerenciadororcamentos.dto.*;
import com.gerenciadororcamentos.entity.Orcamento;
import com.gerenciadororcamentos.repository.OrcamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RequestMapping("/orcamentos")
@RestController
public class OrcamentoController {

    OrcamentoRepository orcamentoRepository;

    public OrcamentoController(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody DadosCadastroOrcamento dados) {
        var orcamento = new Orcamento(dados);
        orcamentoRepository.save(orcamento);
        return ResponseEntity.created(URI.create("/clientes/" + orcamento.getId()))
                .body(new DadosDetalhamentoOrcamento(orcamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        if (!orcamentoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        var orcamento = orcamentoRepository.findById(id).get();

        return ResponseEntity.ok(new DadosDetalhamentoOrcamento(orcamento.getId(),orcamento.getStatus(),
                orcamento.getValorTotal(),orcamento.getCliente(),orcamento.getItens()));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoOrcamento>> listar(@PageableDefault(size = 10) Pageable paginacao) {

        var page = orcamentoRepository.findAll(paginacao).map(o -> new DadosDetalhamentoOrcamento(o.getId(),
                o.getStatus(),
                o.getValorTotal(),o.getCliente(),o.getItens()));

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        orcamentoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPorId(@RequestBody DadosAtualizacaoOrcamento dados, @RequestParam Long id) {

        if (!orcamentoRepository.existsById(id)) {
            ResponseEntity.badRequest().build();
        }
        var orcamentoAtualizado = orcamentoRepository.findById(id).get();
        orcamentoAtualizado.setStatus(dados.statusOrcamento());
        orcamentoAtualizado.setValorTotal(dados.valorTotal());
        orcamentoAtualizado.setCliente(dados.cliente());
        orcamentoRepository.save(orcamentoAtualizado);
        return ResponseEntity.ok().build();

    }

}
