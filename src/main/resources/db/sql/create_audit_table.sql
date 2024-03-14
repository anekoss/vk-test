--liquibase formatted sql

--changeset users:4
-- comment: insert roles table values
create table audit
(
    id              bigint generated always as identity primary key,
    timestamp       timestamp,
    username        text,
    access       bool,
    params   text,
    response_code    int,
    response_message text,
    uri             text
);
-- rollback DROP TABLE audit;