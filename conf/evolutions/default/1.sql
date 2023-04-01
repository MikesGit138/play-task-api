
--- !Ups

-- apply changes
create table task (
  id                            integer auto_increment not null,
  task_title                    varchar(255),
  completed                     tinyint(1),
  constraint pk_task primary key (id)
);


 --- !Downs

-- drop all
drop table if exists task;

