--liquibase formatted sql

--changeset users:7
-- comment: insert user_roles table values
insert into users_roles(users_id, roles_id) values (1, 10);
insert into users_roles(users_id, roles_id) values (2, 1);
insert into users_roles(users_id, roles_id) values (2, 3);
insert into users_roles(users_id, roles_id) values (2, 5);
insert into users_roles(users_id, roles_id) values (3, 8);
insert into users_roles(users_id, roles_id) values (4, 5);
insert into users_roles(users_id, roles_id) values (5, 2);