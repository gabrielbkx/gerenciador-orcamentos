CREATE TYPE role AS ENUM ('ADMIN', 'USER');

CREATE TABLE usuario (
                         id SERIAL PRIMARY KEY,
                         username VARCHAR(80) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         role role NOT NULL DEFAULT 'USER'
);
