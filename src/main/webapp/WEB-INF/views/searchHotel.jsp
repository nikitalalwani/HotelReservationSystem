<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body  style="background-color: Turquoise;">
	<table border="1" align="center" width="80%">
		<tr>
			<td><b>Hotel Name</b></td>
			<td><b>Hotel Address</b></td>
			<td><b>Rooms Available</b></td>
			<td><b>Cost per room</b></td>
			<td><b>Rating</b></td>
			<td><b></b></td>
		</tr>
		<c:forEach var="hotel" items="${hotels}">
			<tr>
				<td>${hotel.hotelName}</td>
				<td>${hotel.street}, ${hotel.city} ${hotel.state} ${hotel.zip}</td>
				<td>${hotel.numberOfRooms}</td>
				<td>${hotel.costPerNight}</td>
				<td>${hotel.rating}</td>

				<td><c:url var="url" value="/reserveHotel">
						<c:param name="hotelName" value="${hotel.hotelName}" />
					</c:url> <a href="${url}">Book Hotel</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>