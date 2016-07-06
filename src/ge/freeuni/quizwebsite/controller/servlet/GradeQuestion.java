package ge.freeuni.quizwebsite.controller.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/GradeQuestion")
public class GradeQuestion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Integer i;
        if(request.getParameter("i") != null){
            i = Integer.parseInt(request.getParameter("i"));
            i++;
        } else{
            i=1;
        }
        session.setAttribute("index", i);
        String id = request.getParameter("Q_ID").substring(0, request.getParameter("Q_ID").length()-1);
        session.setAttribute("Q_ID", Integer.parseInt(id));
        Integer max = Integer.parseInt(request.getParameter("max").substring(0, request.getParameter("max").length()-1));
        System.out.println(max + " bob");
        if(i == max-1){
            RequestDispatcher rd = request.getRequestDispatcher("resultPage.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("multiplePageQuiz.jsp");
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
