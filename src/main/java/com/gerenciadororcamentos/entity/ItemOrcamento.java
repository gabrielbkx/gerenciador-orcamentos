package com.gerenciadororcamentos.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class ItemOrcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private int quantidade;
    private BigDecimal precoUnitario;

    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    private Orcamento orcamento;

    // getters e setters
}
