package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO;
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
 * Created by Sandro on 6/28/2016.
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("password");

        AccountManagerDAO accManager = (AccountManagerDAO) getServletContext().getAttribute(
                AccountManagerDAO.ATTRIBUTE_NAME);

        HttpSession session = request.getSession(true);
        Account acc = accManager.getAccount("tura");
        System.out.println(acc.getId());
        if(accManager.usernameExists(username)){
            if(password != null && hashText(password).equals(accManager.getAccount(username).getHashedPassword())){
                session.setAttribute("account_un", username);
                RequestDispatcher rd = request.getRequestDispatcher("homePage.jsp");
                rd.forward(request, response);
                Account account = accManager.getAccount(username);
                session.setAttribute("account", account);
                response.sendRedirect("homePage.jsp");
                System.out.println("shevida");
            } else{
                System.out.println("ver shevida");
                session.setAttribute("user", "password");
                response.sendRedirect("index.jsp");
            }
        } else {
            session.setAttribute("user", "username");
            response.sendRedirect("index.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
