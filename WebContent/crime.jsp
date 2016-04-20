<script src="js/calendar_kdg.js" type="text/javascript"></script>
<script src="js/jquery-1.12.0.min.js" type="text/javascript"></script>
<script src="js/jquery.maskedinput.js" type="text/javascript"></script>
<script src="js/check.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery(function($){
   $("#date_registration").mask("99/99/9999",{placeholder:"дд/мм/рррр"});
   $("#date_crime").mask("99/99/9999",{placeholder:"дд/мм/рррр"});
   $("#article").mask("999-9",{placeholder:"ст.-ч"});



});
</script>

<!--Reload parent page-->
<script>
    window.onunload = refreshParent;
    function refreshParent() {
        window.opener.location.reload();
    }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<h2>Злочин</h2>
<form action="crime" method="post" class="rf"><br>
	<table border="0" cellpadding="2" cellspacing="0" style="width: 300px">
		<tbody>
			<tr>
				<td>Вид злочину:</td>
				<td>
					<select name="kind_crime" size="1">
						<option value="ЕРДР">ЕРДР</option>
						<option value="ОРС \"Захист\"">ОРС "Захист"</option>
						<option value="ОРС \"Розшук\"">ОРС "Розшук"</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>№ злочину:</td>
				<td><input name="number" type="text" size="44" class="rfield">
				</td>
			</tr>
			<tr>
				<td>Дата реєстрації:</td>
				<td><input id="date_registration" name="date_registration" type="text" size="44" class="rfield" onfocus="this.select();_Calendar.lcs(this)"
    onclick="event.cancelBubble=true;this.select();_Calendar.lcs(this)"></td>
			</tr>
			<tr>
				<td>Стаття:</td>
				<td><input id="article" name="article" type="text" size="44" class="rfield"></td>
			</tr>
			<tr>
				<td>Дата злочину:</td>
				<td><input id="date_crime" name="date_crime" type="text" size="44" class="rfield" onfocus="this.select();_Calendar.lcs(this)"
    onclick="event.cancelBubble=true;this.select();_Calendar.lcs(this)"></td>
			</tr>
			<tr>
				<td>Фабула злочину:</td>
				<td><textarea name="story" rows="4" cols="46" type="text" size="44" class="rfield"></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input name="save" class="btn_submit disabled" type="submit" value="Зберегти">
				</td>
			</tr>
		</tbody>
	</table>
</form>

  
</body>
</html>