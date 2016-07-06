<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/6/2016
  Time: 7:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Results</title>
        <link rel="stylesheet" type="text/css" href="rules.css"/>
        <%
            Integer k = (Integer) session.getAttribute("score");
        %>
    </head>
    <body>
        <%=k%>
    </body>
</html>
