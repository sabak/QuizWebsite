package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO;
import ge.freeuni.quizwebsite.model.Account;

import javax.activation.DataSource;
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
 * Created by Sandro on 6/27/2016.
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String fn = request.getParameter("firstName");
        String ln = request.getParameter("lastName");
        String password = request.getParameter("password");
        String password_confirm = request.getParameter("password_confirm");
        String e_mail = request.getParameter("e_mail");

        AccountManagerDAO accManager = (AccountManagerDAO) getServletContext().getAttribute(
                AccountManagerDAO.ATTRIBUTE_NAME);


        HttpSession session = request.getSession(true);
        if(!checkParams(username, fn, ln, password, password_confirm, e_mail, accManager)){
            session.setAttribute("created", "false");
            RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
            rd.forward(request, response);
        } else {
            session.setAttribute("created", "false");
            Account account = new Account(username, hashText(password), e_mail, fn, ln);
            accManager.createAccount(account);
            response.sendRedirect("index.jsp");
        }
    }

    private boolean checkParams(String username, String fn, String ln, String password, String password_confirm, String e_mail, AccountManagerDAO accManager) {

        //check username
        if(accManager.usernameExists(username))
            return false;

        //check first and last names
        if(fn.length()<2 || ln.length()<2)
            return false;

        //check password
        if(!password.equals(password_confirm) || password.length()<6)
            return false;

        //check e-mail
        boolean hasAt = false;
        for(int i=0; i<e_mail.length()-1; i++) {
            if(e_mail.charAt(i) == '@')
                hasAt = true;
        }
        return hasAt;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
