<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
	<c:set var="context" value="${pageContext.request.contextPath}" />
	
<body style="background-image: url('${context}/resources/bg.jpg');" text="white">
	<form action="${context}/login" method="POST">
		<table border="1"  width="50%">
			<thead>
				<tr>
					<th colspan="2">Enter Information Here</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Username:</td>
					<td><input type="email" name="username" required="required" /><br></td>
				</tr>

				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" required="required" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Login"/></td>
				</tr>
			</tbody>
		</table>
	</form>
		<p>
		<a href="signup.htm">Click here to signup</a>
	</p>
</body>
</html>