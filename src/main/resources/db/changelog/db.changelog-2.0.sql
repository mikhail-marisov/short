--liquibase formatted sql

--changeset mikhail:1
CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);

