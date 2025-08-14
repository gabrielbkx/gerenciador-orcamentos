package com.gerenciadororcamentos.entity;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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


}
