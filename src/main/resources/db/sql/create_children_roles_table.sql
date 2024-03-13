--liquibase formatted sql

--changeset anekoss:2
-- comment: create children_roles table
create table if not exists children_roles
(
    id                bigint generated always as identity primary key,
    parent_role_id    bigint references roles (id),
    children_roles_id bigint references roles (id) check (children_roles_id != parent_role_id)
);
-- rollback DROP TABLE children_roles;