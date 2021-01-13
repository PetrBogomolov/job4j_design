create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values ('IPHONE 6', 17990);
insert into devices(name, price) values ('IPHONE 7', 32950);
insert into devices(name, price) values ('IPHONE 8', 41920);
insert into devices(name, price) values ('IPHONE xc', 46800);
insert into devices(name, price) values ('IPHONE x', 52550);
insert into devices(name, price) values ('IPHONE 11', 75900);
insert into devices(name, price) values ('IPHONE 12', 110900);

insert into people(name) values ('Petr');
insert into people(name) values ('Oleg');
insert into people(name) values ('Maria');
insert into people(name) values ('Olga');

insert into devices_people(device_id, people_id) values (1,1);
insert into devices_people(device_id, people_id) values (6,1);
insert into devices_people(device_id, people_id) values (7,2);
insert into devices_people(device_id, people_id) values (3,2);
insert into devices_people(device_id, people_id) values (2,3);
insert into devices_people(device_id, people_id) values (4,3);
insert into devices_people(device_id, people_id) values (7,4);
-- средняя цена устройств
select avg(price) from devices;
-- вывод таблицы devices_people
select d.name, d.price, p.name
from ((devices_people as pp join devices as d on pp.device_id = d.id)
join people as p on pp.people_id = p.id);
-- средняя цена устройств для каждого покупателя
select p.name, avg(d.price)
from ((devices_people as pp join devices as d on pp.device_id = d.id)
join people as p on pp.people_id = p.id)
group by p.name;
-- покупатели, средняя цена устройств которых выше 50000
select p.name, avg(d.price)
from ((devices_people as pp join devices as d on pp.device_id = d.id)
join people as p on pp.people_id = p.id)
group by p.name
having avg(d.price) > 50000;
