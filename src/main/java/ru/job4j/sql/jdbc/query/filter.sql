-- все продукты с типом СЫР
select pp.name as название_продукта, t.name as  тип, pp.expired_data as срок_годности, pp.price as цена
from product as pp join type as t on pp.type_id = t.id
where t.name='СЫР';

-- все мороженные продукты
select pp.name as название_продукта, t.name as  тип, pp.expired_data as срок_годности, pp.price as цена
from product as pp join type as t on pp.type_id = t.id
where pp.name like'%мороженное%';

-- продукты, срок годности которых заканчивается в следующем месяце
select pp.name as название_продукта, t.name as  тип, pp.expired_data as срок_годности, pp.price as цена
from product as pp join type as t on pp.type_id = t.id
where expired_data between date_trunc('month', current_date) + interval '1 month'
  and date_trunc('month', current_date) + interval '2 month';

-- самый дорогой продукт
select pp.name as название_продукта, t.name as  тип, pp.expired_data as срок_годности, pp.price as цена
from product as pp join type as t on pp.type_id = t.id
where price = (select max(pp.price) from product pp);

-- все продукты определенного типа
select t.name as тип, count(t.name) as количество_продуктов
from product as pp join type as t on pp.type_id = t.id group by t.name;

-- все продукты с типом СЫР и МОЛОКО
select pp.name as название_продукта, t.name as  тип, pp.expired_data as срок_годности, pp.price as цена
from product as pp join type as t on pp.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

-- все продукты, которых осталось меньше 10 штук
select t.name as тип, count(t.name) as количество_продуктов
from product as pp join type as t on pp.type_id = t.id
group by t.name
having count(t.name) < 10;

-- все продукты
select pp.name as название_продукта, t.name as  тип, pp.expired_data as срок_годности, pp.price as цена
from product as pp join type as t on pp.type_id = t.id;
