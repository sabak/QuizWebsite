<%@ page import="ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Account" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/27/2016
  Time: 5:50 PM

  this is where the user's activity will appear
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%
            AccountManagerDAO accManager = (AccountManagerDAO) session.getServletContext().getAttribute(
                    AccountManagerDAO.ATTRIBUTE_NAME);
			/*
        		list of variables:
        		details of the user (the one who's logged in)
    		 */
            Account account = (Account) accManager.getAccount((String) session.getAttribute("account_un"));
        %>
        <title>Your Activity</title>
        <link rel="stylesheet" type="text/css" href="rules.css"/>
    </head>

    <body>
        <div id="title">
            <h1>Your Activity</h1>

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
