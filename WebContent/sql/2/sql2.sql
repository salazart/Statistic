SELECT c.number, c.date_registration, c.article, o.[object], t.date_add
FROM crime c
JOIN traffic t on c.id = t.id_crime
JOIN [object] o on t.id = o.id_traffic
WHERE number = ?