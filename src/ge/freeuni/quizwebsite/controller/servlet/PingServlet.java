package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.AccountManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

public class PingServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        // setting unicode encoding for inputs
        response.setContentType("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>Ping</h1>");

        // getting account manager
        AccountManager accManager = (AccountManager) getServletContext()
                .getAttribute(AccountManager.ATTRIBUTE_NAME);

        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");

        out.println("<h1>DataSource: " + (dataSource != null) + "<h1>");
        out.println("<h1>AccountManager: " + (accManager != null) + "<h1>");
    }
}
