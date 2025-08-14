package com.gerenciadororcamentos.service;

import com.gerenciadororcamentos.entity.Orcamento;
import com.gerenciadororcamentos.repository.OrcamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoService {

    private final OrcamentoRepository orcamentoRepository;

    public OrcamentoService(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }

    public Orcamento salvarOrcamento(Orcamento orcamento) {
        // O valorTotal será calculado automaticamente pelo @PrePersist/@PreUpdate
        return orcamentoRepository.save(orcamento);
    }

    public Orcamento atualizarOrcamento(Orcamento orcamento) {
        // Também recalcula valorTotal automaticamente
        return orcamentoRepository.save(orcamento);
    }

    public Optional<Orcamento> buscarPorId(Long id) {
        return orcamentoRepository.findById(id);
    }

    public List<Orcamento> listarTodos() {
        return orcamentoRepository.findAll();
    }

    public void deletarPorId(Long id) {
        orcamentoRepository.deleteById(id);
    }
}
