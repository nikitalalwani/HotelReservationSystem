<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body  style="background-color: lightblue;">
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<a href="hotel.htm">Add Hotels</a>
	<br />
	<a href="${context}/viewAllBookings">View All Bookings</a>
	<br />
	<a href="${context}/logout">Logout</a>

</body>
</html>