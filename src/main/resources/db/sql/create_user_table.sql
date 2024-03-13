--liquibase formatted sql

--changeset users:2
-- comment: create roles table
create table users
(
    id       bigint generated always as identity primary key,
    username text not null,
    password text not null
);
-- rollback DROP TABLE users;