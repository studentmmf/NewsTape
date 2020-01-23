$(function(){
    	$("select").on('change', function() {
        	alert($(this).val());
        	$.ajax({
                type: 'POST',
                url: 'readServlet',
                data: ({count:$(this).val()}),
                success: function(){
                    alert('Отправлено');
                }
            });
        });
    });