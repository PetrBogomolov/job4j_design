-- запрос выдает список всех заявок и  количество участников со статусом accept
SELECT mt.name AS совещания, count(mb.name) AS количество_участников
FROM ((guest_list gl JOIN meetings mt ON gl.name_meeting = mt.id)
         JOIN members mb ON gl.name_member = mb.id
         JOIN status st ON gl.status_member = st.id)
GROUP BY mt.name, st.name
HAVING st.name = 'accept';

-- запрос выдает список совещаний, на которые не было ни одного приглашения
SELECT m.name AS совещания
FROM guest_list gl
         JOIN meetings m ON gl.name_meeting = m.id
WHERE name_member IS NULL;