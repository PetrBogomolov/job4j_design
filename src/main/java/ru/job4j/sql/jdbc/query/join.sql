create table country(
    id serial primary key,
    name text
);

create table company(
    id serial primary key,
    name text,
    country_id int references country (id)
);

insert into country(name) values ('USA');
insert into country(name) values ('Germany');
insert into country(name) values ('Japan');
insert into country(name) values ('Republic of Korea');
insert into country(name) values ('China');

insert into company(name, country_id) values ('Apple inc', 1);
insert into company(name, country_id) values ('Microsoft', 1);
insert into company(name, country_id) values ('Amazon', 1);
insert into company(name, country_id) values ('Alphabet Inc', 1);
insert into company(name, country_id) values ('Deutsche Bank', 2);
insert into company(name, country_id) values ('Siemens AG', 2);
insert into company(name, country_id) values ('BMW', 2);
insert into company(name, country_id) values ('Bayer', 2);
insert into company(name, country_id) values ('Toyota', 3);
insert into company(name, country_id) values ('Sony', 3);
insert into company(name, country_id) values ('Honda', 3);
insert into company(name, country_id) values ('Panasonic', 3);
insert into company(name, country_id) values ('Samsung Electronics', 4);
insert into company(name, country_id) values ('Hyundai Motor', 4);
insert into company(name, country_id) values ('LG Electronics', 4);
insert into company(name, country_id) values ('KIA Motor', 4);
insert into company(name, country_id) values ('Huawei', 5);
insert into company(name, country_id) values ('Alibaba Group', 5);
insert into company(name, country_id) values ('Bank of China', 5);
insert into company(name, country_id) values ('Lenovo', 5);

select * from company as pp join country as p on pp.country_id = p.id;
select pp.name, p.name from company as pp join country as p on pp.country_id = p.id;
select pp.name, p.name from company as pp join country as p on pp.country_id = p.id
where country_id = 2 or country_id = 3;