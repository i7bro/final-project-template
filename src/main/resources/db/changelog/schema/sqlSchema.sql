--liquibase formatted sql

--changeset semibratov_ilya:1
CREATE TABLE if not exists users (
    id          int AUTO_INCREMENT,
    login       varchar(50) not null,
    email       varchar(50) not null,
    first_name   varchar(100) not null,
    last_name    varchar(100) not null,
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
    direction     VARCHAR(50) NOT NULL,
    route         INT NOT NULL,
    cost          INT NOT NULL,
    notice        VARCHAR(255) NULL,
    PRIMARY KEY (id),
    UNIQUE(title)
);

CREATE TABLE IF NOT EXISTS trips (
    id INT AUTO_INCREMENT,
    tour_id INT NOT NULL,
    free_spots INT NOT NULL,
    arrive_date DATETIME NOT NULL,
    instructors JSON NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (tour_id)
        REFERENCES tours(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS usersRequests (
    id INT AUTO_INCREMENT,
    user_id INT NOT NULL,
    trip_id INT NOT NULL,
    state VARCHAR(50) NOT NULL DEFAULT 'UNCONFIRMED',
    PRIMARY KEY(id),
    FOREIGN KEY(trip_id)
        REFERENCES trips(id)
        ON DELETE CASCADE,
    FOREIGN KEY(user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

--changeset semibratov_ilya:2
INSERT INTO users (login, email, first_name, last_name, password, phone, role)
VALUES ('1', '1', 'Ilya', 'none', '$2y$12$2FEF0d6h1rySif7BHoGY0el3j0Ekstn7GmEVqzMKmdORp8fezcfpG', '1', 'ADMIN');
INSERT INTO users (login, email, first_name, last_name, password, phone, role)
VALUES ('2', '1', 'Ilya', 'none', '$2y$12$2FEF0d6h1rySif7BHoGY0el3j0Ekstn7GmEVqzMKmdORp8fezcfpG', '1', 'USER');
INSERT INTO users (login, email, first_name, last_name, password, phone, role)
VALUES ('3', '1', 'Ilya', 'none', '$2y$12$2FEF0d6h1rySif7BHoGY0el3j0Ekstn7GmEVqzMKmdORp8fezcfpG', '1', 'USER');

INSERT INTO tours (title, description, direction, route, cost, notice)
VALUES ('Around Elbrus', 'some description', 'kavkaz', 35, 15000, 'none');
INSERT INTO tours (title, description, direction, route, cost, notice)
VALUES ('Mountain Iremel', 'ural', 'ural', 40, 7000, 'none');
INSERT INTO tours (title, description, direction, route, cost, notice)
VALUES ('Crimea dreams', 'walk near sea', 'crimea', 54, 10000, 'none');
INSERT INTO tours (title, description, direction, route, cost, notice)
VALUES ('test', 'test', 'test', 54, 10000, 'test');
INSERT INTO tours (title, description, direction, route, cost, notice)
VALUES ('test1', 'test1', 'test1', 54, 10000, 'test1');

INSERT INTO trips (tour_id, free_spots, arrive_date, instructors)
VALUES (1, 10, '2021-07-01 19:00', '{"main":"Semibratov Ilya", "helper":"Semibratova Irina"}');
INSERT INTO trips (tour_id, free_spots, arrive_date, instructors)
VALUES (1, 10, '2021-07-20 19:00', '{"main":"Petrov Petya", "helper":"Ivanov Ivan"}');
INSERT INTO trips (tour_id, free_spots, arrive_date, instructors)
VALUES (1, 13, '2021-09-01 21:00', '{"main":"Semibratov Ilya", "helper":"Petrov Petya"}');
INSERT INTO trips (tour_id, free_spots, arrive_date, instructors)
VALUES (2, 15, '2021-07-01 20:00', '{"main":"Petrov Petya", "helper":"Semibratova Irina"}');
INSERT INTO trips (tour_id, free_spots, arrive_date, instructors)
VALUES (2, 10, '2021-08-01 20:00', '{"main":"Petrov Petya", "helper":"Semibratova Irina"}');
INSERT INTO trips (tour_id, free_spots, arrive_date, instructors)
VALUES (3, 10, '2021-08-20 10:00', '{"main":"Semibratov Ilya", "helper":"Ivanv Ivan"}');
