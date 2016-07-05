<%@ page import="ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.AchievementManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Account" %>
<%@ page import="ge.freeuni.quizwebsite.model.Achievement" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/27/2016
  Time: 5:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%
            AccountManagerDAO accManager = (AccountManagerDAO) session.getServletContext().getAttribute(
                    AccountManagerDAO.ATTRIBUTE_NAME);
            AchievementManagerDAO achManager = (AchievementManagerDAO) session.getServletContext().getAttribute(
                    AchievementManagerDAO.ATTRIBUTE_NAME);
			/*
        		list of variables:
        		details of the user (the one who's logged in)
    		 */

            Account account = (Account) accManager.getAccount(request.getParameter("account"));
        %>

        <title>Achievements</title>
        <link rel="stylesheet" type="text/css" href="rules.css"/>
    </head>

    <body>
        <div id="title">
            <h1>All Achievements</h1>
            </br>
            <%
                List<Achievement> achievements = achManager.getAchievements(account);
                for(int i=0; i<achievements.size(); i++){%>
                 <p class="tn" style="margin-bottom: 20px;">
                  Achievement type:  <% achievements.get(0).getAchievementType();%>
                  Date Unlocked:  <% achievements.get(i).getDateUnlocked();%>
                 </p>

            <%  }%>

        </div>

        <button class="button pr" onclick="location.href='homePage.jsp'" style="position:absolute; bottom: +40px;"> Return </button>
    </body>
</html>
