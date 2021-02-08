-- таблица встреч и всех приглашенных гостей
CREATE TABLE guest_list
(
    id serial primary key,
    name_meeting_id int references meetings(id),
    name_member_id int references members(id),
    status_member_id int references status(id)
);
