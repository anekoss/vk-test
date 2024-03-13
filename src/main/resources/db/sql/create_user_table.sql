--liquibase formatted sql

--changeset users:3
-- comment: create roles table
create table if not exists users
(
    id       bigint generated always as identity primary key,
    name     text not null
);
-- rollback DROP TABLE users;