CREATE TABLE "user" (
    id serial,
    firstname varchar(20) not null,
    email varchar(50) not null,
    lastname varchar(20) not null,
    primary key (id)
);