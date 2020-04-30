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
	<table border="1">

		<c:forEach var="reserve" items="${reservations}">
			<tr>
				<td><b>Reservation Number</b></td>
				<td><b>Checkin Date</b></td>
				<td><b>Checkout Date</b></td>
				<td><b>Booking Status</b></td>
				<td><b>Number of rooms booked</b></td>
				<td><b>Cost per room</b></td>
				<td><b>Total cost</b></td>
				<td><b>Username</b></td>
				<td><b>Hotel name</b></td>
								<td><b></b></td>
								<td><b></b></td>
				
			</tr>
			<tr>
				<td>${reserve.reservationNumber}</td>
				<td>${reserve.checkinDate}</td>
				<td>${reserve.checkoutDate}</td>
				<td>${reserve.bookingStatus}</td>
				<td>${reserve.numberOfRoomsToBook}</td>
				<td>${reserve.hotel.costPerNight}</td>
				<td>${reserve.totalCost}</td>
				<td>${reserve.customer.username}</td>
				<td>${reserve.hotel.hotelName}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>