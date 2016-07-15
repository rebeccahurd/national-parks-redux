<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<title>List of Parks</title>
	</head>
	<body>
		<h1>Please choose from one of our excellent parks in order to begin the process of reserving a Campground and Site.</h1>
	
		<div id="parkContainer">
			<c:forEach var="park" items="${parkList}">
				<div class="park">
					<c:url var="parkHref" value="/campgroundList">
						<c:param name="parkId">${park.parkId}</c:param>
						<c:param name="name">${park.name}</c:param>
					</c:url>
					<h3><a href="${parkHref}">${park.name} (${park.location})</a></h3>
					<%-- <c:url var="parkImgSrc" value="img/parks/${park.parkCode}.jpg" />
					<a href="${parkHref}"><img src="${parkImgSrc}" /></a> --%>
					<p>${park.description}</p>
					
					<c:url var="parkImgSrc" value="img/${park.name}.jpg" />
					<img src="${parkImgSrc}" />
				</div>	
			</c:forEach>
		</div>
		
	
	</body>
</html>