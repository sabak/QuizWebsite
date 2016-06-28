package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO;
import ge.freeuni.quizwebsite.model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Sandro on 6/28/2016.
 */
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("password");

        AccountManagerDAO accManager = new AccountManagerDAO();
        HttpSession session = request.getSession(true);

        if(accManager.usernameExists(username)){
            Account account = accManager.getAccount(username);
            if(password.equals(account.getHashedPassword())){
                session.setAttribute("id", account.getId());
                RequestDispatcher rd = request.getRequestDispatcher("homePage.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect("index.jsp");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
