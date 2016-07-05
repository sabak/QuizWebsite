package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO;
import ge.freeuni.quizwebsite.manager.dao.AnnouncementManagerDAO;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Announcement;

import javax.servlet.RequestDispatcher;
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
@WebServlet("/makeAnnouncement")
public class makeAnnouncement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AnnouncementManagerDAO annManager = ( AnnouncementManagerDAO) getServletContext().getAttribute(
                AnnouncementManagerDAO.ATTRIBUTE_NAME);
        String param = request.getParameter("announcementText");
        HttpSession session = request.getSession(true);
        Account author = (Account) session.getAttribute("account");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlTime = new java.sql.Timestamp(utilDate.getTime());
        Announcement a = new Announcement(param, author, sqlTime);
        annManager.createAnnouncement(a);
        response.sendRedirect("/homePage.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
