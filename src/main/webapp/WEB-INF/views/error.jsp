
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <body  style="background-color: Turquoise;">
        <h1>${error_message}</h1>
        <p><a href="${pageContext.request.contextPath}/dashboard">Click here to go to main page</a></p>
    </body>
</html>