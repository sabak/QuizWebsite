package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.FriendManagerDAO;
import ge.freeuni.quizwebsite.manager.dao.TextMessageManagerDAO;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.message.FriendRequest;
import ge.freeuni.quizwebsite.model.message.TextMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by AVTO on 7/5/2016.
 */
@WebServlet("/sendMessage")
public class sendMessage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //aq chaiwereba is saxeli romelic jspshi iqneba name am velis
        String msgName = "messageText";
        String message = request.getParameter(msgName);
        TextMessageManagerDAO messageManager = (TextMessageManagerDAO) getServletContext().getAttribute(
                TextMessageManagerDAO.ATTRIBUTE_NAME);

        HttpSession session = request.getSession(true);
        Account sender = (Account)session.getAttribute("account");
        Account reciever = (Account)session.getAttribute("user_account");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlTime = new java.sql.Timestamp(utilDate.getTime());
        TextMessage msg = new TextMessage(message, sender, reciever, sqlTime ,false);
        messageManager.sendMessage(msg);
        response.sendRedirect("otherUserPage.jsp");
        // to do:gadamisamarteba da am yvelafris amushaveba frontze
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
