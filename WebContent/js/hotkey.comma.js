			    $().ready(function() {
				
				var arr = new Array();
				arr[32] = ', ';
				arr[44] = ', ';
				arr[46] = ', ';

				
//		var i=0;
		for (i = 0; i < 20; i++) 
		{ 
				$('#placeCid_'+i).keypress(function(event){

					if( b = arr[event.which] ){
						event.preventDefault();
						var text = $( this ).val();
						$( this ).val( text.substring( 0, text.length ) + b );
					}
				})
			}			
			    })
