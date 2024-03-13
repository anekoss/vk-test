--liquibase formatted sql

--changeset anekoss:1
-- comment: create roles table
create table roles
(
    id       bigint generated always as identity primary key,
    name text not null unique
);
-- rollback DROP TABLE roles;