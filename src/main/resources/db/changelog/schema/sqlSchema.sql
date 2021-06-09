--liquibase formatted sql

--changeset semibratov_ilya:1
CREATE TABLE if not exists users (
    id          int AUTO_INCREMENT,
    login       varchar(50) not null,
    email       varchar(50) not null,
    firstName   varchar(100) not null,
    lastName    varchar(100) not null,
    password    varchar(255) not null,
    phone       varchar(25) not null,
    role        varchar(10) not null,
    PRIMARY KEY(id),
    UNIQUE(login)
);

CREATE TABLE if not exists tours (
  id            INT AUTO_INCREMENT,
  title         VARCHAR(100) NOT NULL,
  description   VARCHAR(255) NOT NULL,
  route         INT NOT NULL,
  cost          INT NOT NULL,
  notice        VARCHAR(255) NULL,
  PRIMARY KEY (id),
  UNIQUE(title)
);

--changeset semibratov_ilya:2
INSERT INTO users (login, email, firstName, lastName, password, phone, role)
VALUES ('1', '1', 'Ilya', 'none', '$2y$12$2FEF0d6h1rySif7BHoGY0el3j0Ekstn7GmEVqzMKmdORp8fezcfpG', '1', 'ADMIN');
INSERT INTO users (login, email, firstName, lastName, password, phone, role)
VALUES ('2', '1', 'Ilya', 'none', '$2y$12$2FEF0d6h1rySif7BHoGY0el3j0Ekstn7GmEVqzMKmdORp8fezcfpG', '1', 'USER');

INSERT INTO tours (title, description, route, cost, notice)
VALUES ('Around Elbrus', 'some description', 35, 15000, 'none');
INSERT INTO tours (title, description, route, cost, notice)
VALUES ('Mountain Iremel', 'ural', 40, 7000, 'none');
INSERT INTO tours (title, description, route, cost, notice)
VALUES ('Crimea dreams', 'walk near sea', 54, 10000, 'none');