CREATE TABLE tb_books_authors (
    book_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, author_id),
    CONSTRAINT fk_book_in_authors FOREIGN KEY (book_id) REFERENCES tb_books (id) ON DELETE CASCADE,
    CONSTRAINT fk_author_in_books FOREIGN KEY (author_id) REFERENCES tb_authors (id) ON DELETE CASCADE
);