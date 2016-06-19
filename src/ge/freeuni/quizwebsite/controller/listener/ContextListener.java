package ge.freeuni.quizwebsite.controller.listener;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import ge.freeuni.quizwebsite.manager.dao.*;
import ge.freeuni.quizwebsite.manager.dao.db.DBInfo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.sql.DataSource;

@WebListener()
public class ContextListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public ContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/"
                    + DBInfo.DB_NAME);
            sce.getServletContext().setAttribute("DataSource", dataSource);
            sce.getServletContext().setAttribute(AccountManagerDAO.ATTRIBUTE_NAME, new AccountManagerDAO(dataSource));
            sce.getServletContext().setAttribute(AdminManagerDAO.ATTRIBUTE_NAME, new AdminManagerDAO(dataSource));
            sce.getServletContext().setAttribute(AnnouncementManagerDAO.ATTRIBUTE_NAME, new AnnouncementManagerDAO(dataSource));
            sce.getServletContext().setAttribute(AchievementManagerDAO.ATTRIBUTE_NAME, new AchievementManagerDAO(dataSource));
            sce.getServletContext().setAttribute(ChallengeManagerDAO.ATTRIBUTE_NAME, new ChallengeManagerDAO(dataSource));
            sce.getServletContext().setAttribute(FriendManagerDAO.ATTRIBUTE_NAME, new FriendManagerDAO(dataSource));
            sce.getServletContext().setAttribute(TextMessageManagerDAO.ATTRIBUTE_NAME, new TextMessageManagerDAO(dataSource));
            sce.getServletContext().setAttribute(HistoryManagerDAO.ATTRIBUTE_NAME, new HistoryManagerDAO(dataSource));
            sce.getServletContext().setAttribute(QuizManagerDAO.ATTRIBUTE_NAME, new QuizManagerDAO(dataSource));
            sce.getServletContext().setAttribute(StatsManagerDAO.ATTRIBUTE_NAME, new StatsManagerDAO(dataSource));

        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        try {
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
