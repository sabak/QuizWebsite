package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO;
import ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO;
import ge.freeuni.quizwebsite.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by AVTO on 6/30/2016.
 */
@WebServlet("/QuizCreation")
public class QuizCreation extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountManagerDAO accManager = (AccountManagerDAO) getServletContext().getAttribute(
                AccountManagerDAO.ATTRIBUTE_NAME);
        QuizManagerDAO quizManager = (QuizManagerDAO) getServletContext().getAttribute(
                QuizManagerDAO.ATTRIBUTE_NAME
        );
        HttpSession session = request.getSession(true);
        Account account = accManager.getAccount((String) session.getAttribute("account_un"));
        String requetType = request.getParameter("type");
        String qName;
        String qDesc;
        boolean isRandomized = false,
                isImmediatelyCorrected = false;
        PageType pageType = PageType.MULTI_PAGE;
        int index = 1;
        Quiz quiz = null;

        if(requetType.equals("0")){//get quiz name, description, and booleans

            qName = request.getParameter("quiz-name");
            qDesc = request.getParameter("quiz-description");

            String[] boxes = request.getParameterValues("cBox");

            for(int i=0; i<3; i++){
                if(boxes[i] != null && boxes[i].equals("random")){
                    isRandomized = true;
                } else if(boxes[i] != null && boxes[i].equals("isImmediate")){
                    isImmediatelyCorrected = true;
                } else if(boxes[i] != null && boxes[i].equals("sPage")){
                    pageType = PageType.ONE_PAGE;
                }
                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sqlTime = new java.sql.Timestamp(utilDate.getTime());
                quiz = new Quiz(qName, qDesc, isRandomized, isImmediatelyCorrected, pageType, sqlTime);
            }
        }else if(requetType.equals("1")){//question response type]
            quizManager.addQuestion(quiz,
                    createQuestion(QuestionType.QUESTION_RESPONSE, (String) request.getAttribute("q1"),
                            index, request.getParameter("a1"), request));
        }else if(requetType.equals("2")){//fill in the blanks
            quizManager.addQuestion(quiz,
                    createQuestion(QuestionType.FILL_IN_THE_BLANK, (String) request.getAttribute("q2"),
                            index, request.getParameter("a2"), request));
        }else if(requetType.equals("3")){//multiple choice
            quizManager.addQuestion(quiz,
                    createQuestion(QuestionType.MULTIPLE_CHOICE, (String) request.getAttribute("q3"),
                            index, request.getParameter("a3"), request));
        }else if(requetType.equals("4")){//picture-response
            quizManager.addQuestion(quiz,
                createQuestion(QuestionType.PICTURE_RESPONSE, (String) request.getAttribute("q4"),
                        index, request.getParameter("a4"), request));
        }
    }

    private Question createQuestion(QuestionType qt, String questionText, int index, String ans, HttpServletRequest request){
        //get answers
        //and store them in a list
        StringTokenizer tk = new StringTokenizer(ans, ";");
        List<Answer> answers = new Vector<>();
        while(tk.hasMoreElements()){
            Answer answer = new Answer(tk.nextToken(), true);
            answers.add(answer);
        }
        //if the question is a multiple choice one,
        //add wrong options to the list
        if(request.getParameter("type").equals("3")){
            String o1 = request.getParameter("o31");
            String o2 = request.getParameter("o32");
            String o3 = request.getParameter("o33");

            answers.add(new Answer(o1, true));
            answers.add(new Answer(o2, true));
            answers.add(new Answer(o3, true));
        }

        Question question = new Question(qt, questionText , index, answers);
        return question;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
