CREATE TABLE something
(
    id BINARY(16) NOT NULL,
    CONSTRAINT pk_something PRIMARY KEY (id)
);

ALTER TABLE product
    ADD inventory_count INT NULL;

ALTER TABLE product
    MODIFY inventory_count INT NOT NULL;