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
            System.out.println(quizManager.getQuestions(quiz));
        %>
    </head>

    <body>
        <div style="position: absolute; left: 40%;top: 10%">
            <!--quiz details are displayed here-->
            <form name="quiz" id="inputs_form" action="/Login" method="post">
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

                <p class="tn">
                    <%
                        System.out.println(quiz);
                        AccountManagerDAO accManager = (AccountManagerDAO) session.getServletContext().getAttribute(
					                                        AccountManagerDAO.ATTRIBUTE_NAME);
					    Account account = (Account) accManager.getAccount((String) session.getAttribute("account_un"));
                        quizManager.createQuiz(quiz, account);
                        List<Question> questions = quizManager.getQuestions(quiz);
                        System.out.println(questions);
                        for(int i=0; i<questions.size(); i++){
                            Question quest = questions.get(i);
                            String Q_TEXT = quest.getText();
                           // Integer Q_ID = quest.getId();
                            List<Answer> Q_ANS = quest.getAnswers();
                            QuestionType Q_TYPE = quest.getType();
                            int Q_INDEX = quest.getIndex(); %>

                            <p>
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
                            </p>
                    <%  }
                    %>
                </p>

            </form>

            <div id="bs"><!-- bs stands for buttons -->
                <button class="button pass" onclick="location.href='prec.html'">Create!</button>
                <br/>
                <button class="button pass" onclick="location.href='prec.html'">add more questions</button>
                <br/>
                <button class="button pass" onclick="location.href='registration.jsp'"> Cancel</button>
            </div>
        </div>
    </body>
</html>
