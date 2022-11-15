CREATE TABLE cart (
    id              BIGINT PRIMARY KEY,
    product_name    VARCHAR,
    article_number  VARCHAR,
    amount          BIGINT,
    price_per_item  DOUBLE PRECISION,
    total_cost      DOUBLE PRECISION,
    description     VARCHAR,
    delivery_id     BIGINT,

    CONSTRAINT fk_delivery FOREIGN KEY (delivery_id) REFERENCES delivery (id)
);

CREATE SEQUENCE IF NOT EXISTS cart_sequence;