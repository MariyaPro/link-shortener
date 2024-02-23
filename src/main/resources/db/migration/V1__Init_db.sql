create sequence hibernate_sequence start 1 increment 1;

create table link (
                         id int8 not null,
                         reference varchar(255),
                         primary key (id)
);
