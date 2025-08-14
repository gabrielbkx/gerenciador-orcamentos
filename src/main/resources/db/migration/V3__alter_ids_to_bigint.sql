-- Migration para alterar IDs para BIGINT sem dropar sequências usadas

-- 1. Alterar tabela cliente
ALTER TABLE cliente
ALTER COLUMN id TYPE BIGINT;

-- Criar nova sequência bigserial para cliente.id (se não existir)
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relname = 'cliente_id_seq') THEN
CREATE SEQUENCE cliente_id_seq AS BIGINT START WITH 1;
END IF;
END$$;

-- Ajustar default para usar a sequência
ALTER TABLE cliente ALTER COLUMN id SET DEFAULT nextval('cliente_id_seq');

-- 2. Alterar tabela orcamento
ALTER TABLE orcamento
ALTER COLUMN id TYPE BIGINT;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relname = 'orcamento_id_seq') THEN
CREATE SEQUENCE orcamento_id_seq AS BIGINT START WITH 1;
END IF;
END$$;

ALTER TABLE orcamento ALTER COLUMN id SET DEFAULT nextval('orcamento_id_seq');

-- Ajustar foreign key cliente_id para BIGINT
ALTER TABLE orcamento
ALTER COLUMN cliente_id TYPE BIGINT;

-- 3. Alterar tabela item_orcamento
ALTER TABLE item_orcamento
ALTER COLUMN id TYPE BIGINT;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relname = 'item_orcamento_id_seq') THEN
CREATE SEQUENCE item_orcamento_id_seq AS BIGINT START WITH 1;
END IF;
END$$;

ALTER TABLE item_orcamento ALTER COLUMN id SET DEFAULT nextval('item_orcamento_id_seq');

-- Ajustar foreign key orcamento_id para BIGINT
ALTER TABLE item_orcamento
ALTER COLUMN orcamento_id TYPE BIGINT;
