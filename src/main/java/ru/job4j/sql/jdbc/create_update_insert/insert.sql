-- заполняем таблицы

-- заполняем таблицу категории заявки
insert into category(name) values ('important');
insert into category(name) values ('minor');

-- заполняем таблицу статус заявки
insert into status(name) values ('done');
insert into status(name) values ('in process');
insert into status(name) values ('in queue');

-- заполняем таблицу роли
insert into role(name) values ('admin');
insert into role(name) values ('user');

-- заполняем таблицу права ролей
insert into rules(name) values ('read');
insert into rules(name) values ('write');
insert into rules(name) values ('execution');

-- заполняем таблицу роли и их права
insert into role_rules(role_id, rules_id) values (1, 1);
insert into role_rules(role_id, rules_id) values (1, 2);
insert into role_rules(role_id, rules_id) values (1, 3);
insert into role_rules(role_id, rules_id) values (2, 1);
insert into role_rules(role_id, rules_id) values (2, 2);

-- заполняем таблицу пользователи
insert into users(name, role_id) values ('user1', 1);
insert into users(name, role_id) values ('user2', 2);

-- заполняем таблицу заявки
insert into item(name, category_id, status_id, user_id)
values ('item1', 1, 1, 1);

-- заполняем таблицу комментарии к заявке
insert into comments (content, item_id) values ('', 1);

-- заполняем таблицу приложенные к заявке файлы
insert into attached_files(name, item_id) values ('', 1);