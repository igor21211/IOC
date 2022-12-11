create database if not exists contacts;

create table if not exists Person(
                       id serial not null primary key ,
                       first_name varchar(50) not null ,
                       last_name varchar(50) not null ,
                       email varchar(50)not null ,
                       country varchar(50) not null ,
                       phone_number bigint not null
);
