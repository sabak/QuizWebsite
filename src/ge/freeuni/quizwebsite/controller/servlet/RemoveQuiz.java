package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO;
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
@WebServlet("/RemoveQuiz")
public class RemoveQuiz extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        QuizManagerDAO quizManager = (QuizManagerDAO) getServletContext().getAttribute(
                QuizManagerDAO.ATTRIBUTE_NAME);
        Quiz q = (Quiz) session.getAttribute("quiz");
        quizManager.removeQuiz(q);
        response.sendRedirect("homePage.jsp");



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
