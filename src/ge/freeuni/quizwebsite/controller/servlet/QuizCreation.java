package ge.freeuni.quizwebsite.controller.servlet;

import ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO;
import ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO;
import ge.freeuni.quizwebsite.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
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
       // System.out.println("1." + quiz.toString());
        List<Question> questionList = new ArrayList<>();
        System.out.println("size: " + questionList.size());

        if(requetType.equals("0")){//get quiz name, description, and booleans

            qName = request.getParameter("quiz-name");
            qDesc = request.getParameter("quiz-description");

            String[] boxes = request.getParameterValues("cBox");
            if(boxes != null) {
                for (int i = 0; i < boxes.length; i++) {
                    if (boxes[i].equals("random")) {
                        isRandomized = true;
                    } else if (boxes[i].equals("isImmediate")) {
                        isImmediatelyCorrected = true;
                    } else if (boxes[i].equals("sPage")) {
                        pageType = PageType.ONE_PAGE;
                    }
                }
            }
            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp sqlTime = new java.sql.Timestamp(utilDate.getTime());
            quiz = new Quiz(qName, qDesc, isRandomized, isImmediatelyCorrected, pageType, sqlTime);
            quiz = quizManager.createQuiz(quiz, account);
            session.setAttribute("quiz", quiz);
            RequestDispatcher rd = request.getRequestDispatcher("addQuestion.jsp");
            rd.forward(request, response);

        }else if(requetType.equals("1")){//question response type]
            Question q = createQuestion(QuestionType.QUESTION_RESPONSE, (String) request.getAttribute("q1"),
                    index, request.getParameter("a1"), request);
            boolean add = questionList.add(q);
            System.out.println(q.toString() + " qr");
            System.out.println(add + requetType);
            System.out.println(questionList.get(questionList.size()-1));
        }else if(requetType.equals("2")){//fill in the blanks
            Question q = createQuestion(QuestionType.FILL_IN_THE_BLANK, (String) request.getAttribute("q2"),
                    index, request.getParameter("a2"), request);
            boolean add = questionList.add(q);
            System.out.println(q.toString() + " fib");
            System.out.println(add + requetType);
            System.out.println(questionList.get(questionList.size()-1));
        }else if(requetType.equals("3")){//multiple choice
            Question q = createQuestion(QuestionType.MULTIPLE_CHOICE, (String) request.getAttribute("q3"),
                    index, request.getParameter("a3"), request);
            System.out.println(q.toString() + " mc");
            boolean add = questionList.add(q);
            System.out.println(add + requetType);
            System.out.println(questionList.get(questionList.size()-1));
        }else if(requetType.equals("4")){//picture-response
            Question q = createQuestion(QuestionType.PICTURE_RESPONSE, (String) request.getAttribute("q4"),
                    index, request.getParameter("a4"), request);
            System.out.println(q.toString() + " pr");
            boolean add = questionList.add(q);
            System.out.println(add + requetType);
            System.out.println(questionList.get(questionList.size()-1));
        }else if(requetType.equals("-1")){
            System.out.println("3." + session.getAttribute("quiz").toString());
            quiz = (Quiz)session.getAttribute("quiz");
            System.out.println(questionList.get(questionList.size()-1) + " question list");
            quizManager.addQuestions(quiz, questionList);
            session.setAttribute("quiz", quizManager.createQuiz(quiz , account));
            System.out.println("5." + session.getAttribute("quiz").toString());
            RequestDispatcher rd = request.getRequestDispatcher("quizOverview.jsp");
            rd.forward(request, response);
        }else if(requetType.equals("-2")){
            quizManager.createQuiz(quiz, account);
            RequestDispatcher rd = request.getRequestDispatcher("homePage.jsp");
            rd.forward(request, response);
        }
        RequestDispatcher rd = request.getRequestDispatcher("addQuestion.jsp");
        rd.forward(request, response);
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
        System.out.println(question.toString());
        return question;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
