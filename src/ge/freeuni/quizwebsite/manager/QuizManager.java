package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Quiz;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface QuizManager {

    public Quiz getQuiz(Integer id);

    public Quiz createQuiz(Quiz quiz);

    public void removeQuiz(Quiz quiz);

    public List<Quiz> getRecentlyCreatedQuizzes(int limit);

    public List<Quiz> getRecentlyCreatedQuizzes(int limitFrom, int limitTo);

    public List<Quiz> getTakenQuizzes(Account acc, int limit);

    public List<Quiz> getTakenQuizzes(Account acc, int limitFrom, int limitTo);

    public List<Quiz> getCreatedQuizzes(Account acc, int limit);

    public List<Quiz> getCreatedQuizzes(Account acc, int limitFrom, int limitTo);

    public List<Quiz> getRecentlyTakenResults(Quiz quiz, int limit);

    public List<Quiz> getRecentlyTakenResults(Quiz quiz, int limitFrom, int limitTo);

    public List<Quiz> getTakenQuizzes(int limit);

    public List<Quiz> getTakenQuizzes(int limitFrom, int limitTo);

}
