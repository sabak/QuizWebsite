package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.ChallengeManager;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Quiz;
import ge.freeuni.quizwebsite.model.message.Challenge;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Saba on 19-06-2016.
 */
public class ChallengeManagerDAO extends AbstractManagerDAO implements ChallengeManager {

    public static final String ATTRIBUTE_NAME = "challenge_manager";

    public ChallengeManagerDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Challenge getChallenge(Integer id) {
        return null;
    }

    @Override
    public void challengeUser(Account from, Account to, Quiz quiz) {

    }

    @Override
    public List<Challenge> getSentChallenges(Account account) {
        return null;
    }

    @Override
    public List<Challenge> getReceivedChallenges(Account account) {
        return null;
    }

    @Override
    public void confirmChallenge(Challenge challenge) {

    }

    @Override
    public void declineChallenge(Challenge challenge) {

    }

}
