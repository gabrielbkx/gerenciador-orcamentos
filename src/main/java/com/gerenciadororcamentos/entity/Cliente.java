package com.gerenciadororcamentos.entity;


import com.gerenciadororcamentos.dto.DadosCadastroCliente;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Orcamento> orcamentos;


    public Cliente(DadosCadastroCliente dados) {

        this.id = dados.id();

        if (nome==null) {
            this.nome = dados.nome();
        }
        if (email==null) {
            this.email = dados.email();
        }
        if (telefone==null) {
            this.telefone = dados.telefone();
        }
    }

}
