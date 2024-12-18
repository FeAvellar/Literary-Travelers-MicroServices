CREATE TABLE tb_editions (
    id BIGSERIAL PRIMARY KEY,
    book_id BIGINT NOT NULL,
    edition_number INT NOT NULL,
    publisher VARCHAR(255),
    isbn10 CHAR(10),
    isbn13 CHAR(13),
    publication_date DATE,
    pages INT,
    cover_photo TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES tb_books (id) ON DELETE CASCADE
);