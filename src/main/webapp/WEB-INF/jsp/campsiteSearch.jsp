<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Campsite Search Form</title>
	</head>
	<body>
		<h1>Reserve a Campsite at ${param.name}!</h1>
		<h2>Please input the desired range of dates you would like to camp in order to determine which Sites are available.</h2>
		
		<form action="campsiteSearchResults" method="GET">
			<label for="fromDate">Reservation Start Date:</label>
			<input type="date" name="fromDate" id="fromDate"/><br>
		
			<label for="toDate">Reservation End Date:</label>
			<input type="date" name="toDate" id="toDate"/><br>
			
			<input type="hidden" value="${param.campgroundId}" name="campgroundId" />
			<input type="hidden" value="${param.name}" name="name" />
			
			<input type="submit" value="Search for Campsites!" />
		</form>
	</body>
</html>