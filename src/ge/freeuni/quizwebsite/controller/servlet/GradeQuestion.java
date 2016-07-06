package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO;
import ge.freeuni.quizwebsite.model.Answer;
import ge.freeuni.quizwebsite.model.Question;
import ge.freeuni.quizwebsite.model.QuestionType;
import ge.freeuni.quizwebsite.model.Quiz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/GradeQuestion")
public class GradeQuestion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Integer i;
        Integer score ;
        if(session.getAttribute("index") != null ){
            i = (Integer) session.getAttribute("index");
            i++;
        } else{
            i=1;
        }
        if(session.getAttribute("score") != null ){
            score = (Integer) session.getAttribute("score");
        } else{
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
                text = (String) request.getAttribute(i.toString());
                break;
            case QUESTION_RESPONSE:
                text = (String) request.getAttribute(i.toString());
                break;
            case PICTURE_RESPONSE:
                text = (String) request.getAttribute(i.toString());
                break;
            case MULTIPLE_CHOICE:
                text = (String) request.getAttribute(i.toString());

                break;
        }
        List<Answer> ans = quest.getAnswers();
        session.setAttribute("lastOneCorrect", false);
        for (int j =0; j < ans.size(); j++){
               if(ans.get(j).getText().equals(text)){
                   score++;
                   session.setAttribute("lastOneCorrect", true);
               }
        }
        session.setAttribute("score", score);

        if(i == max){
            session.setAttribute("index", 0);
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
