<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <c:set var="context" value="${pageContext.request.contextPath}" />
    <body style="background-image: url('${context}/resources/bg.jpg');" text="white">
	<form:form action="${context}/register" method="post" modelAttribute="customer">

		<table>
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" required="required"
						pattern="[A-Za-z]{3,10}" title="character between 3 to 10" /> <font
					color="red"><form:errors path="firstName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" required="required"
						pattern="[A-Za-z]{3,10}" title="character between 3 to 10" /> <font
					color="red"><form:errors path="lastName" /></font></td>
			</tr>
			<tr>
				<td>Email Id:</td>
				<td><form:input path="username" size="30" required="required"
						type="email" /> <font color="red"><form:errors
							path="username" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30"
						required="required" /> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Register User" /></td>				
			</tr>
		</table>
		<p><a href="${pageContext.request.contextPath}/">Click here to Login</a></p>
	</form:form>
    </body>
</html>