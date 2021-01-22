CREATE TABLE video
(
    id serial primary key,
    user_id integer not null ,
    url_video varchar not null ,
    name varchar not null ,
    icon varchar not null ,
    mini_description varchar not null ,
    description varchar not null,
    foreign key (user_id) REFERENCES users(id_user)
);