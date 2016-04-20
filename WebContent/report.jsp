<script type="text/javascript">
jQuery(function($){
   $("#dateStart").mask("99/99/9999",{placeholder:"дд/мм/рррр"});
   $("#dateEnd").mask("99/99/9999",{placeholder:"дд/мм/рррр"});
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



<h2>Звіти</h2>
<form action="report" method="post" class="rf"><br>
	<table border="0" cellpadding="2" cellspacing="0" style="width: 480px">
		<tbody>
			${inputData}
		</tbody>
	</table>
</form>

<table id="placeData"  class="tftable" border="1">

	${reportRow}

</table>


