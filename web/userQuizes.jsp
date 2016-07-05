<%@ page import="ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Account" %>
<%@ page import="ge.freeuni.quizwebsite.model.Quiz" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/27/2016
  Time: 5:50 PM

   quizzes created by this user will appear here
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
            System.out.println("aeee " + request.getParameter("account"));
            Account account = (Account) accManager.getAccount(request.getParameter("account"));
            System.out.println("aeee " + account);
            QuizManagerDAO qManager = (QuizManagerDAO) session.getServletContext().getAttribute(
                    QuizManagerDAO.ATTRIBUTE_NAME);

            List<Quiz> createdQuizzes = qManager.getCreatedQuizzes(account, 30);
        %>
        <title>Your Quizes</title>
        <link rel="stylesheet" type="text/css" href="rules.css"/>
    </head>

    <body>
        <div id="title">
            <h1>Your Quizes</h1>
            <p class="tn">
                <%
                    if(createdQuizzes != null){
                        for(int i=0; i<createdQuizzes.size(); i=i+2){ %>
                            Quiz Name: <%=createdQuizzes.get(i).getName()%> </br>
                            Quiz Description: <%=createdQuizzes.get(i).getDescription()%> </br>
                            <button class="button sub" style="margin-bottom: 33px;" onclick="location.href='/takeQuiz?quiz=<%=createdQuizzes.get(i).getName()%>'"> take quiz </button></br>
                <%	}
                }
                %>
            </p>
        </div>

        <button class="button pr" onclick="location.href='homePage.jsp'" style="position:absolute; bottom: +40px;"> Return </button>
    </body>
</html>
