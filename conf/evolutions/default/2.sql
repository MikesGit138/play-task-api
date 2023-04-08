
--- !Ups

-- apply changes
create table user (
                      id                            integer auto_increment not null,
                      username                    varchar(255),
                      password                     varchar(255),
                      constraint pk_task primary key (id)
);


--- !Downs

-- drop all
drop table if exists user;

