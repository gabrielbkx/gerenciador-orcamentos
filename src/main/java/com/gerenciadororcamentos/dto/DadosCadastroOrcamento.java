package com.gerenciadororcamentos.dto;

import com.gerenciadororcamentos.entity.Cliente;
import com.gerenciadororcamentos.entity.ItemOrcamento;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public record DadosCadastroOrcamento(@NotNull
                                     BigDecimal valor,
                                     @NotNull
                                     Cliente cliente,
                                     List<ItemOrcamento> itens) {
}
