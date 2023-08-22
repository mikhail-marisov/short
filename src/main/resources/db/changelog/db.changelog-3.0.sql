--liquibase formatted sql

--changeset mikhail:1
INSERT INTO users (name, password, role)
VALUES ('admin', '$2a$12$GpikR6ytCxhQpWdGU4ErreforKeNPqjGlN.jdCMZ4hXYOyA39IMs2', 'admin'),
       ('user', '$2a$12$XTI9pS9hOWP0T8rZ.zKN1ezk1wJeA5Dg/zx.CsgvMP8mzmXpUJY/e', 'user');