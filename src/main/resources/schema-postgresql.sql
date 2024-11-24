CREATE TABLE IF NOT EXISTS cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    email VARCHAR(50) UNIQUE,
    telefone VARCHAR(15),
    servico VARCHAR(20) CHECK (servico IN ('Corte', 'Barba', 'Corte + Barba')),
    horario TIME,
    dia DATE
);
