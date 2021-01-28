-- Имена всех работников, которые не состоят в компании с id = 5
SELECT name, company_id
FROM person
WHERE company_id != 5;

-- Названия компании для каждого работника
SELECT p.name, c.name
FROM person p
         JOIN company c ON p.company_id = c.id;

-- Название компании с максимальным количеством человек + количество человек в этой компании
SELECT c.name as название_компании, count(p.name) as число_сотрудников
FROM person p
         JOIN company c ON p.company_id = c.id
GROUP BY c.name
ORDER BY count(p.name) DESC LIMIT 1;
