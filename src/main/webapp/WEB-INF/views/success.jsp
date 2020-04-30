<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${message}</title>
    </head>
    <body  style="background-color: Turquoise;">
        <h1>${message}</h1><br>
        <p><a href="${pageContext.request.contextPath}/dashboard">Click here to go to main page</a></p>
      </body>
</html>