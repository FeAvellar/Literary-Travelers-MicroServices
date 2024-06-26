CREATE TABLE tb_photo (
    id BIGSERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    img BYTEA,
    FOREIGN KEY (user_id) REFERENCES tb_user (id)
);
