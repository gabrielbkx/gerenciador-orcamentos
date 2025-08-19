package com.gerenciadororcamentos.dto;

import com.gerenciadororcamentos.entity.Cliente;
import com.gerenciadororcamentos.entity.ItemOrcamento;
import com.gerenciadororcamentos.entity.Orcamento;
import com.gerenciadororcamentos.enums.StatusOrcamento;
import java.math.BigDecimal;
import java.util.List;

public record DadosDetalhamentoOrcamento(Long id, StatusOrcamento status,BigDecimal valor, Cliente cliente, List<ItemOrcamento> itens) {


    public DadosDetalhamentoOrcamento(Orcamento orcamento) {
        this(orcamento.getId(),
             orcamento.getStatus(),
             orcamento.getValorTotal(),
             orcamento.getCliente(),
             orcamento.getItens());
    }
}
