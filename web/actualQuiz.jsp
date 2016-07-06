<%@ page import="ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.PageType" %>
<%@ page import="ge.freeuni.quizwebsite.model.Question" %>
<%@ page import="ge.freeuni.quizwebsite.model.Quiz" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/6/2016
  Time: 3:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>quiz</title>
        <link rel="stylesheet" type="text/css" href="rules.css"/>
        <%
            String sPage = " no";
            String quiz_id = (String) request.getParameter("quiz");
            System.out.println("quvizicjs2 " + quiz_id);
            Integer Q_ID = Integer.parseInt(quiz_id);

            QuizManagerDAO qManager = (QuizManagerDAO) session.getServletContext().getAttribute(
            QuizManagerDAO.ATTRIBUTE_NAME);

            Quiz q = qManager.getQuiz(Q_ID);
            List<Question> questionList = qManager.getQuestions(q);
            if(q.getPageType().equals(PageType.ONE_PAGE))
                sPage = "yes";
            int count = 1; //to number questions
        %>
    </head>

    <body>
        <%if (sPage.equals("yes")){%>
            <div >

            </div>
        <% } else {%>
            <div >

            </div>
        <%}%>
    </body>
</html>
