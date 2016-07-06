package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Answer;
import ge.freeuni.quizwebsite.model.Question;
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

/**
 * Created by AVTO on 7/6/2016.
 */
@WebServlet("/GradeQuiz")
public class GradeQuiz extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer score = 0;
        HttpSession session = request.getSession(true);
        Account account = (Account) session.getAttribute("account");

        QuizManagerDAO qManager = (QuizManagerDAO) session.getServletContext().getAttribute(
                QuizManagerDAO.ATTRIBUTE_NAME);

        Quiz q = (Quiz) session.getAttribute("quiz");
        List<Question> questionList = (List<Question>) session.getAttribute("questionList");
        for(Integer i=0; i<questionList.size();i++){
            System.out.println(request.getParameter(i.toString()) + " 1");
            String answer = request.getParameter(i.toString());
            List<Answer> answerList = questionList.get(i).getAnswers();
            System.out.println(answerList + " 2");
            for(int j=0; j<answerList.size(); j++) {
                if (answer.equals(answerList.get(j).getText()))
                    score++;
            }
        }

        session.setAttribute("score", score);
        session.setAttribute("max", questionList.size());
        RequestDispatcher rd = request.getRequestDispatcher("resultPage.jsp");
        rd.forward(request, response);

        System.out.println(account);
        System.out.println(q);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
