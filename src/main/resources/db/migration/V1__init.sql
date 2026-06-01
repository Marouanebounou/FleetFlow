create table users (
    id BIGSERIAL primary key,
    name varchar(255) not null,
    phone varchar(255) not null unique ,
    role varchar(255) not null default 'ROLE_CLIENT',
    password varchar(255) not null,
    email varchar(255) not null unique
)

create table clients(
    id BIGINT primary key,
    ville varchar(255),
    constraint fk_client_user foreign key  (id) references users(id) on delete cascade
)

create table drivers (
    id BIGINT primary key,
    driver_license varchar(255),
    is_available boolean not null default false,
    constraint fk_driver_user foreign key (id) references users(id) on delete cascade
)

create table vehicles(
    id BIGSERIAL primary key ,
    plate varchar(255) not null unique,
    capacity int not null
)