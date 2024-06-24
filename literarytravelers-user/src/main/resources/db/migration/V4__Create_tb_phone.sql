CREATE TABLE tb_phone (
    id SERIAL PRIMARY KEY,
    phone_number VARCHAR(20) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tb_user(id)
);