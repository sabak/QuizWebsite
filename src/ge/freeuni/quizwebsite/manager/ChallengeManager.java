package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Quiz;
import ge.freeuni.quizwebsite.model.message.Challenge;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface ChallengeManager {

    /**
     * Returns challenge by given unique identifier.
     *
     * @param id Unique identifier.
     * @return Found challenge, null if not found
     */
    Challenge getChallenge(Integer id);

    /**
     * Sends quiz challenge to desired user account.
     *
     * @param from Challenger user account
     * @param to   Challenged user account
     * @param quiz Challenged quiz
     */
    void challengeUser(Account from, Account to, Quiz quiz);

    /**
     * Return list of all sent challenges applicable to given user.
     *
     * @param account Challenger user account
     * @return List of challenges
     */
    List<Challenge> getSentChallenges(Account account);

    /**
     * Return list of all received challenges applicable to given user.
     *
     * @param account Challenged user account
     * @return List of challenges
     */
    List<Challenge> getReceivedChallenges(Account account);

    /**
     * Accept incoming challenge.
     *
     * @param challenge Challenge to be accepted
     */
    void confirmChallenge(Challenge challenge);

    /**
     * Decline incoming challenge.
     *
     * @param challenge Challenge to be declined.
     */
    void declineChallenge(Challenge challenge);

}
