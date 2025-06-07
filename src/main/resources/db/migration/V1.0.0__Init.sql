CREATE TABLE Users(
    email text primary key,
    birthdate timestamp,
    createdAt timestamp default now(),
    updateAt timestamp default now()
);

CREATE TABLE Authors (
    id serial primary key,
    name text,
    surname text,
    birthdate timestamp,
    createdAt timestamp default now(),
    updateAt timestamp default now()
);


CREATE TABLE Books(
    id serial primary key,
    title text,
    author serial references Authors(id),
    createdAt timestamp default now(),
    updateAt timestamp default now()
);

