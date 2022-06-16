--liquibase formatted sql

--changeset evgeniy:1
CREATE TABLE IF NOT EXISTS users
(
    id        BIGSERIAL PRIMARY KEY,
    login     VARCHAR(64) NOT NULL UNIQUE,
    password  VARCHAR(128),
    full_name VARCHAR(64)
);
--rollback DROP TABLE users;