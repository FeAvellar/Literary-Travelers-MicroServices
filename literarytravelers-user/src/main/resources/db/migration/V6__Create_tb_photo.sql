CREATE TABLE tb_photo (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    img_perfil BYTEA,
    FOREIGN KEY (user_id) REFERENCES tb_user (id)
);
