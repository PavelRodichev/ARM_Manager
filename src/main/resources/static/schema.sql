CREATE DATABASE credit_management;

-- Подключение к базе
\c credit_management;

-- Таблица клиентов
CREATE TABLE clients
(
    id                   BIGSERIAL PRIMARY KEY,
    first_name           VARCHAR(100)       NOT NULL,
    last_name            VARCHAR(100)       NOT NULL,
    middle_name          VARCHAR(100),
    passport_series      VARCHAR(4)         NOT NULL,
    passport_number      VARCHAR(6)         NOT NULL,
    marital_status       VARCHAR(50)        NOT NULL,
    registration_address TEXT               NOT NULL,
    phone                VARCHAR(20) UNIQUE NOT NULL,
    employment_info      TEXT
);

-- Таблица кредитных заявок
CREATE TABLE credit_applications
(
    id               BIGSERIAL PRIMARY KEY,
    client_id        BIGINT         NOT NULL REFERENCES clients (id),
    desired_amount   NUMERIC(15, 2) NOT NULL,
    application_date TIM