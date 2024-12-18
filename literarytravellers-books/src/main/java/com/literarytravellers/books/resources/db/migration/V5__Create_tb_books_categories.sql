CREATE TABLE tb_books_categories (
    book_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, category_id),
    CONSTRAINT fk_book_in_categories FOREIGN KEY (book_id) REFERENCES tb_books (id) ON DELETE CASCADE,
    CONSTRAINT fk_category_in_books FOREIGN KEY (category_id) REFERENCES tb_categories (id) ON DELETE CASCADE
);
