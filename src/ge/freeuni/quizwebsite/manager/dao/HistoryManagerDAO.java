package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.HistoryManager;
import ge.freeuni.quizwebsite.manager.dao.db.DbContract;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Quiz;
import ge.freeuni.quizwebsite.model.QuizResult;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saba on 19-06-2016.
 */
public class HistoryManagerDAO extends AbstractManagerDAO implements HistoryManager {

    public static final String ATTRIBUTE_NAME = "history_manager";

    public HistoryManagerDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<QuizResult> getHistory(Account account, Quiz quiz) {
        List<QuizResult> quizResults = new ArrayList<>();
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM " + DbContract.QuizResult.TABLE_NAME + " WHERE "
                    + DbContract.QuizResult.COLUMN_NAME_ACCOUNT_ID + " = ? AND "
                    + DbContract.QuizResult.COLUMN_NAME_QUIZ_ID + " = ? ORDER BY "
                    + DbContract.QuizResult.COLUMN_NAME_SUBMIT_DATE + " DESC;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account.getId());
            statement.setInt(2, quiz.getId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt(DbContract.QuizResult.COLUMN_NAME_RESULT_ID);
                int score = rs.getInt(DbContract.QuizResult.COLUMN_NAME_SCORE);
                Time timeTaken = rs.getTime(DbContract.QuizResult.COLUMN_NAME_TIME_TAKEN);
                Timestamp submitDate = rs.getTimestamp(DbContract.QuizResult.COLUMN_NAME_SUBMIT_DATE);

                QuizResult quizResult = new QuizResult(id, score, account, timeTaken, submitDate);
                quizResults.add(quizResult);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return quizResults;
    }

    @Override
    public void submitQuizResult(Account account, Quiz quiz, QuizResult quizResult) {
        try (Connection con = dataSource.getConnection()) {
            String query = "INSERT INTO " + DbContract.QuizResult.TABLE_NAME + " (" +
                    DbContract.QuizResult.COLUMN_NAME_QUIZ_ID + ", " +
                    DbContract.QuizResult.COLUMN_NAME_ACCOUNT_ID + ", " +
                    DbContract.QuizResult.COLUMN_NAME_SCORE + ", " +
                    DbContract.QuizResult.COLUMN_NAME_TIME_TAKEN + ", " +
                    DbContract.QuizResult.COLUMN_NAME_SUBMIT_DATE + ") VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1, quiz.getId());
            statement.setInt(2, account.getId());
            statement.setInt(3, quizResult.getScore());
            statement.setTime(4, quizResult.getTimeTaken());
            Timestamp submitted = quizResult.getResultSubmitDate();
            statement.setTimestamp(5, submitted != null ? submitted : getCurrentTimestamp());
            statement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void clearHistory(Quiz quiz) {
        deleteQuizResult(DbContract.QuizResult.COLUMN_NAME_QUIZ_ID, quiz.getId());
    }

    @Override
    public void clearHistory(Account account) {
        deleteQuizResult(DbContract.QuizResult.COLUMN_NAME_ACCOUNT_ID, account.getId());
    }

    private void deleteQuizResult(String column, Integer id) {
        try (Connection con = dataSource.getConnection()) {
            String query = "DELETE FROM " + DbContract.QuizResult.TABLE_NAME + " WHERE "
                    + column + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void clearHistory(Quiz quiz, Account account) {
        try (Connection con = dataSource.getConnection()) {
            String query = "DELETE FROM " + DbContract.QuizResult.TABLE_NAME + " WHERE "
                    + DbContract.QuizResult.COLUMN_NAME_QUIZ_ID + " = ? AND "
                    + DbContract.QuizResult.COLUMN_NAME_ACCOUNT_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, quiz.getId());
            statement.setInt(2, account.getId());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
