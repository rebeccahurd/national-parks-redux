<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<title>List of Campgrounds for ${park}</title>
	</head>
	<body>
		<h1>Please choose one of the options below to search for availability for
		the following campgrounds at ${param.name}</h1>
		
		<div id="campgroundContainer">
			<c:forEach var="campground" items="${campgroundList}">
				<c:url var="campgroundHref" value="/campsiteSearch">
					<c:param name="campgroundId">${campground.campgroundId}</c:param>
					<c:param name="name">${campground.name}</c:param>
				</c:url>
				
				<h3><a href="${campgroundHref}">${campground.name}</a></h3>
				<h4>Key Facts:</h4>
				<p>Months Open: ${campground.openFromMM} - ${campground.openToMM}</p>
				<p>Daily Fee: $${campground.dailyFee}0</p>
				
				<c:url var="campgroundImgSrc" value="img/${campground.name}.jpg" />
				<img src="${campgroundImgSrc}" />
				
				<a href="${campgroundHref}">Find Availability</a>
			</c:forEach>
		
		</div>
		
	</body>
</html>