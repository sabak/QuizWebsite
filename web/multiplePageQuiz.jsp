<%@ page import="ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Question" %>
<%@ page import="ge.freeuni.quizwebsite.model.QuestionType" %>
<%@ page import="ge.freeuni.quizwebsite.model.Quiz" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Random" %>
<%--
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
            String quiz_id = (String) request.getParameter("quiz");
            System.out.println("quvizicjs2 " + quiz_id);
            Integer Q_ID;
            if(quiz_id != null) {
                Q_ID = Integer.parseInt(quiz_id);
            } else {
                Q_ID = (Integer) session.getAttribute("Q_ID");
            }
            System.out.println("quvizicjs3 " + Q_ID);
            QuizManagerDAO qManager = (QuizManagerDAO) session.getServletContext().getAttribute(
                    QuizManagerDAO.ATTRIBUTE_NAME);

            Quiz q = qManager.getQuiz(Q_ID);
            List<Question> questionList = qManager.getQuestions(q);
            if(q.hasHasRandomOrder())
                Collections.shuffle(questionList, new Random(System.nanoTime()));
            Integer i = 0;
            if(session.getAttribute("index") != null)
                i = (Integer) session.getAttribute("index");
            Integer count = i+1; //to number questions
        %>
    </head>

    <body>
        <form id="test2" action="/GradeQuestion" method="post">
            <input type=hidden name="index" value=<%=i%>/>
            <input type=hidden name="max" value=<%=questionList.size()%>/>
            <input type=hidden name="Q_ID" value=<%=Q_ID%>/>
            <input type="hidden" name="question" value="<%=questionList.get(i).getId()%>">
            <p class="tb"> Question # <%=count%> <%count++;%></p>
            <div id="question_div_sPage">
                <% if(questionList.get(i).getType().equals(QuestionType.FILL_IN_THE_BLANK) ||
                        questionList.get(i).getType().equals(QuestionType.QUESTION_RESPONSE)) {%>
                Question: <%=questionList.get(i).getText()%> </br>
                <input type="text" name="<%=i%>" placeholder="answer" style="margin-bottom: 10px"/>

                <% } else if(questionList.get(i).getType().equals(QuestionType.MULTIPLE_CHOICE)){%>
                Question: <%=questionList.get(i).getText()%> </br>

                <% for(int k=0; k<questionList.get(i).getAnswers().size(); k++) {%>
                <input type="radio" name="<%=i%>" value="<%=questionList.get(i).getAnswers().get(k).getText()%>"> <%=questionList.get(i).getAnswers().get(k).getText()%> </br>
                <%}%>

                <% } else if(questionList.get(i).getType().equals(QuestionType.PICTURE_RESPONSE)){%>
                <img src="<%=questionList.get(i).getText()%>" alt="HTML5 Icon" style="width:128px;height:128px;"> </br>
                <input type="text" name="<%=i%>" placeholder="answer" style="margin-bottom: 10px"/>
                <% }%>
            </div>
            <button class="button sub" onclick="document.getElementById('test2').submit();"> Submit </button>
        </form>
    </body>
</html>
