
ALTER TABLE usuario
ALTER COLUMN id TYPE BIGINT;

ALTER SEQUENCE usuario_id_seq
    AS BIGINT;


SELECT setval('usuario_id_seq', COALESCE((SELECT MAX(id) FROM usuario), 1));


ALTER TABLE usuario
    ALTER COLUMN id SET DEFAULT nextval('usuario_id_seq');
