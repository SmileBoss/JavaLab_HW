create table icons
(
    user_id integer not null,
    icon    varchar not null,
    foreign key (user_id) REFERENCES users (id_user)
);