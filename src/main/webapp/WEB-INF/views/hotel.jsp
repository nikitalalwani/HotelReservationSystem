<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body  style="background-color: Turquoise;">
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<form:form action="${context}/addHotel" method="post"
		modelAttribute="hotel">

		<table>
			<tr>
				<td>Hotel Name:</td>
				<td><form:input path="hotelName" size="30" required="required" title="character between 3 to 10"/> <font
					color="red"><form:errors path="hotelName" /></font></td>
			</tr>
			<tr>
				<td>Cost per night:</td>
				<td><form:input path="costPerNight" size="30" required="required" type="number"/> <font
					color="red"><form:errors path="costPerNight" /></font></td>
			</tr>			
			<tr>
				<td>Number of Rooms:</td>
				<td><form:input path="numberOfRooms" size="30" required="required" type="number"/> <font
					color="red"><form:errors path="numberOfRooms" /></font></td>
			</tr>
			<tr>
				<td>Street:</td>
				<td><form:input path="street" size="30" required="required"
						type="street" />
					<form:errors path="street" /></td>
			</tr>
			<tr>
				<td>City:</td>
				<td><form:input path="city" size="30" required="required"
						type="city" /> <font color="red"><form:errors path="city" /></font></td>
			</tr>

			<tr>
				<td>State:</td>
				<td><form:input path="state" size="30" required="required" />
					<font color="red"><form:errors path="state" /></font></td>
			</tr>
			<tr>
				<td>Zip:</td>
				<td><form:input path="zip" size="30" required="required" type="number"/>
					<font color="red"><form:errors path="zip" /></font></td>
			</tr>

			<tr>
				<td>Phone:</td>
				<td><form:input path="phone" size="30" required="required" />
					<font color="red"><form:errors path="phone" /></font></td>
			</tr>
			<tr>
				<td>Rating:</td>
				<td><form:select path="rating">
						<form:option value="1">1</form:option>
						<form:option value="1.5">1.5</form:option>
						<form:option selected="selected" value="2.0">2.0</form:option>
						<form:option value="2.5">2.5</form:option>
						<form:option value="3.0">3.0</form:option>
						<form:option value="3.5">3.5</form:option>
						<form:option value="4.0">4.0</form:option>
						<form:option value="4.5">4.5</form:option>
						<form:option value="5.0">5.0</form:option>
					</form:select></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Add Hotel" /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>