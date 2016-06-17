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

    // First ordered by score, then ordered by time taken (if tie)
    public List<QuizResult> getTopResults(Quiz quiz, int limit);

    //First ordered by score, then ordered by time taken (if tie)
    public List<QuizResult> getTopResults(Quiz quiz, int limitFrom, int limitTo);

    public QuizResult getBestScore(Quiz quiz, Account acc);

    public QuizSummary getSummaryStats(Quiz quiz);

    //Quizzes sorted by times taken
    public List<Quiz> getPopularQuizzes(int limit);

    public List<Quiz> getPopularQuizzes(int limitFrom, int limitTo);

    public List<Quiz> getHighestPerformers(Quiz quiz, int limit);

    public List<Quiz> getHighestPerformers(Quiz quiz, int limitFrom, int limitTo);

    public List<Quiz> getHighestPerformers(Quiz quiz, Time time, int limit);

    public List<Quiz> getHighestPerformers(Quiz quiz, Time time, int limitFrom, int limitTo);

    public List<QuizResult> getResults(Quiz quiz, OrderBy orderBy, int limit);

    public List<QuizResult> getResults(Quiz quiz, OrderBy orderBy, int limitFrom, int limitTo);

    public List<QuizResult> getResults(Account acc, Quiz quiz, OrderBy orderBy, int limit);

    public List<QuizResult> getResults(Account acc, Quiz quiz, OrderBy orderBy, int limitFrom, int limitTo);

}
