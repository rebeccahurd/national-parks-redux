<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/common/header.jsp" />

		<h1>Reserve a Campsite at ${param.name}!</h1>
		<h2>Please input the desired range of dates you would like to camp in order to determine which Sites are available.</h2>
		
		<form action="campsiteSearchResults" method="GET" class="form">
			<label for="fromDate">* Reservation Start Date:</label>
			<input type="date" name="fromDate" id="fromDate"/><br>
		
			<label for="toDate">* Reservation End Date:</label>
			<input type="date" name="toDate" id="toDate"/><br>
			
			<label for="maxOccupancy">Max Occupancy:</label>
			<select name="maxOccupancy" id="maxOccupancy">
				<c:forEach begin="1" end="12" var="number">
					<c:choose>
						<c:when test="${number == 6}">
							<option selected="selected" value="${number}">${number}</option>
						</c:when>
						<c:otherwise>
							<option value="${number}">${number}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select><br>
			
			<label for="maxRvLength">Max RV Length (if applicable):</label>
				<select name="maxRvLength" id="maxRvLength">
					<option value="0">No RVs Permitted</option>
					<option value="20">20 Feet</option>
					<option value="35">35 Feet</option>
				</select><br>
				
			
			<input type="checkbox" value="true" name="utilities" />Supports Utility Hookups<br>
			
			<input type="checkbox" value="true" name="accessible" />Handicap Accessible<br>
			
			<input type="hidden" value="${param.campgroundId}" name="campgroundId" />
			<input type="hidden" value="${param.name}" name="name" />

			<input type="submit" value="Search for Campsites!" />
			
			<p> * Denotes Required Field Input </p>
		</form>
	</body>
</html>