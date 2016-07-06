package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.ChallengeManager;
import ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO;
import ge.freeuni.quizwebsite.manager.dao.ChallengeManagerDAO;
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
@WebServlet("/challengeFriend")
public class challengeFriend extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("Username");
        HttpSession session = request.getSession(true);

        ChallengeManager challengeManager = (ChallengeManagerDAO)session.getServletContext().getAttribute(
                ChallengeManagerDAO.ATTRIBUTE_NAME);
        AccountManagerDAO accManager = (AccountManagerDAO) getServletContext().getAttribute(
                AccountManagerDAO.ATTRIBUTE_NAME);
        Account to = accManager.getAccount(name);
        Account account = (Account) session.getAttribute("account");
        String quiz_id = (String) request.getParameter("quiz");
        System.out.println(quiz_id);
        Integer Q_ID = Integer.parseInt(quiz_id.substring(0, quiz_id.length()-1));

        QuizManagerDAO qManager = (QuizManagerDAO) session.getServletContext().getAttribute(
                QuizManagerDAO.ATTRIBUTE_NAME);

        Quiz q = qManager.getQuiz(Q_ID);
        challengeManager.challengeUser(account, to, q);
        response.sendRedirect("takequiz.jsp?quiz=" + quiz_id);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
