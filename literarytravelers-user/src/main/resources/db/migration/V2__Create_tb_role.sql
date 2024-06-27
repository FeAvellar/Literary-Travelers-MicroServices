CREATE TABLE tb_role (
    id BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT
);