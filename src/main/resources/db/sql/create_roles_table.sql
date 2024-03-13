--liquibase formatted sql

--changeset anekoss:1
-- comment: create roles table
create table if not exists roles
(
    id       bigint generated always as identity primary key,
    roleType text not null unique
);
-- rollback DROP TABLE roles;