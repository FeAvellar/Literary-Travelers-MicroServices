CREATE TABLE tb_editions_authors (
    edition_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (edition_id, author_id),
    CONSTRAINT fk_edition_in_authors FOREIGN KEY (edition_id) REFERENCES tb_editions (id) ON DELETE CASCADE,
    CONSTRAINT fk_author_in_editions FOREIGN KEY (author_id) REFERENCES tb_authors (id) ON DELETE CASCADE
);