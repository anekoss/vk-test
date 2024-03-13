--liquibase formatted sql

--changeset users:4
-- comment: create user_roles table
create table if not exists user_roles
(
    id       bigint generated always as identity primary key,
    role_id bigint references roles (id),
    user_id  bigint references users (id)
);
-- rollback DROP TABLE user_roles;