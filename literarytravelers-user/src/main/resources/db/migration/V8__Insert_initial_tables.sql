BEGIN;

-- Insere um usuário
INSERT INTO tb_user (fullname, login_name, password, email, cpf, birth_date)
VALUES ('Fernanda Carvalho dos Santos', 'FeAvellar', '123', 'nanda.fasp@gmail.com', '13976888745', '1991-07-31');

-- Associa o usuário a ROLE
DO $$
DECLARE
    user_id INT;
    role_id INT;
BEGIN
    SELECT currval('tb_user_id_seq') INTO user_id;
    SELECT id INTO role_id FROM tb_role WHERE role_name = 'ADMIN_ACCESS';

    -- Verifica se encontrou a role com nome 'ADMIN_ACCESS'
    IF role_id IS NULL THEN
        RAISE EXCEPTION 'Permissão ADMIN_ACCESS não encontrada!';
    END IF;

    -- Insere a associação do usuário com a role
    INSERT INTO tb_user_roles (user_id, role_id) 
    VALUES (user_id, role_id);
END $$;

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

COMMIT;
