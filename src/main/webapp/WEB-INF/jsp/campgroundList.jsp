<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/common/header.jsp" />

		<h2>Please choose one of the options below to search for availability for
		the following campgrounds at ${param.name}:</h2>
		
		<div id="campgroundContainer">
			<c:forEach var="campground" items="${campgroundList}">
				<div class="card">
					<c:url var="campgroundHref" value="/campsiteSearch">
						<c:param name="campgroundId">${campground.campgroundId}</c:param>
						<c:param name="name">${campground.name}</c:param>
					</c:url>
	
					<c:url var="campgroundImgSrc" value="img/${campground.name}.jpg" />
					<img src="${campgroundImgSrc}" class="cardImage"/>
					
					<div class="description">
						<h3><a href="${campgroundHref}">${campground.name}</a></h3>
						<h4>Key Facts:</h4>
						<p>Months Open: ${campground.openFromMM} - ${campground.openToMM}</p>
						<p>Daily Fee: $${campground.dailyFee}0</p>
						
						<div class="button">
							<a href="${campgroundHref}">Find Availability</a>
						</div>
					</div>
				</div>
			</c:forEach>
		
		</div>
		
	</body>
</html>