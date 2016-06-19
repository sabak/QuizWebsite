package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.HistoryManager;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Quiz;
import ge.freeuni.quizwebsite.model.QuizResult;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Reaper on 19-06-2016.
 */
public class HistoryManagerDAO extends AbstractManagerDAO implements HistoryManager {

    public static final String ATTRIBUTE_NAME = "history_manager";

    public HistoryManagerDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<QuizResult> getHistory(Account acc, Quiz quiz) {
        return null;
    }

    @Override
    public void clearHistory(Quiz quiz) {

    }

    @Override
    public void clearHistory(Account account) {

    }

    @Override
    public void clearHistory(Quiz quiz, Account account) {

    }

}
