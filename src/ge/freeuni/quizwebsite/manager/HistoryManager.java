package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Quiz;
import ge.freeuni.quizwebsite.model.QuizResult;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface HistoryManager {

    public List<QuizResult> getHistory(Account acc, Quiz quiz);

    public void clearHistory(Quiz quiz);

    public void clearHistory(Account account);

    public void clearHistory(Quiz quiz, Account account);

}
