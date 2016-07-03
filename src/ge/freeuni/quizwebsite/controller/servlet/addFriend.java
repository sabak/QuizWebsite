package ge.freeuni.quizwebsite.controller.servlet;


import ge.freeuni.quizwebsite.manager.dao.FriendManagerDAO;
import ge.freeuni.quizwebsite.model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by AVTO on 7/3/2016.
 */
@WebServlet("/addFriend")
public class addFriend extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("aaaaaaaaaaaaaaa");
        HttpSession session = request.getSession(true);
        Account account = (Account) session.getAttribute("user_account");
        Account senderAccount = (Account) session.getAttribute("account");
        FriendManagerDAO friendManager = (FriendManagerDAO) getServletContext().getAttribute(
                FriendManagerDAO.ATTRIBUTE_NAME);
        friendManager.requestFriendship(senderAccount, account);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
