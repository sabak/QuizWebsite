package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Quiz;
import ge.freeuni.quizwebsite.model.message.Challenge;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface ChallengeManager {

    public Challenge getChallenge(Integer id);

    public void challengeUser(Account from, Account to, Quiz quiz);

    public List<Challenge> getChallenges(Account account);

    public void confirmChallenge(Challenge challenge);

    public void declineChallenge(Challenge challenge);

}
