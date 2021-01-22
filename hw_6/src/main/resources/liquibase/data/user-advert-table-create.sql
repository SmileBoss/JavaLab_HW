create table adverts
(
    id               serial primary key,
    user_id          integer not null,
    name             varchar not null,
    description      varchar not null,
    foreign key (user_id) REFERENCES users (id_user)
);