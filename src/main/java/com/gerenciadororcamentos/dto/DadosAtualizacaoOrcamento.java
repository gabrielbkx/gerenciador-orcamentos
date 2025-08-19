package com.gerenciadororcamentos.dto;

import com.gerenciadororcamentos.entity.Cliente;
import com.gerenciadororcamentos.enums.StatusOrcamento;

import java.math.BigDecimal;

public record DadosAtualizacaoOrcamento(StatusOrcamento statusOrcamento, BigDecimal valorTotal,Cliente cliente) {
}
