-- создаем таблицы

-- создаем таблицу категории заявки
create table category
(
    id   serial primary key,
    name text
);

-- создаем таблицу состояние заявки
create table status
(
    id   serial primary key,
    name text
);

-- создаем таблицу роли
create table role
(
    id   serial primary key,
    name text
);

-- создаем таблицу права ролей
create table rules
(
    id   serial primary key,
    name text
);

/* создаем таблицу роли и права ролей
   таблица является дополнительной и служит
   для реализации связи many-to-many между таблицами
   role и rules */
create table role_rules
(
    id       serial primary key,
    role_id  int references role (id),
    rules_id int references rules (id)
);

/* создаем таблицу пользователи
   эта таблица содержит поле таблицы role */
create table users
(
    id      serial primary key,
    name    text,
    role_id int references role (id)
);

/* создаем таблицу заявок
   эта таблица содержит в себе поля таблиц:
   users, category, status */
create table item
(
    id          serial primary key,
    name        text,
    category_id int references category (id),
    status_id   int references status (id),
    user_id     int references users (id)
);

/* создаем таблицу комментарии к заявке
   эта таблица содержит поле таблицы item */
create table comments
(
    id      serial primary key,
    content text,
    item_id int references item (id)
);

/* создаем таблицу приложенные к заявке файлы
   эта таблица содержит поле таблицы item */
create table attached_files
(
    id      serial primary key,
    name    text,
    item_id int references item (id)
);
