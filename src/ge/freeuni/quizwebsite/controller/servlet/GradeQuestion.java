package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.HistoryManagerDAO;
import ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO;
import ge.freeuni.quizwebsite.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/GradeQuestion")
public class GradeQuestion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Integer i;
        Integer score ;
        HistoryManagerDAO hManager = (HistoryManagerDAO) session.getServletContext().getAttribute(
                HistoryManagerDAO.ATTRIBUTE_NAME);
        if(session.getAttribute("index") != null ){
            i = (Integer) session.getAttribute("index");
        } else{
            i=0;
        }
        if(session.getAttribute("score") != null ){
            score = (Integer) session.getAttribute("score");
        } else{
            long startTime = System.currentTimeMillis();
            session.setAttribute("start", startTime);
            score=0;
        }
        session.setAttribute("index", i);
        String id = request.getParameter("Q_ID").substring(0, request.getParameter("Q_ID").length()-1);
        session.setAttribute("Q_ID", Integer.parseInt(id));
        Integer max = Integer.parseInt(request.getParameter("max").substring(0, request.getParameter("max").length()-1));

        String q = request.getParameter("question");
        Integer Q_ID = Integer.parseInt(q);

        QuizManagerDAO qManager = (QuizManagerDAO) session.getServletContext().getAttribute(
                QuizManagerDAO.ATTRIBUTE_NAME);
        List<Question> questionList = qManager.getQuestions((Quiz) session.getAttribute("quiz"));
        Question quest = null;
        for(int k=0; k<questionList.size(); k++){
            if(questionList.get(k).getId() == Q_ID)
                quest = questionList.get(k);
        }
        QuestionType type = quest.getType();
        String text = "";
        switch(type){
            case FILL_IN_THE_BLANK:
                text = (String) request.getParameter(i.toString());
                System.out.println("i: " + i);
                break;
            case QUESTION_RESPONSE:
                text = (String) request.getParameter(i.toString());
                System.out.println("i: " + i);
                break;
            case PICTURE_RESPONSE:
                text = (String) request.getParameter(i.toString());
                System.out.println("i: " + i);
                break;
            case MULTIPLE_CHOICE:
                text = (String) request.getParameter(i.toString());
                System.out.println("i: " + i);
                break;
        }
        i++;
        session.setAttribute("index", i);
        List<Answer> ans = quest.getAnswers();
        session.setAttribute("lastOneCorrect", false);
        for (int j =0; j < ans.size(); j++){
               if(ans.get(j).isCorrect() && ans.get(j).getText().equals(text)){
                   score++;
                   session.setAttribute("lastOneCorrect", true);
               }
        }
        session.setAttribute("score", score);

        if(i == max){
            session.setAttribute("index", 0);
            long timeTaken = System.currentTimeMillis();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ;
            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sqlTime = new java.sql.Timestamp(utilDate.getTime());

            long start = (long)session.getAttribute("start");
            timeTaken -= start;
            Time t = new Time(timeTaken);
            QuizResult res = new QuizResult(score,(Account)session.getAttribute("account"), t,sqlTime);
            hManager.submitQuizResult((Account)session.getAttribute("account"),(Quiz) session.getAttribute("quiz"),res);
            session.setAttribute("max", max);
            RequestDispatcher rd = request.getRequestDispatcher("resultPage.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("multiplePageQuiz.jsp");
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
