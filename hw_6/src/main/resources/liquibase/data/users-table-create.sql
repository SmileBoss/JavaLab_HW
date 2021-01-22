CREATE TABLE users
(
    id_user  serial,
    email    char(50),
    nickname char(20),
    password varchar,
    PRIMARY KEY (id_user)
);