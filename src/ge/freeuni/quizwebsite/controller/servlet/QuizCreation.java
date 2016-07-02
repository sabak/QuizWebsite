package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.PageType;
import ge.freeuni.quizwebsite.model.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by AVTO on 6/30/2016.
 */
@WebServlet("/QuizCreation")
public class QuizCreation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //name of the quiz
        String name = "quiz-name";
        String quizName = request.getParameter(name);

        //description of the quiz
        String descName = "quiz-description";
        String description = request.getParameter(descName);


        //whether or not the quiz should be in random order
        //false by default
        String randomName = "rand";
        boolean isRandom = false;
        if (request.getParameter(randomName) == "true")
            isRandom = true;
        System.out.println(isRandom);
        System.out.println(request.getParameter(randomName));
        //whether or not the quiz should be immediately corrected
        //false by default
        String immediateName = "immCorrected";
        boolean immediate = false;
        if (request.getParameter(immediateName) == "true")
            immediate = true;
        String pageTypeName = "sPage";
        PageType pageType = PageType.MULTI_PAGE;
        if (request.getParameter(pageTypeName) == null){
            pageType = PageType.ONE_PAGE;
        }
        HttpSession session = request.getSession(true);
        String questionName = "questions";
        Object quests = session.getAttribute(questionName);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlTime = new java.sql.Timestamp(utilDate.getTime());
        QuizManagerDAO quizManager = (QuizManagerDAO) request.getServletContext().getAttribute(QuizManagerDAO.ATTRIBUTE_NAME);
//        Quiz quiz = new Quiz(quizName, description, isRandom, immediate, pageType, sqlTime);
//
//        quizManager.createQuiz(quiz, (Account)session.getAttribute("account"));
//        quizManager.addQuestions(quiz, quests);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
