create table company_employees(
                                  id serial primary key,
                                  name text,
                                  number_department int8,
                                  access_permission bool
);
insert into company_employees(name, number_department, access_permission) values ('Bogomolov Petr', '01', 'true');
select * from company_employees;
update company_employees set access_permission = 'false';
select * from company_employees;
delete from company_employees;
select * from company_employees;