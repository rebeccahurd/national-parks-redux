<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Reservation Confirmation</title>
	</head>
	<body>
		<h1>Thank you and congratulations!  You have successfully reserved a Site.  We look forward to having you at our park.</h1>
		
		<h3>Reservation Confirmation:</h3>
	
		<table>
			<tr>
				<td>Name:</td>
				<td>${reservation.name}</td>
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
			<tr>
				<td>Site Number:</td>
				<td>${site.siteNumber}</td>
			</tr>
			<tr>
				<td>Site Number:</td>
				<td>${site.siteNumber}</td>
			</tr>
			<tr>
				<td>Maximum Occupancy:</td>
				<td>${site.maxOccupancy}</td>
			</tr>
			<tr>
				<td>Handicap Accessibility:</td>
				<td>${site.accessible}</td>
			</tr>
			<tr>
				<td>Utility Hookup Options</td>
				<td>${site.utilities}</td>
			</tr>
			<tr>
				<td>Max RV Length (if applicable)</td>
				<td>${site.maxRvLength}</td>
			</tr>
		</table>
	</body>
</html>