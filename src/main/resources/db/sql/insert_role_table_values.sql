--liquibase formatted sql

--changeset users:6
-- comment: insert roles table values
insert into roles(name) values ('ROLE_POSTS_VIEWER');
insert into roles(name) values ( 'ROLE_POSTS_EDITOR');
insert into roles(name) values ( 'ROLE_ALBUMS_VIEWER');
insert into roles(name) values ( 'ROLE_ALBUMS_EDITOR');
insert into roles(name) values ( 'ROLE_USERS_VIEWER');
insert into roles(name) values ( 'ROLE_USERS_EDITOR');
insert into roles(name) values ( 'ROLE_POSTS_MODERATOR');
insert into roles(name) values ( 'ROLE_ALBUMS_MODERATOR');
insert into roles(name) values ( 'ROLE_USERS_MODERATOR');
insert into roles(name) values ( 'ROLE_ADMIN');