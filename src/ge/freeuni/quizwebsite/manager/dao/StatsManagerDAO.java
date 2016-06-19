package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.OrderBy;
import ge.freeuni.quizwebsite.manager.StatsManager;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Quiz;
import ge.freeuni.quizwebsite.model.QuizResult;
import ge.freeuni.quizwebsite.model.QuizSummary;

import javax.sql.DataSource;
import java.sql.Time;
import java.util.List;

/**
 * Created by Saba on 19-06-2016.
 */
public class StatsManagerDAO extends AbstractManagerDAO implements StatsManager {

    public static final String ATTRIBUTE_NAME = "stats_manager";

    public StatsManagerDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<QuizResult> getTopResults(Quiz quiz, int limit) {
        return getTopResults(quiz, 0, limit);
    }

    @Override
    public List<QuizResult> getTopResults(Quiz quiz, int limitFrom, int limitTo) {
        return null;
    }

    @Override
    public QuizResult getBestScore(Quiz quiz, Account acc) {
        return null;
    }

    @Override
    public QuizSummary getSummaryStats(Quiz quiz) {
        return null;
    }

    @Override
    public List<Quiz> getPopularQuizzes(int limit) {
        return getPopularQuizzes(0, limit);
    }

    @Override
    public List<Quiz> getPopularQuizzes(int limitFrom, int limitTo) {
        return null;
    }

    @Override
    public List<Account> getHighestPerformers(Quiz quiz, int limit) {
        return getHighestPerformers(quiz, 0, limit);
    }

    @Override
    public List<Account> getHighestPerformers(Quiz quiz, int limitFrom, int limitTo) {
        return null;
    }

    @Override
    public List<Account> getHighestPerformers(Quiz quiz, Time time, int limit) {
        return getHighestPerformers(quiz, time, 0, limit);
    }

    @Override
    public List<Account> getHighestPerformers(Quiz quiz, Time time, int limitFrom, int limitTo) {
        return null;
    }

    @Override
    public List<QuizResult> getResults(Quiz quiz, OrderBy orderBy, int limit) {
        return getResults(quiz, orderBy, 0, limit);
    }

    @Override
    public List<QuizResult> getResults(Quiz quiz, OrderBy orderBy, int limitFrom, int limitTo) {
        return null;
    }

    @Override
    public List<QuizResult> getResults(Account account, Quiz quiz, OrderBy orderBy, int limit) {
        return getResults(account, quiz, orderBy, 0, limit);
    }

    @Override
    public List<QuizResult> getResults(Account account, Quiz quiz, OrderBy orderBy, int limitFrom, int limitTo) {
        return null;
    }

}
