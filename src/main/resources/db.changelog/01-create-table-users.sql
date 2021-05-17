create table users (
    id int primary key,
    login varchar(50) unique not null,
    email varchar(50) unique not null,
    firstName varchar(100) not null,
    lastName varchar(100) not null,
    password varchar(255) not null,
    phone varchar(15) not null,
    role varchar(10) not null
);