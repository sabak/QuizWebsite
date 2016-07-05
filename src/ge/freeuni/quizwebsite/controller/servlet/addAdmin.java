package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO;
import ge.freeuni.quizwebsite.manager.dao.AdminManagerDAO;
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
@WebServlet("/addAdmin")
public class addAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Account account = (Account) session.getAttribute("user_account");
        AdminManagerDAO adminManager = (AdminManagerDAO) session.getServletContext().getAttribute(AdminManagerDAO.ATTRIBUTE_NAME);
        adminManager.addAdmin(account);
        response.sendRedirect("otherUserPage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
