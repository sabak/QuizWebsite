<%@ page import="ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Account" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/27/2016
  Time: 5:50 PM

  this is where the activity of all of the user's friends is displayed
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
            String A_FNAME = (String)account.getFirstName();
            String A_LNAME = (String)account.getLastName();
            String A_MAIL = (String)account.getEmail();
            String a_name = (String)account.getHashedPassword();
            Integer a_id = (Integer) account.getId();
        %>
        <title>Friends' Activity</title>
        <link rel="stylesheet" type="text/css" href="rules.css"/>
    </head>

    <body>
        <div id="title">
            <h1>Your Friend's Activity</h1>
        </div>

        <button class="button pr" onclick="location.href='homePage.jsp'" style="position:absolute; bottom: +40px;"> Return </button>
    </body>
</html>
