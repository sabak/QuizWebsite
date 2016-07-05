<%@ page import="ge.freeuni.quizwebsite.model.Quiz" %>
<%@ page import="ge.freeuni.quizwebsite.model.Account" %><%--
  Created by IntelliJ IDEA.
  User: AVTO
  Date: 7/6/2016
  Time: 12:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>xax qvizs vaketeb</title>
    <%
        Quiz q = (Quiz) request.getAttribute("quiz");
        Account account = (Account) session.getAttribute("account");
    %>
</head>
<body>

</body>
</html>
