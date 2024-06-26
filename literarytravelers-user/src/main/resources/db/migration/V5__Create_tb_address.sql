CREATE TABLE tb_address (
    id BIGSERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    country VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    district VARCHAR (255) NOT NULL,
    street VARCHAR (255) NOT NULL,
    number VARCHAR (6) NOT NULL,
    zipcode VARCHAR (8) NOT NULL,

    FOREIGN KEY (user_id) REFERENCES tb_user (id)
);
