package com.gerenciadororcamentos.entity;


import com.gerenciadororcamentos.enums.StatusOrcamento;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private StatusOrcamento status;
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL)
    private List<ItemOrcamento> itens;

    // getters e setters
}
