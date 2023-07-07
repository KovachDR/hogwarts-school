create table persons
(
    id                 serial,
    name               text    not null,
    age                integer not null,
    has_driver_license boolean not null,
    cars_id            integer references cars (id)
);

create table cars
(
    id    serial primary key,
    brand text    not null,
    model text    not null,
    price integer not null
);

insert into persons (name, age, has_driver_license, cars_id)
values ('Ivan Ivanov', 20, true, 1),
       ('Sergey Sergeev', 21, true, 2),
       ('Vladimir Vladimirov', 30, true, 3),
       ('Dima Dimov', 29, true, 1);

insert into cars(brand, model, price)
values ('Toyota','Corolla',2000000),
       ('Lada','Vesta',1000000),
       ('Reno','Logan',500000);



