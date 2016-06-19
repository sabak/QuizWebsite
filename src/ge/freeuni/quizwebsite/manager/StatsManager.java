package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Quiz;
import ge.freeuni.quizwebsite.model.QuizResult;
import ge.freeuni.quizwebsite.model.QuizSummary;

import java.sql.Time;
import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface StatsManager {

    /**
     * Returns top quiz results. Entries ordered by score, then ordered
     * by time taken (when tie). Amount limited by specified parameter.
     *
     * @param quiz  Target quiz
     * @param limit Number of consecutive quiz results to return
     * @return List of quiz results
     */
    List<QuizResult> getTopResults(Quiz quiz, int limit);

    /**
     * Returns top quiz results. Entries ordered by score, then ordered
     * by time taken (when tie). Amount limited by specified parameters.
     *
     * @param quiz      Target quiz
     * @param limitFrom Index to get quiz results from this point onwards
     * @param limitTo   Stop index
     * @return List of quiz results
     */
    List<QuizResult> getTopResults(Quiz quiz, int limitFrom, int limitTo);

    /**
     * Returns user's best result for given quiz.
     *
     * @param quiz Target quiz
     * @param acc  Given user account
     * @return Best quiz result
     */
    QuizResult getBestScore(Quiz quiz, Account acc);

    /**
     * Returns quiz summary
     *
     * @param quiz Target quiz
     * @return Quiz summary
     */
    QuizSummary getSummaryStats(Quiz quiz);

    /**
     * Returns most popular quizzes. Quizzes sorted by times taken,
     * amount limited by given parameter.
     *
     * @param limit Number of consecutive quizzes to return
     * @return List of quizzes
     */
    List<Quiz> getPopularQuizzes(int limit);

    /**
     * Returns most popular quizzes. Quizzes sorted by times taken,
     * amount limited by given parameters.
     *
     * @param limitFrom Index to get quizzes from this point onwards
     * @param limitTo   Stop index
     * @return List of quizzes
     */
    List<Quiz> getPopularQuizzes(int limitFrom, int limitTo);

    /**
     * Returns highest performer user accounts (all time) for given quiz, amount
     * limited by given parameter.
     *
     * @param quiz  Target quiz
     * @param limit Number of consecutive accounts to return
     * @return List of accounts
     */
    List<Account> getHighestPerformers(Quiz quiz, int limit);

    /**
     * Returns highest performer user accounts (all time) for given quiz, amount
     * limited by given parameters.
     *
     * @param quiz      Target quiz
     * @param limitFrom Index to get accounts from this point onwards
     * @param limitTo   Stop index
     * @return List of accounts
     */
    List<Account> getHighestPerformers(Quiz quiz, int limitFrom, int limitTo);

    /**
     * Returns highest performer user accounts (for specified time interval)
     * for given quiz, amount limited by given parameter.
     *
     * @param quiz  Target quiz
     * @param time  Recent time interval to query results from
     * @param limit Number of consecutive accounts to return
     * @return List of accounts
     */
    List<Account> getHighestPerformers(Quiz quiz, Time time, int limit);

    /**
     * Returns highest performer user accounts (for specified time interval)
     * for given quiz, amount limited by given parameters.
     *
     * @param quiz      Target quiz
     * @param time      Recent time interval to query results from
     * @param limitFrom Index to get accounts from this point onwards
     * @param limitTo   Stop index
     * @return List of accounts
     */
    List<Account> getHighestPerformers(Quiz quiz, Time time, int limitFrom, int limitTo);

    /**
     * Returns all results for given quiz, can be specified to be sorted with
     * any of the following criteria: date, score, time it took to complete.
     * Amount limited by specified parameter.
     *
     * @param quiz    Target quiz
     * @param orderBy Sorting flag
     * @param limit   Number of consecutive quiz results to return
     * @return List of quiz results
     */
    List<QuizResult> getResults(Quiz quiz, OrderBy orderBy, int limit);

    /**
     * Returns all results for given quiz, can be specified to be sorted with
     * any of the following criteria: date, score, time it took to complete.
     * Amount limited by specified parameters.
     *
     * @param quiz      Target quiz
     * @param orderBy   Sorting flag
     * @param limitFrom Index to get quiz results from this point onwards
     * @param limitTo   Stop index
     * @return List of quiz results
     */
    List<QuizResult> getResults(Quiz quiz, OrderBy orderBy, int limitFrom, int limitTo);

    /**
     * Returns all results for given quiz and account, can be specified to be sorted with
     * any of the following criteria: date, score, time it took to complete.
     * Amount limited by specified parameter.
     *
     * @param account Target account
     * @param quiz    Target quiz
     * @param orderBy Sorting flag
     * @param limit   Number of consecutive quiz results to return
     * @return List of quiz results
     */
    List<QuizResult> getResults(Account account, Quiz quiz, OrderBy orderBy, int limit);

    /**
     * Returns all results for given quiz and account, can be specified to be sorted with
     * any of the following criteria: date, score, time it took to complete.
     * Amount limited by specified parameters.
     *
     * @param account   Target account
     * @param quiz      Target quiz
     * @param orderBy   Sorting flag
     * @param limitFrom Index to get quiz results from this point onwards
     * @param limitTo   Stop index
     * @return List of quiz results
     */
    List<QuizResult> getResults(Account account, Quiz quiz, OrderBy orderBy, int limitFrom, int limitTo);

}
