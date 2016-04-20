SELECT c.number, c.date_registration, c.article, s.number_raport, s.date_raport
FROM crime c
JOIN scouting s on c.id = s.id_crime
WHERE number = ?