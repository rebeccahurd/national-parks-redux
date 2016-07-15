<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css'>
		<title>National Park Geek Reservation Site</title>
		<c:url var="cssHref" value="/css/site.css" />
		<link rel="stylesheet" href="${cssHref}" />
	</head>
	<body>

<div class="header">
	<c:url value="/" var="homePageURL" />
	<a href="${homePageURL}"> 
	    <c:url value="img/logo.png" var="logoURL" />
	    <img src="${logoURL}" id="logo" />
	</a>
    
    <nav>
		<span class="dropbtn"><a href="${homePageURL}">Home</a></span>
		<div class="dropdown">
			<button class="dropbtn">View Campgrounds by Park</button>
			<div class="dropdown-content">
				<c:forEach var="park" items="${parks}">
					<c:url var="parkHref" value="/campgroundList">
						<c:param name="parkId">${park.parkId}</c:param>
						<c:param name="name">${park.name}</c:param>
					</c:url>
					<a href="${parkHref}">${park.name}</a>
				</c:forEach>
			</div>
		</div>
    </nav>
</div>
   
<div id="content">
    
    <h1><c:out value="${param.pageTitle}" /></h1>
    
    
    