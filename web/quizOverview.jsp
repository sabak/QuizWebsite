<%@ page import="ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.StringTokenizer" %>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/3/2016
  Time: 10:36 PM
  To change this template use File | Settings | File Templates.

  after user is done adding questions to the quiz,
  this is where he can review it
  to add more questions,
  remove already added ones,
  cancel quiz creation
  or submit the quiz
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Quiz</title>
        <link rel="stylesheet" type="text/css" href="rules.css"/>
        <%
            QuizManagerDAO quizManager = (QuizManagerDAO) session.getServletContext().getAttribute(
                    QuizManagerDAO.ATTRIBUTE_NAME);
            Quiz quiz = (Quiz) session.getAttribute("quiz");
        %>
    </head>

    <body>
        <div style="position: absolute; left: 40%;top: 10%">
            <!--quiz details are displayed here-->
            <div>
                <h1 id="title">Your Quiz</h1>

                <p class="tn">
                    <%
                        String QUIZ_STRING = quiz.toString();
                        StringTokenizer tk = new StringTokenizer(QUIZ_STRING, ",");
                        tk.nextToken();//so that ID isn't displayed
                        while(tk.hasMoreElements()){ %>
                            <%=tk.nextToken()%> </br>
                    <%  }
                    %>
                </p>
]
                <form id="listOfQuestions" action="/QuizCreation" method="post">
                    <input type=hidden name="type" value="-2"/>
                    <p class="tn">
                            <%
                            List<Question> questions = (List<Question>) session.getAttribute("questions");
                            System.out.println("kitxvebi:" + questions);
                            for(int i=0; i<questions.size(); i++){
                                Question quest = questions.get(i);
                                String Q_TEXT = quest.getText();
                                Integer Q_ID = quest.getId();
                                List<Answer> Q_ANS = quest.getAnswers();
                                QuestionType Q_TYPE = quest.getType(); %>
                                <p class="tn">
                                    Question: <%=Q_TEXT%> </br>
                                    <%
                                        String qType = null;
                                        switch(Q_TYPE){
                                            case QUESTION_RESPONSE: qType = "Question-Response"; break;
                                            case FILL_IN_THE_BLANK: qType = "Fill in the Blank"; break;
                                            case MULTIPLE_CHOICE: qType = "Multiple Choice"; break;
                                            case PICTURE_RESPONSE: qType = "Picture-Resopnse"; break;
                                        }
                                    %>
                                    Question Type: <%=qType%> </br>
                                    Answer(s):<%
                                        for(int j=0; j<Q_ANS.size(); j++){%>
                                            <%=Q_ANS.get(j)%> </br>
                                    <%  }
                                    %>
                                    <input type="checkbox" name="removeQ" value=<%=Q_ID%>> remove question
                                </p>
                        <%  }
                        %>
                    </p>
                    <div id="bs"><!-- bs stands for buttons -->
                        <button class="button sub" onclick="document.getElementById('listOfQuestions').submit()">Create!</button>
                        <br/>
                        <button class="button pass" onclick="location.href='addQuestion.jsp'">add more questions</button>
                        <br/>
                        <button class="button pass" onclick="location.href='registration.jsp'"> Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
