package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO;
import ge.freeuni.quizwebsite.model.Account;

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
@WebServlet("/removeAccount")
public class removeAccount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Account account = (Account) session.getAttribute("user_account");
        if (account != null){
            AccountManagerDAO accManager = (AccountManagerDAO) session.getServletContext().getAttribute(
                    AccountManagerDAO.ATTRIBUTE_NAME);
            accManager.removeAccount(account);
            response.sendRedirect("homePage.jsp");
        }else{
            System.out.println("null ari accounti");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
