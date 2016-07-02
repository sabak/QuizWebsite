package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO;
import ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO;
import ge.freeuni.quizwebsite.model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ge.freeuni.quizwebsite.util.SHAHasher.hashText;

/**
 * Created by AVTO on 7/2/2016.
 */
@WebServlet("/searchInfo")
public class searchInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("searchData");
        if (name != null && name!= ""){
            QuizManagerDAO quizManager = (QuizManagerDAO) request.getServletContext().getAttribute(QuizManagerDAO.ATTRIBUTE_NAME);
            AccountManagerDAO accountManager = (AccountManagerDAO) request.getServletContext().getAttribute(AccountManagerDAO.ATTRIBUTE_NAME);
            Account account = accountManager.getAccount(name);
            if (account != null){
                HttpSession session = request.getSession(true);
                session.setAttribute("user_account", account);
                response.sendRedirect("otherUserPage.jsp");
                }
            }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
