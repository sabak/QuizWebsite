package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Quiz;
import ge.freeuni.quizwebsite.model.QuizResult;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface HistoryManager {

    /**
     * Returns all quiz result for given user account and quiz.
     *
     * @param acc  Target user account
     * @param quiz Target quiz
     * @return List of quiz results
     */
    List<QuizResult> getHistory(Account acc, Quiz quiz);

    /**
     * Clears history for given quiz.
     *
     * @param quiz Target quiz
     */
    void clearHistory(Quiz quiz);

    /**
     * Clears history for given user account.
     *
     * @param account Target user account
     */
    void clearHistory(Account account);

    /**
     * Clears history for given quiz and user account.
     *
     * @param quiz    Target quiz.
     * @param account Target user account.
     */
    void clearHistory(Quiz quiz, Account account);

}
