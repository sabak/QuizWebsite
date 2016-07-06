package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by AVTO on 7/6/2016.
 */
@WebServlet("/GradeQuiz")
public class GradeQuiz extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Account account = (Account) session.getAttribute("account");
        String quiz_id = (String) request.getParameter("quiz");
        Integer Q_ID = Integer.parseInt(quiz_id);

        QuizManagerDAO qManager = (QuizManagerDAO) session.getServletContext().getAttribute(
                QuizManagerDAO.ATTRIBUTE_NAME);

        Quiz q = qManager.getQuiz(Q_ID);

        System.out.println(account);
        System.out.println(q);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
