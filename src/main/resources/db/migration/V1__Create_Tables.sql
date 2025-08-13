CREATE TABLE cliente (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         email VARCHAR(150) NOT NULL UNIQUE,
                         telefone VARCHAR(20)
);

CREATE TYPE status_orcamento AS ENUM ('RASCUNHO', 'ENVIADO', 'APROVADO', 'REJEITADO');

CREATE TABLE orcamento (
                           id SERIAL PRIMARY KEY,
                           data DATE NOT NULL,
                           status status_orcamento NOT NULL DEFAULT 'RASCUNHO',
                           valor_total NUMERIC(15,2) NOT NULL DEFAULT 0,
                           cliente_id BIGINT NOT NULL REFERENCES cliente(id) ON DELETE CASCADE
);

CREATE TABLE item_orcamento (
                                id SERIAL PRIMARY KEY,
                                descricao VARCHAR(255) NOT NULL,
                                quantidade INT NOT NULL,
                                preco_unitario NUMERIC(15,2) NOT NULL,
                                orcamento_id BIGINT NOT NULL REFERENCES orcamento(id) ON DELETE CASCADE
);
