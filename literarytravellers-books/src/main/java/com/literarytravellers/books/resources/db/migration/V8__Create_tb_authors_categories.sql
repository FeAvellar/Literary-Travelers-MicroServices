CREATE TABLE tb_authors_categories (
    author_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (author_id, category_id),
    CONSTRAINT fk_author_in_categories FOREIGN KEY (author_id) REFERENCES tb_authors (id) ON DELETE CASCADE,
    CONSTRAINT fk_category_in_authors FOREIGN KEY (category_id) REFERENCES tb_categories (id) ON DELETE CASCADE
);