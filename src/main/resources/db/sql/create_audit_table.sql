--liquibase formatted sql

--changeset users:4
-- comment: insert roles table values
create table audit
(
    id              bigint generated always as identity primary key,
    timestamp       timestamp,
    username        text,
    hasAccess       bool,
    requestParams   text,
    executionTime   bigint,
    responseCode    int,
    responseMessage text,
    uri             text
);
-- rollback DROP TABLE audit;