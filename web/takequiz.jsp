<%@ page import="ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.StatsManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Account" %>
<%@ page import="ge.freeuni.quizwebsite.model.PageType" %>
<%@ page import="ge.freeuni.quizwebsite.model.Quiz" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.AdminManagerDAO" %>
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
        <title>Quiz Creation</title>
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
            AdminManagerDAO adminManager = (AdminManagerDAO) session.getServletContext().getAttribute(AdminManagerDAO.ATTRIBUTE_NAME);


            Quiz q = qManager.getQuiz(Q_ID);
            session.setAttribute("quiz", q);

            Account account =(Account) session.getAttribute("account");

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

            <button class="button sub" id="submit" onclick="location.href='actualQuiz.jsp?quiz=<%=Q_ID%>'" style="margin-left: 25px;"> Take Quiz </button>
            </br>
            <%
                int limit = qManager.getQuizQuantity();
                boolean author = false;
                List quizzes = qManager.getCreatedQuizzes(account, limit);
                for (int i=0; i< quizzes.size(); i++){
                    if (quizzes.get(i).toString().equals(q.toString())) {
                        author = true;
                    }
                }
                if(adminManager.isAdmin(account) || author ) {%>

                <button class="button pr" onclick="location.href='RemoveQuiz'"> Remove Quiz </button>
            <% }%>
            <form action = "challengeFriend" method = "post">
                <input type=hidden name="quiz" value=<%=quiz_id%>
                        />
                <input type="text" name="Username" placeholder="Enter Friends's Username to challenge" id = "qName" style="position:relative; left:10%; top:70px;"/>

                <button class="button pr" > Challenge Friend </button>
            </form>
            <button class="button pr" onclick="location.href='homePage.jsp'"> Cancel </button>
        </div>
    </body>
</html>
