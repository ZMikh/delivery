CREATE TABLE delivery (
    id              BIGINT PRIMARY KEY,
    state           VARCHAR,
    delivery_time   TIMESTAMP,
    description     VARCHAR,
    address         VARCHAR,
    is_pick_up      BOOLEAN
);

CREATE SEQUENCE IF NOT EXISTS delivery_sequence;