create table if not exists users(
    id serial primary key,
    name varchar (250),
    password varchar (250),
    email varchar (250)
);
create table if not exists brands(
    id serial primary key,
    name varchar (250)
);
create table if not exists bodies(
    id serial primary key,
    name varchar (250)
);
create table if not exists advertisement(
    id serial primary key,
    description varchar (250),
    brand_id int references brands (id),
    bodies_id int references bodies (id),
    users_id int references users (id),
    sold boolean not null,
    created TIMESTAMP,
    photo boolean
);
create table if not exists photos(
    id serial primary key,
    photoName varchar (250),
    advertisement_id int references advertisement (id)
);
create table if not exists mark(
    id serial primary key,
    name varchar (250)
);