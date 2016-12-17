CREATE DATABASE task
  WITH ENCODING='UTF8'
       OWNER=postgres
       CONNECTION LIMIT=-1;

create sequence tasks_ids;

CREATE TABLE public.tasks
(
    id bigint primary key,
    name varchar(256),
    description varchar(256)
) ;