CREATE DATABASE task
  WITH ENCODING='UTF8'
       OWNER=postgres
       CONNECTION LIMIT=-1;

\connect task

create sequence tasks_ids;

CREATE TABLE tasks
(
    id bigint primary key,
    name varchar(256),
    description varchar(256)
) ;