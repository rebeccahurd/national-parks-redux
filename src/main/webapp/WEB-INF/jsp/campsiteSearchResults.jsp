<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/common/header.jsp" />

		<c:choose>
			<c:when test="${empty siteList}">
				<p>Your search returned no available campsites for the dates provided! Try using broader search criteria.</p>
				<c:url var="campgroundHref" value="/campsiteSearch">
					<c:param name="campgroundId">${param.campgroundId}</c:param>
					<c:param name="name">${param.name}</c:param>
				</c:url>
				<a href="${campgroundHref}">Return to Reservation Search Form</a>
			</c:when>
			
			<c:otherwise>
				<h1>For the dates you provided, below are the Sites that are available.  Please choose one to continue on with your reservation.</h1>
				
				<c:forEach var="site" items="${siteList}">
					<table>
						<tr>
							<td>Site Number:</td>
							<td>${site.siteId}</td>
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
						
						<c:url var="reservationFormLink" value="/reservationForm">
							<c:param name="siteId">${site.siteId}</c:param>
						</c:url>
					</table>
					<a href="${reservationFormLink}">Reserve this campsite!</a>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</body>
</html>