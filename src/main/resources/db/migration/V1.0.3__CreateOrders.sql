CREATE TABLE Orders(
    id serial,
    userId serial references Users(id),
    bookId serial references Books(id),

    quantity INT NOT NULL,
    price NUMERIC(10, 2) NOT NULL,

    createdAt timestamp NOT NULL default now(),
    updatedAt timestamp NOT NULL default now(),

    PRIMARY KEY (id, userId, bookId)
);

