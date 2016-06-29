package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AccountManager;
import ge.freeuni.quizwebsite.manager.ChallengeManager;
import ge.freeuni.quizwebsite.manager.QuizManager;
import ge.freeuni.quizwebsite.manager.dao.db.DbContract;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Quiz;
import ge.freeuni.quizwebsite.model.message.Challenge;
import ge.freeuni.quizwebsite.model.message.TextMessage;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saba on 19-06-2016.
 */
public class ChallengeManagerDAO extends AbstractManagerDAO implements ChallengeManager {

    public static final String ATTRIBUTE_NAME = "challenge_manager";

    private AccountManager accountManager;
    private QuizManager quizManager;

    public ChallengeManagerDAO(DataSource dataSource, AccountManager accountManager, QuizManager quizManager) {
        super(dataSource);
        this.accountManager = accountManager;
        this.quizManager = quizManager;
    }

    @Override
    public Challenge getChallenge(Integer id) {
        Challenge challenge = null;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM " + DbContract.Challenge.TABLE_NAME + " WHERE "
                    + DbContract.Challenge.COLUMN_NAME_CHALLENGE_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Integer senderId = rs.getInt(DbContract.Challenge.COLUMN_NAME_SENDER_ID);
                Integer receiverId = rs.getInt(DbContract.Challenge.COLUMN_NAME_RECEIVER_ID);
                Integer quizId = rs.getInt(DbContract.Challenge.COLUMN_NAME_QUIZ_ID);
                String dateStr = rs.getString(DbContract.Challenge.COLUMN_NAME_SEND_DATE);

                Account sender = accountManager.getAccount(senderId);
                Account receiver = accountManager.getAccount(receiverId);
                Quiz quiz = quizManager.getQuiz(quizId);

                challenge = new Challenge(id, quiz, sender, receiver, Timestamp.valueOf(dateStr));
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return challenge;
    }

    @Override
    public void challengeUser(Account from, Account to, Quiz quiz) {
        try (Connection con = dataSource.getConnection()) {
            String query = "INSERT INTO " + DbContract.Challenge.TABLE_NAME + " (" +
                    DbContract.Challenge.COLUMN_NAME_SENDER_ID + ", " +
                    DbContract.Challenge.COLUMN_NAME_RECEIVER_ID + ", " +
                    DbContract.Challenge.COLUMN_NAME_SEND_DATE + ", " +
                    DbContract.Challenge.COLUMN_NAME_QUIZ_ID + ") VALUES (?, ?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1, from.getId());
            statement.setInt(2, to.getId());
            statement.setString(3, getCurrentTimestamp().toString());
            statement.setInt(4, quiz.getId());
            statement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Challenge> getSentChallenges(Account account) {
        return getChallenges(account, true);
    }

    @Override
    public List<Challenge> getReceivedChallenges(Account account) {
        return getChallenges(account, true);
    }

    private List<Challenge> getChallenges(Account account, boolean isChallenger) {
        List<Challenge> challenges = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String col = isChallenger ? DbContract.Challenge.COLUMN_NAME_SENDER_ID : DbContract.Challenge.COLUMN_NAME_RECEIVER_ID;
            String query = "SELECT " + DbContract.Challenge.COLUMN_NAME_QUIZ_ID
                    + " FROM " + DbContract.Challenge.TABLE_NAME + " WHERE " +
                    col + " = ? ORDER BY " + DbContract.Challenge.COLUMN_NAME_SEND_DATE + " DESC;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Challenge challenge = getChallenge(rs.getInt(DbContract.Challenge.COLUMN_NAME_CHALLENGE_ID));
                challenges.add(challenge);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return challenges;
    }

    @Override
    public void confirmChallenge(Challenge challenge) {

    }

    @Override
    public void declineChallenge(Challenge challenge) {

    }

}
