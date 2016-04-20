<script src="js/calendar_kdg.js" type="text/javascript"></script>
<script src="js/jquery-1.12.0.min.js" type="text/javascript"></script>
<script src="js/jquery.maskedinput.js" type="text/javascript"></script>
<script src="js/check.js" type="text/javascript"></script>
<script src="js/hotkey.comma.js" type="text/javascript"></script>
<script src="js/addTable.js" type="text/javascript"></script>
<script src="js/check.leters.js" type="text/javascript"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>УОТЗ</title>
<link href="css/style.css" rel="stylesheet" type="text/css">

</head>

<body  bgcolor="#4a5770"; onkeyup="hotkey(event)">

<table width="690" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="main_border" >
<tr>
    <td><img src="img/header.gif" width="690" height="100"></td>
  </tr>

  <tr>
    <td><table width="690" border="0" cellspacing="0" cellpadding="0">
      <tr>

        <td width="182px" valign="top" class="left">
		
<p align="center" class="title">Навігація</p>
<div id="coolmenu">
<a href="/statistic/start">Головна</a>
<a href="/statistic/scouting">Радіорозвідки</a>
<a href="/statistic/award">Ухвали</a>
<a href="/statistic/traffic">Трафіки</a>
<a href="/statistic/report">Звіти</a>
</div>	
       </td>
<!--Підгрузка лівого блока!-->


        <td valign="top" class="pol" bgcolor="#ecf2fe"> 

<!--Підгрузка Головного блока-->


<jsp:include page="${block}" flush="true" />

<!--Підгрузка Головного блока-->
        
        </td>
      </tr>
    </table></td>
  </tr>
  			
  			
<!--Підгрузка нижнього блока!-->
  <tr>
    <td><img src="img/footer.gif" width="690" height="20"></td>
  </tr>
<!--Підгрузка нижнього блока!-->


</table>


</body>
</html>

<script>
	departamentsIds = new Array(4,5,32,52,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,32,65);
	erdrIds = new Array(25,28,15,1,9,10,11,12,13,14,7,16,17,18,8,19,1,2,21,22,23,24,1,26,27,15,0);
	function changeTest() {
		var idCrimeElement = document.getElementById("id_crime");
		var text = idCrimeElement.options[idCrimeElement.selectedIndex].text;
		var indexErdr = text.substring(8,10);
		var indexDepartament;
		for (var i = 0; i < erdrIds.length; i++) {
			if (erdrIds[i] == indexErdr) {
				indexDepartament = i;
			}
		}
		var departamentId = departamentsIds[indexDepartament];
		document.getElementById("id_department").value=departamentId;
	}
</script>