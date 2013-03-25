	$( document ).ready(function() {
	$(window).resize(function() {
		var h = $(window).height(), offsetTop = 20; // Calculate the top offset

		$('#map-canvas').css('height', (h - offsetTop));
	}).resize();
	}