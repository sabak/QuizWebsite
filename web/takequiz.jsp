<%@ page import="ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.StatsManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Account" %>
<%@ page import="ge.freeuni.quizwebsite.model.PageType" %>
<%@ page import="ge.freeuni.quizwebsite.model.Quiz" %>
<%@ page import="java.util.List" %>
<%--
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
        <link rel="stylesheet" type="text/css" href="rules.css"/>
        <%
            String sPage = " no";
            String quiz_id = (String) request.getParameter("quiz");
            System.out.println("quvizicjs " + quiz_id);
            Integer Q_ID = Integer.parseInt(quiz_id);

            QuizManagerDAO qManager = (QuizManagerDAO) session.getServletContext().getAttribute(
                    QuizManagerDAO.ATTRIBUTE_NAME);
            StatsManagerDAO statsManager = (StatsManagerDAO) session.getServletContext().getAttribute(
                    StatsManagerDAO.ATTRIBUTE_NAME);

            Quiz q = qManager.getQuiz(Q_ID);

            if(q.getPageType().equals(PageType.ONE_PAGE))
                sPage = " yes";
        %>
    </head>

    <body>
        <div id="inputs" class="tn">
            Quiz Name: <%=q.getName()%> </br>
            Quiz Description: <%=q.getDescription()%> </br>
            Is in Random Order: <%=q.hasHasRandomOrder()%> </br>
            Is Immediately Corrected: <%=q.isImmediatelyCorrected()%> </br>
            Is On Single Page:<%=sPage%> </br>
            Highest Score: <%List<Account> gela = statsManager.getHighestPerformers(q, 1);
                if(gela != null){%>
                    <%=gela.get(0)%>
                <%} else {%>
                    none
            <% }%>
            </br>

            <button class="button sub" id="submit" onclick="location.href='actualQuiz.jsp'" style="margin-left: 25px;"> Take Quiz </button>
            </br>
            <button class="button pr" onclick="location.href='homePage.jsp'"> Cancel </button>
        </div>
    </body>
</html>
