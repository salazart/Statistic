<!--Скрипти для "Live search" в полі "Select" -->
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/jquery.searchabledropdown-1.0.8.min.js" type="text/javascript"></script>

<!--Script add rows in denamic table through hotkey "+" -->
<script type="text/javascript">
function hotkey(event) {
    var id = event.keyCode;
    if(window.event.keyCode==107)
    	{
    	addRowObjectTraffic();
    	}
}
	</script>

<!--використання полів select, як "Live search"-->	
	<script type="text/javascript">
		$(document).ready(function() {
			$("select[id='id_crime']").searchable();
		});
	</script>
	
<h2>Трафіки</h2>
<form action="traffic" method="post" class="rf"><br>
	<table border="0" cellpadding="2" cellspacing="0" style="width: 480px">
		<tbody>
			<tr>
			<tr>
				<td style="width: 10%;">Злочин:</td>
				<td width="10">
					<select id="id_crime" name="id_crime" onchange="changeTest()" size="1" class="rfield">
						${crime}
					</select>
				</td>
				<td>
					<button class="button" onClick="window.open('/statistic/crime','_blank','width=450,height=425,scrollbars=1');">Додати</button>
				</td>
			<tr>
				<td>Відділ:</td>
				<td colspan="2"><select id="id_department" name="id_department" size="1" class="rfield">${departament}</select></td>
			</tr>
			</tr>
	<!--Блок динамічної таблиці!-->
			<tr>
			<th colspan="3" align="left">
				<script src="addTable.js" type="text/javascript"></script>
				<table id="objectData"  class="tftable" border="1">
				
    				<th style="width: 60%;">Тип</th><th style="width: 20%;"><img src="img/operators.gif" width="150" height="30"><th style="width: 5%;"></th>
    				
    				${trafficRow}
   
    				<tr><input type="button" value="+" onclick="Javascript:addRowObjectTraffic()"></tr>
				</table>
			</th>
			</tr>
<!--кінець блока динамічної таблиці!-->							
			<tr>
				<td colspan="2">
					<button class="button" formmethod="GET" onClick="window.location='/statistic/traffic';">Новий</button>
					<input name="save" class="btn_submit disabled" type="submit" value=${saveButton}>
				</td>
				<td></td>
			</tr>
		</tbody>
	</table>
	${textResult}
</form>
