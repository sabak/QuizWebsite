package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.*;
import ge.freeuni.quizwebsite.manager.dao.*;
import ge.freeuni.quizwebsite.util.SHAHasher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ping")
public class PingService extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // setting unicode encoding for inputs
        response.setContentType("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>Ping</h1>");

        // getting various managers
        AccountManager accManager = (AccountManager) getServletContext()
                .getAttribute(AccountManagerDAO.ATTRIBUTE_NAME);
        AdminManager adminManager = (AdminManager) getServletContext()
                .getAttribute(AdminManagerDAO.ATTRIBUTE_NAME);
        AchievementManager achievementManager = (AchievementManager) getServletContext()
                .getAttribute(AchievementManagerDAO.ATTRIBUTE_NAME);
        AnnouncementManager announcementManager = (AnnouncementManager) getServletContext()
                .getAttribute(AnnouncementManagerDAO.ATTRIBUTE_NAME);
        ChallengeManager challengeManager = (ChallengeManager) getServletContext()
                .getAttribute(ChallengeManagerDAO.ATTRIBUTE_NAME);
        FriendManager friendManager = (FriendManager) getServletContext()
                .getAttribute(FriendManagerDAO.ATTRIBUTE_NAME);
        TextMessageManager messageManager = (TextMessageManager) getServletContext()
                .getAttribute(TextMessageManagerDAO.ATTRIBUTE_NAME);
        HistoryManager historyManager = (HistoryManager) getServletContext()
                .getAttribute(HistoryManagerDAO.ATTRIBUTE_NAME);
        QuizManager quizManager = (QuizManager) getServletContext()
                .getAttribute(QuizManagerDAO.ATTRIBUTE_NAME);
        StatsManager statsManager = (StatsManager) getServletContext()
                .getAttribute(StatsManagerDAO.ATTRIBUTE_NAME);

        out.println("<h2>AccountManager: " + (accManager != null) + "</h2>");
        out.println("<h2>AdminManager: " + (adminManager != null) + "</h2>");
        out.println("<h2>AchievementManager: " + (achievementManager != null) + "</h2>");
        out.println("<h2>AnnouncementManager: " + (announcementManager != null) + "</h2>");
        out.println("<h2>ChallengeManager: " + (challengeManager != null) + "</h2>");
        out.println("<h2>FriendManager: " + (friendManager != null) + "</h2>");
        out.println("<h2>TextMessageManager: " + (messageManager != null) + "</h2>");
        out.println("<h2>HistoryManager: " + (historyManager != null) + "</h2>");
        out.println("<h2>QuizManager: " + (quizManager != null) + "</h2>");
        out.println("<h2>StatsManager: " + (statsManager != null) + "</h2>");
        out.println("<h3>SHA-256 Hash for \'humanlabrador\': " + SHAHasher.hashText("humanlabrador")
                + ", should be 54b4427f1e21f7b79a0b656b259520ceb5f49055e47c1e84c6f3346b77d537a5</h3>");

        out.println(accManager != null ? accManager.getAccountsQuantity() : 0);
    }

}
