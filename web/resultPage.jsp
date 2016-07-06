<%@ page import="ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Account" %>
<%@ page import="ge.freeuni.quizwebsite.model.QuizResult" %>
<%@ page import="ge.freeuni.quizwebsite.model.Quiz" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.HistoryManagerDAO" %><%--
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
        <p class="tb">
            You have Scored </br>
            <%=k%>  </br>
            out of </br>
            <%=session.getAttribute("max")%> </br>
            time taken: </br>
            <%=session.getAttribute("timetaken")%> milliseconds </br>
        </p>

        <button class="button pr" onclick="location.href='homePage.jsp'"> Return </button>
    </body>
</html>
