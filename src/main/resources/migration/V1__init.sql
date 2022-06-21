CREATE TABLE IF NOT EXISTS users
(
    user_id   SERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS edge
(
    id             SERIAL PRIMARY KEY,
    first_user_id  SERIAL,
    second_user_id SERIAL,
    foreign key (first_user_id) references users (user_id),
    foreign key (second_user_id) references users (user_id)

);

INSERT INTO users (user_name)
VALUES ('Bob'),
       ('Mike'),
       ('Carmen'),
       ('Lola');

INSERT INTO edge (first_user_id, second_user_id)
VALUES (1, 2),
       (1, 3),
       (1, 4),
       (2, 3);



