--liquibase formatted sql

--changeset users:3
-- comment: create user_roles table
create table users_roles
(
    id       bigint generated always as identity primary key,
    roles_id bigint references roles (id),
    users_id  bigint references users (id)
);
-- rollback DROP TABLE user_roles;