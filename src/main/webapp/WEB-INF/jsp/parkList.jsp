<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/common/header.jsp" />

		<h2>Please choose from one of our excellent parks in order to begin the process of reserving a campsite.</h2>
	
		<div id="parkContainer">
			<c:forEach var="park" items="${parkList}">
				<div class="card">
					<c:url var="parkHref" value="/campgroundList">
						<c:param name="parkId">${park.parkId}</c:param>
						<c:param name="name">${park.name}</c:param>
					</c:url>
					<c:url var="parkImgSrc" value="img/${park.name}.jpg" />
					<img src="${parkImgSrc}" class="cardImage"/>
					<div class="description">
						<h3><a href="${parkHref}">${park.name} (${park.location})</a></h3>
						<p>${park.description}</p>
					</div>
				</div>	
			</c:forEach>
		</div>
	</body>
</html>