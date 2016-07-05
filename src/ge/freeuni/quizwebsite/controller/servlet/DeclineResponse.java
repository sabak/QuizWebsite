package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.FriendManagerDAO;
import ge.freeuni.quizwebsite.model.message.FriendRequest;

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
@WebServlet(name = "DeclineResponse")
public class DeclineResponse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        FriendManagerDAO friendManager = (FriendManagerDAO) getServletContext().getAttribute(
                FriendManagerDAO.ATTRIBUTE_NAME);
        FriendRequest req = (FriendRequest)session.getAttribute("request");


        friendManager.declineFriendship(req);

        response.sendRedirect("homePage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
