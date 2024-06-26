CREATE TABLE tb_phone (
    id BIGSERIAL PRIMARY KEY,
    number VARCHAR(20) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tb_user(id)
);