
$(function(){
	$("select").on('change', function() {
		
		$.post("readServlet", {count:$(this).val()},  function(responseText) {
			$('#ajaxGetUserServletResponse').html(responseText);
		})
	});
});