
<%@page import="java.util.ArrayList"%>
<%@page import="edu.cmu.model.Person"%>

<!DOCTYPE html>
<html>
<head>
<title>Locations of Contacts</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">
<style>
html,body,#map-canvas {
	margin: 0;
	padding: 0;
	height: 100%;
}
</style>
<link href="stylesheets/bootstrap.min.css" rel="stylesheet"
	media="screen">
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB9dFVcs4IwfOuEdsHQRYJlyc-LixLfVRQ&v=3.exp&sensor=false"></script>
<script>
	var map;
	var geocoder;

	function initialize() {

		var mapOptions = {
			zoom : 2,
			center : new google.maps.LatLng(32.797, -6.19),
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);

		var windowHeight = screen.availHeight / 4 * 3 + "px";
		document.getElementById('map-canvas').style.height = windowHeight;
		document.getElementById('name-content').style.height = windowHeight;
	}

	function addmarker(id, location) {

		if (document.getElementById(id).getAttribute('value') == 'false')
			document.getElementById(id).setAttribute('value', 'true');
		else
			return;

		geocoder = new google.maps.Geocoder();
		geocoder.geocode({
			'address' : location
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				var marker = new google.maps.Marker({
					map : map,
					position : results[0].geometry.location
				});
			} else {
			}
		});
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<script src="scripts/bootstrap.min.js"></script>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<div class="container">
		<div class="navbar navbar-inverse navbar-static-top">
			<div class="navbar-inner">
				<ul class="nav">
					<li class=""><a class="brand" href="#">Locations of
							Contacts</a></li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span2" id="name-content">
			<p>Click to show location</p>
				<%
					ArrayList<Person> persons = (ArrayList<Person>) request
							.getAttribute("persons");
					for (int i = 0; i < persons.size(); i++) {
				%>
				<button class="btn btn-small btn-info" type="button"
					onclick="addmarker('<%=persons.get(i).getId()%>','<%=persons.get(i).getLocation()%>')"><%=persons.get(i).getFirstName() + " "
						+ persons.get(i).getLastName()%></button>
				<input type="hidden" id="<%=persons.get(i).getId()%>" value="false" /><br/>
				<%
					}
				%>
			</div>
			<div class="span10" id="map-canvas"></div>
		</div>
	</div>

</body>
</html>