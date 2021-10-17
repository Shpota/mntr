CREATE TABLE services
(
    id           VARCHAR(255) PRIMARY KEY,
    name         VARCHAR(255),
    url          TEXT,
    created_date BIGINT,
    status       VARCHAR(10)
);
