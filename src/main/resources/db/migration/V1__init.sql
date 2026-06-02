create table users (
    id BIGINT AUTO_INCREMENT primary key,
    name varchar(255) not null,
    phone varchar(255) not null unique,
    role varchar(255) not null default 'ROLE_CLIENT',
    password varchar(255) not null,
    email varchar(255) not null unique
);

create table clients(
    id BIGINT primary key,
    ville varchar(255),
    constraint fk_client_user foreign key (id) references users(id) on delete cascade
);

create table drivers (
    id BIGINT primary key,
    driver_license varchar(255),
    is_available boolean not null default false,
    constraint fk_driver_user foreign key (id) references users(id) on delete cascade
);

create table vehicles(
    id BIGINT AUTO_INCREMENT primary key,
    plate varchar(255) not null unique,
    capacity int not null,
    status varchar(255) not null default 'MAINTENANCE'
);

create table livraison (
    id BIGINT AUTO_INCREMENT primary key,
    dateLivraison timestamp,
    start_adresse varchar(255),
    finish_adresse varchar(255),
    statut varchar(255),
    driver_id BIGINT,
    vehicle_id BIGINT,
    client_id BIGINT not null,
    constraint fk_livraison_driver foreign key (driver_id) references drivers(id),
    constraint fk_livraison_vehicle foreign key (vehicle_id) references vehicles(id),
    constraint fk_livraison_client foreign key (client_id) references clients(id)
);
