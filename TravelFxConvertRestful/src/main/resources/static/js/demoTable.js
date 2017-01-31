/**
 * http://usejsdoc.org/
 */
$(document).ready(function() {
    //var table = $('#example').DataTable();
 
	$('#displaypanel').show()
	$('#displaypanel2').hide()
	var table = $('#example').DataTable( {
        "paging":   false,
        "ordering": true,
        "info":     false
    } );
    
    $('#example tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );
 
    $('#button').click( function () {
        alert( table.rows('.selected').data().length +' row(s) selected' );
    } );
} );