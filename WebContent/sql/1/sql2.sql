SELECT depart, crim, artic, SUM(traff) as ctraff, SUM(mon) as cmon, SUM(scout) as cscout 
from temp_report 
group by  depart, crim, artic
order by depart, crim