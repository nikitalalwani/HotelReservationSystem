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
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<form:form action="${context}/reserveHotel" method="post"
		modelAttribute="reservation">

		<table>
			<tr>
				<td>Hotel Name:</td>
				<td><form:input type="text" path="hotel" value="${hotel}" readonly="true"/></td>
			</tr>
			<tr>
				<td>Number Of Rooms to book:</td>
				<td><form:input type="text" path="numberOfRoomsToBook" required="required" pattern="\d+" title="Please enter a valid number of rooms"/></td>
			</tr>
			<tr>
				<td>Checkin Date:</td>
				<td><form:input type="text" path="checkinDate" pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))"
						required="required" /></td>
				<td>yyyy-mm-dd</td>
			</tr>
			<tr>
				<td>Checkout Date:</td>
				<td><form:input type="text"  path="checkoutDate"
						required="required" pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))"/></td>
				<td>yyyy-mm-dd</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Confirm" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>