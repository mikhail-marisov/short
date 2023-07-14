--liquibase formatted sql

--changeset mikhail:1
CREATE TABLE IF NOT EXISTS links
(
    id SERIAL PRIMARY KEY,
    link VARCHAR(255),
    short_link VARCHAR(255),
    counts BIGINT,
    PRIMARY KEY (id)
    );

