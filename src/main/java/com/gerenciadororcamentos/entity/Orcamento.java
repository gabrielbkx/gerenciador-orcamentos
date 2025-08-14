package com.gerenciadororcamentos.entity;


import com.gerenciadororcamentos.enums.StatusOrcamento;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private StatusOrcamento status;
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL)
    private List<ItemOrcamento> itens = new ArrayList<>();

    // Aualiza automaticamente o valor total do orçamento sempre que um item for adicionado ou removido
    @PrePersist
    @PreUpdate
    private void calcularValorTotal() {
        if (itens != null && !itens.isEmpty()) {
            this.valorTotal = itens.stream()
                    .map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            this.valorTotal = BigDecimal.ZERO;
        }
    }
}
