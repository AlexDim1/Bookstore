-- Schema for Bookstore application
-- This runs first to create tables before data insertion

CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    description TEXT,
    short_description VARCHAR(500),
    page_count INTEGER,
    price DOUBLE PRECISION NOT NULL,
    times_bought INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    product_id BIGINT REFERENCES products(id) ON DELETE CASCADE,
    buy_amount INTEGER NOT NULL,
    buyer_name VARCHAR(255) NOT NULL,
    buyer_address VARCHAR(500) NOT NULL,
    total_price DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS reviews (
    id BIGSERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    content VARCHAR(1000) NOT NULL,
    product_id BIGINT REFERENCES products(id) ON DELETE CASCADE
);
