BEGIN;

-- Insere um usuário
INSERT INTO tb_user (username, password, email, cpf, birth_date)
VALUES ('Fernanda Avellar', '123', 'nanda.fasp@gmail.com', '13976888745', '1991-07-31');

-- Insere uma role
INSERT INTO tb_role (role_name) 
VALUES ('administrator');

-- Insere um telefone associado ao usuário
INSERT INTO tb_phone (user_id, number) 
VALUES (currval('tb_user_id_seq'), '61998457021');

-- Insere um endereço associado ao usuário
INSERT INTO tb_address (user_id, country, state, city, district, street, number, zipcode)
VALUES (currval('tb_user_id_seq'), 'Brasil', 'DF', 'Brasília', 'Octogonal', 'AOS 01 - Bloco A', '309', '70660011');

-- Insere uma foto associada ao usuário
-- Aqui você precisa inserir a foto como um valor binário. Se você tiver o arquivo binário da foto, pode usar pg_read_binary_file
-- Neste exemplo, estou assumindo que você está inserindo uma foto como um valor de texto para ilustração
INSERT INTO tb_photo (user_id, img)
VALUES (currval('tb_user_id_seq'), 'iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII=');

-- Associa o usuário à role
INSERT INTO tb_user_roles (user_id, role_id) 
VALUES (currval('tb_user_id_seq'), currval('tb_role_id_seq'));

COMMIT;
