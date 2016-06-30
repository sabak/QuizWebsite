package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.QuizManager;
import ge.freeuni.quizwebsite.manager.dao.db.DbContract;
import ge.freeuni.quizwebsite.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by Saba on 19-06-2016.
 */
public class QuizManagerDAO extends AbstractManagerDAO implements QuizManager {

    public static final String ATTRIBUTE_NAME = "quiz_manager";

    public QuizManagerDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Quiz getQuiz(Integer id) {
        Quiz quiz = null;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM " + DbContract.Quiz.TABLE_NAME + " WHERE "
                    + DbContract.Quiz.COLUMN_NAME_QUIZ_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String name = rs.getString(DbContract.Quiz.COLUMN_NAME_NAME);
                String description = rs.getString(DbContract.Quiz.COLUMN_NAME_DESCRIPTION);
                Timestamp dateCreated = rs.getTimestamp(DbContract.Quiz.COLUMN_NAME_DATE_CREATED);
                boolean hasRandomOrder = rs.getBoolean(DbContract.Quiz.COLUMN_NAME_HAS_RANDOM);
                boolean isMultiplePage = rs.getBoolean(DbContract.Quiz.COLUMN_NAME_MULTIPLE_PAGE);
                boolean isImmediatelyCorrected = rs.getBoolean(DbContract.Quiz.COLUMN_NAME_IMMEDIATE_CORRECTION);

                quiz = new Quiz(id, name, description, hasRandomOrder,
                        isImmediatelyCorrected, isMultiplePage ? PageType.MULTI_PAGE : PageType.ONE_PAGE,
                        dateCreated);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return quiz;
    }

    @Override
    public boolean createQuiz(Quiz quiz, Account account) {
        try {
            Connection con = dataSource.getConnection();
            String query = "INSERT INTO " + DbContract.Quiz.TABLE_NAME + " (" +
                    DbContract.Quiz.COLUMN_NAME_ACCOUNT_ID + ", " +
                    DbContract.Quiz.COLUMN_NAME_DATE_CREATED + ", " +
                    DbContract.Quiz.COLUMN_NAME_NAME + ", " +
                    DbContract.Quiz.COLUMN_NAME_DESCRIPTION + ", " +
                    DbContract.Quiz.COLUMN_NAME_HAS_RANDOM + ", " +
                    DbContract.Quiz.COLUMN_NAME_MULTIPLE_PAGE + ", " +
                    DbContract.Quiz.COLUMN_NAME_IMMEDIATE_CORRECTION + ") VALUES ( ?, ?, ?, ?, ?, ?, ?); ";
            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1, account.getId());
            statement.setTimestamp(2, quiz.getDateCreated());
            statement.setString(3, quiz.getName());
            statement.setString(4, quiz.getDescription());
            statement.setBoolean(5, quiz.hasHasRandomOrder());
            statement.setBoolean(6, quiz.getPageType() == PageType.MULTI_PAGE);
            statement.setBoolean(7, quiz.isImmediatelyCorrected());
            statement.executeUpdate();

            con.close();
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;

    }

    @Override
    public List<Question> getQuestions(Quiz quiz) {
        return null;
    }

    @Override
    public void addQuestions(Quiz quiz, List<Question> questions) {
        for (Question question : questions) {
            addQuestion(quiz, question);
        }
    }

    @Override
    public void addQuestion(Quiz quiz, Question question) {

    }

    @Override
    public void removeQuiz(Quiz quiz) {
        System.out.println("asdads");
        try (Connection con = dataSource.getConnection()) {
            String query = "DELETE FROM " + DbContract.Quiz.TABLE_NAME + " WHERE "
                    + DbContract.Quiz.COLUMN_NAME_QUIZ_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, quiz.getId());
            statement.executeUpdate();
            System.out.println("bigdeckz");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Quiz> getRecentlyCreatedQuizzes(int limit) {
        return getRecentlyCreatedQuizzes(0, limit);
    }

    @Override
    public List<Quiz> getRecentlyCreatedQuizzes(int limitFrom, int limitTo) {
        return null;
    }

    @Override
    public List<Quiz> getTakenQuizzes(Account account, int limit) {
        return getTakenQuizzes(account, 0, limit);
    }

    @Override
    public List<Quiz> getTakenQuizzes(Account account, int limitFrom, int limitTo) {
        return null;
    }

    @Override
    public List<Quiz> getCreatedQuizzes(Account account, int limit) {
        return getCreatedQuizzes(account, 0, limit);
    }

    @Override
    public List<Quiz> getCreatedQuizzes(Account account, int limitFrom, int limitTo) {
        return null;
    }

    @Override
    public List<QuizResult> getRecentlyTakenResults(Quiz quiz, int limit) {
        return getRecentlyTakenResults(quiz, 0, limit);
    }

    @Override
    public List<QuizResult> getRecentlyTakenResults(Quiz quiz, int limitFrom, int limitTo) {
        return null;
    }

    @Override
    public List<Quiz> getTakenQuizzes(int limit) {
        return getTakenQuizzes(0, limit);
    }

    @Override
    public List<Quiz> getTakenQuizzes(int limitFrom, int limitTo) {
        return null;
    }

}
