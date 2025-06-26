CREATE TABLE Users(
    id serial primary key,
    email text UNIQUE NOT NULL,
    birthdate timestamp,
    surnme text NOT NULL,
    createdAt timestamp NOT NULL default now(),
    updatedAt timestamp NOT NULL default now()
);

CREATE TABLE Authors (
    id serial primary key,
    name text NOT NULL,
    surname text NOT NULL,
    birthdate timestamp,
    createdAt timestamp NOT NULL default now(),
    updatedAt timestamp NOT NULL default now()
);


CREATE TABLE Books(
    id serial primary key,
    title text NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    publication timestamp NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    author serial references Authors(id),
    createdAt timestamp NOT NULL default now(),
    updatedAt timestamp NOT NULL default now()
);

