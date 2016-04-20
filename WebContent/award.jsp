<script type="text/javascript">
jQuery(function($){
   $("#date_add").mask("99/99/9999",{placeholder:"дд/мм/рррр"});
   $("#date_award").mask("99/99/9999",{placeholder:"дд/мм/рррр"});
   $("#date_validity_award").mask("99/99/9999",{placeholder:"дд/мм/рррр"});
   $("#date_submission").mask("99/99/9999",{placeholder:"дд/мм/рррр"});
});
	  </script>

<!--Script add rows in denamic table through hotkey "+" -->
<script type="text/javascript">
function hotkey(event) {
    var id = event.keyCode;
    if(window.event.keyCode==107)
  //if(window.event.ctrlKey && window.event.altKey && window.event.keyCode == 50) 
    	{
    	addRowObject();
    	}
}
	</script>

<!--Скрипти для "Live search" в полі "Select" -->
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/jquery.searchabledropdown-1.0.8.min.js" type="text/javascript"></script>

<!--використання полів select, як "Live search"-->	
	<script type="text/javascript">
		$(document).ready(function() {
			$("select[id='id_crime']").searchable();
		});
	</script>
	
<h2>Ухвали</h2>
<form action="award" method="post" class="rf"><br>
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
			<tr>
				<td>№ ухвали:</td>
				<td colspan="2"><input name="number_award" type="text" class="rfield" size="14" value=${number_award}>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;від:&nbsp;
					<input id="date_award" name="date_award" type="text" size="6" class="rfield" onfocus="this.select();_Calendar.lcs(this)"
    onclick="event.cancelBubble=true;this.select();_Calendar.lcs(this)" value=${date_award}>
    			</td>
			</tr>
    		<tr>
    			 <td>Надійшла:</td>
				 <td colspan="2"><input id="date_submission" name="date_submission" type="text" size="6" class="rfield" onfocus="this.select();_Calendar.lcs(this)"
    onclick="event.cancelBubble=true;this.select();_Calendar.lcs(this)" value=${date_submission}>
  
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Дія ухвали:
				<input id="date_validity_award" name="date_validity_award" type="text" size="6" class="rfield" onfocus="this.select();_Calendar.lcs(this)"
    onclick="event.cancelBubble=true;this.select();_Calendar.lcs(this)"  value=${date_validity_award}>
    		</tr>
			<tr>
				<td>Співробітник:</td>
				<td colspan="2"><select name="id_employe" size="1" class="rfield">${employe}</select></td>
			</tr>
			</tr>
	<!--Блок динамічної таблиці!-->
			<tr>
			<th colspan="3" align="left">
				<script src="addTable.js" type="text/javascript"></script>
				<table id="objectData"  class="tftable" border="1">
				
    				<th style="width: 50%;">Тип</th>
    				<th style="width: 35%;">
    					<img src="img/operators.gif" width="150" height="30">
    				</th>
    				<th style="width: 5%;">Відправлено</th>
    				<th style="width: 5%;"></th>
    					
    					${objectRow}
    					
    				<tr>
						<input type="button" value="+" onclick="Javascript:addRowObject()">
					</tr>
				</table>
			</th>
			</tr>
<!--кінець блока динамічної таблиці!-->							
			<tr>
				<td>Примітка:</td>
				<td colspan="2"><textarea name="note" rows="3" cols="50">${note}</textarea></td>
			</tr>

			<tr>
				<td></td>
				<td colspan="2">
					<button class="button" formmethod="GET" onClick="window.location='/statistic/award';">Новий</button>
					<input name="save" class="btn_submit disabled" type="submit" value="Зберегти">
				</td>
			</tr>
		</tbody>
	</table>
	${textResult}
</form>
