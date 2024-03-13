--liquibase formatted sql

--changeset users:5
-- comment: insert users table values
insert into users(username, password) values ('admin', '$2a$10$eDL0k3WnbL0BO4vdDEl7gu8sD2fc.Dvjf0HptLs/G9uWTCbMv8R4a');
insert into users(username, password) values ('user', '$2a$10$CBU2cocOR4QKbeWk.h7C8eDpM3wM6oaBHxqlBfuO6IEdx8fzSwU0C');
insert into users(username, password) values ('albums_moderator', '$2a$10$CBU2cocOR4QKbeWk.h7C8eDpM3wM6oaBHxqlBfuO6IEdx8fzSwU0C');
insert into users(username, password) values ('users_viewer', '$2a$10$CBU2cocOR4QKbeWk.h7C8eDpM3wM6oaBHxqlBfuO6IEdx8fzSwU0C');
insert into users(username, password) values ('posts_editor', '$2a$10$CBU2cocOR4QKbeWk.h7C8eDpM3wM6oaBHxqlBfuO6IEdx8fzSwU0C');