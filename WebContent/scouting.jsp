<script type="text/javascript">
jQuery(function($){
   $("#date_add").mask("99/99/9999",{placeholder:"дд/мм/рррр"});
   $("#date_raport").mask("99/99/9999",{placeholder:"дд/мм/рррр"});
   $("#date_dovidka").mask("99/99/9999",{placeholder:"дд/мм/рррр"});
});
	  </script>

<!--Скрипти для "Live search" в полі "Select" -->
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/jquery.searchabledropdown-1.0.8.min.js" type="text/javascript"></script>

<!--Скрипти для додавання динамічного поля натисканням кнопки "вниз"-->
<script src="js/hotkey.addTable.js" type="text/javascript"></script>

<!--використання полів select, як "Live search"-->	
	<script type="text/javascript">
		$(document).ready(function() {
			$("select[id='id_crime']").searchable();
		});
	</script>



<h2>Радіорозвідки</h2>
<form action="scouting" method="post" class="rf"><br>
	<table border="0" cellpadding="2" cellspacing="0" style="width: 480px">
		<tbody>
			<tr>
			<td style="width: 10%;">Злочин:</td>
			<td width="10">
				<select id="id_crime" name="id_crime" onchange="changeTest()" size="1" class="rfield">
					${crime}
				</select>
			</td>
				
			<td><button class="button" onClick="window.open('/statistic/crime','_blank','width=450,height=425,scrollbars=1');">Додати</button></td>
			</tr>
			<tr>
				<td>Відділ:</td>
				<td colspan="3"><select id="id_department" name="id_department" size="1" class="rfield" value=${id_department}>${departament}</select></td>
			</tr>
			<tr>
				<td>№ рапорту:</td>
				<td ><input name="number_raport" type="text" size="14" class="rfield" value=${number_raport}></td>
				<td style="width: 10%;">Дата рапорту:</td>
				<td><input id="date_raport" name="date_raport" type="text" value=${date_raport} size="6" class="rfield" onfocus="this.select();_Calendar.lcs(this)"
    onclick="event.cancelBubble=true;this.select();_Calendar.lcs(this)"></td>
			</tr>
			<tr>
				<td>№ довідки:</td>
				<td><input name="number_dovidka" type="text" size="14" value=${number_dovidka}></td>
				<td>Дата довідки:</td>
				<td><input id="date_dovidka" name="date_dovidka" type="text" value=${date_dovidka} size="6" onfocus="this.select();_Calendar.lcs(this)"
    onclick="event.cancelBubble=true;this.select();_Calendar.lcs(this)"></td>
			</tr>
			<tr>
				<td>Виконавець:</td>
				<td colspan="2"><select name="id_employe" size="1" class="rfield">${employe}</select></td>
			</tr>

	<!--Блок динамічної таблиці!-->
	
			<tr>
			<th colspan="4" align="left">
				<script src="addTable.js" type="text/javascript"></script>
				<table id="placeData"  class="tftable" border="1">
    				<th style="width: 40%;">Місце</th><th><img src="img/operators.gif" width="130" height="30"></th><th style="width: 10%;">LAC</th><th style="width: 22%;">CID</th><th></th>
					
					${placeRow}
    				
    				<tr><input type="button" id="add" value="+" onclick="Javascript:addRowPlace()"></tr>
				</table>
			</th>
			</tr>
<!--кінець блока динамічної таблиці!-->

			<tr>
				<td>Примітка:</td>
				<td colspan="4"><textarea name="note" rows="2" cols="45">${note}</textarea></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<button class="button" formmethod="GET" onClick="window.location='/statistic/scouting';">Новий</button>
					<input name="save" id="demo" class="btn_submit disabled" type="submit" value=${saveButton}>
					<input name="printReference" class="btn_submit disabled" type="submit" value="Довідка">
		
				</td>
			</tr>
		</tbody>
	</table>
	${textResult}
</form>

