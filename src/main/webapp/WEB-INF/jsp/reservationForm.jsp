<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Site Reservation Form</title>
	</head>
	<body>
		<h1>Please input any additional required fields to finalize your Site reservation.</h1>
		
		<form action="reservationForm" method="POST">
			<table>
				<tr>
					<td><label for="name">Your Name:</label></td>
					<td><input type="text" name="name" id="reservationName"/></td>
				</tr>
				<tr>
					<td>Reservation Start Date:</td>
					<td>${reservation.fromDate}</td>
				</tr>
				<tr>
					<td>Reservation End Date:</td>
					<td>${reservation.toDate}</td>
				</tr>
				<tr>
					<td>Date Reservation Created:</td>
					<td>${reservation.createDate}</td>
				</tr>
			</table>
			
			<input type="submit" value="Submit your reservation!"/>
		</form>
	</body>
</html>