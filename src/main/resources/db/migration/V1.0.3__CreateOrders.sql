CREATE TABLE Orders(
    id INT NOT NULL,
    userId serial references Users(id),
    bookId serial references Books(id),

    quantity INT NOT NULL,
    price NUMERIC(10, 2) NOT NULL,

    createdAt timestamp NOT NULL default now(),
    updatedAt timestamp NOT NULL default now(),

    PRIMARY KEY (id, userId, bookId)
);

CREATE SEQUENCE orders_id_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;