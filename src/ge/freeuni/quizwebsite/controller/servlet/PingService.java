package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.AccountManager;
import ge.freeuni.quizwebsite.util.SHAHasher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
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

        // getting account manager
        AccountManager accManager = (AccountManager) getServletContext()
                .getAttribute(AccountManager.ATTRIBUTE_NAME);

        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");

        out.println("<h2>DataSource: " + (dataSource != null) + "</h2>");
        out.println("<h2>AccountManager: " + (accManager != null) + "</h2>");
        out.println("<h3>SHA-256 Hash for \'humanlabrador\': " + SHAHasher.hashText("humanlabrador")
                + ", should be 54b4427f1e21f7b79a0b656b259520ceb5f49055e47c1e84c6f3346b77d537a5</h3>");
    }
}
