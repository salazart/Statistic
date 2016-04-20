function addRowPlace() {
    var table = document.getElementById("placeData");
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    row.insertCell(0).innerHTML='<input name="placeName_' + rowCount + '" class="my-input-place" type="text">';
	var cell1 = row.insertCell(1);
	cell1.innerHTML='<input id="operator_' + rowCount + '" class="rfield" name="operatorVodafone_' + rowCount + '" type="checkbox" align="center" style="width: 26%;">\
    					 <input id="operator_' + rowCount + '" class="rfield" name="operatorKyivstar_' + rowCount + '" type="checkbox" align="center" style="width: 26%;">\
    				 	 <input id="operator_' + rowCount + '" class="rfield" name="operatorLifecell_' + rowCount + '" type="checkbox" align="center" style="width: 26%;">';
 
    cell1.className = "tdRow_"+ rowCount +" emptyCheckbox";
    row.insertCell(2).innerHTML='<input name="placeLac_' + rowCount + '" onkeyup="return proverka(this);" class="my-input-cid" type="text">';
    row.insertCell(3).innerHTML='<input name="placeCid_' + rowCount + '" id="placeCid_'+ rowCount +'" onkeyup="return proverka(this);" class="my-input-lac" type="text"">';
    row.insertCell(4).innerHTML= '<input type="button" type="text" value = "X" onClick="Javacsript:deleteRow(this)">';
     
    
    //automatic replacement "," on ", " 
			    $().ready(function() {
				
				var arr = new Array();
				arr[32] = ', ';
				arr[44] = ', ';
				arr[46] = ', ';

				$('#placeCid_' + rowCount).keypress(function(event){
					if( b = arr[event.which] ){
						event.preventDefault();
						var text = $( this ).val();
						$( this ).val( text.substring( 0, text.length ) + b );
					}
				})					
			    })
}

function addRowObject() {
    var table = document.getElementById("objectData");
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    row.insertCell(0).innerHTML='<input name="objectName_' + rowCount + '" class="my-object"  value="Моніторинг"\
    	onfocus="if (this.value == \'Моніторинг\') {this.value=\'\'}" onBlur="if (this.value == \'\') {this.value=\'Моніторинг\'}">';
	row.insertCell(1).innerHTML='<input name="operatorVodafone_' + rowCount + '" type="checkbox" align="center" style="width: 27%;">\
    					 <input name="operatorKyivstar_' + rowCount + '" type="checkbox" align="center" style="width: 27%;">\
    				 	 <input name="operatorLifecell_' + rowCount + '" type="checkbox" align="center" style="width: 27%;">';
	row.insertCell(2).innerHTML='<input name="sendObject_' + rowCount + '" class="my-checkbox" type="checkbox" align="center" value="true"/>';
    row.insertCell(3).innerHTML= '<input type="button" type="text" value = "X" onClick="Javacsript:deleteRow(this)">';
}

function addRowObjectTraffic() {
    var table = document.getElementById("objectData");
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    row.insertCell(0).innerHTML='<input name="objectName_' + rowCount + '" class="my-object"  value="Моніторинг"\
    	onfocus="if (this.value == \'Моніторинг\') {this.value=\'\'}" onBlur="if (this.value == \'\') {this.value=\'Моніторинг\'}">';
	row.insertCell(1).innerHTML='<input name="operatorVodafone_' + rowCount + '" type="checkbox" align="center" style="width: 27%;">\
    					 <input name="operatorKyivstar_' + rowCount + '" type="checkbox" align="center" style="width: 27%;">\
    				 	 <input name="operatorLifecell_' + rowCount + '" type="checkbox" align="center" style="width: 27%;">';
    row.insertCell(2).innerHTML= '<input type="button" type="text" value = "X" onClick="Javacsript:deleteRow(this)">';
}

function deleteRow(obj) {
    var index = obj.parentNode.parentNode;
    var table = index.parentNode;
    table.removeChild(index);
}
