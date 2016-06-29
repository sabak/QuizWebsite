package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Question;
import ge.freeuni.quizwebsite.model.Quiz;
import ge.freeuni.quizwebsite.model.QuizResult;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface QuizManager {

    /**
     * Returns quiz by given unique identifier.
     *
     * @param id Unique identifier
     * @return Found quiz, null if not found
     */
    Quiz getQuiz(Integer id);

    /**
     * Creates/saves given quiz and returns with unique
     * identifier.
     *
     * @param quiz Quiz to be created.
     * @return true if successfully created, false otherwise
     */
    boolean createQuiz(Quiz quiz);

    /**
     * Returns list of all questions in the given quiz
     *
     * @param quiz Target quiz
     * @return List of all questions in the quiz
     */
    List<Question> getQuestions(Quiz quiz);

    /**
     * Adds questions to the given quiz
     *
     * @param quiz      Target quiz
     * @param questions List of questions to be added
     */
    void addQuestions(Quiz quiz, List<Question> questions);

    /**
     * Adds single question to the given quiz
     *
     * @param quiz     Target quiz
     * @param question Question to be added
     */
    void addQuestion(Quiz quiz, Question question);

    /**
     * Removes given quiz.
     *
     * @param quiz Quiz to be removed.
     */
    void removeQuiz(Quiz quiz);

    /**
     * Returns all recently created quizzes, amount limited by given
     * parameter.
     *
     * @param limit Number of consecutive recent quizzes to be returned.
     * @return List of recently created quizzes
     */
    List<Quiz> getRecentlyCreatedQuizzes(int limit);

    /**
     * Returns all recently created quizzes, amount limited by given
     * parameters.
     *
     * @param limitFrom Index to get quizzes from this point onwards
     * @param limitTo   Stop index
     * @return List of recently created quizzes
     */
    List<Quiz> getRecentlyCreatedQuizzes(int limitFrom, int limitTo);

    /**
     * Returns all taken quizzes for given user account, amount limited by
     * specified parameter.
     *
     * @param account Target user account
     * @param limit   Number of consecutive quizzes to be returned
     * @return List of quizzes
     */
    List<Quiz> getTakenQuizzes(Account account, int limit);

    /**
     * Returns all taken quizzes for given user account, amount limited by
     * specified parameters.
     *
     * @param account   Target user account
     * @param limitFrom Index to get quizzes from this point onwards
     * @param limitTo   Stop index
     * @return List of quizzes
     */
    List<Quiz> getTakenQuizzes(Account account, int limitFrom, int limitTo);

    /**
     * Returns quizzes created by given user account, amount limited by
     * specified parameter.
     *
     * @param account Target user account
     * @param limit   Number of consecutive quizzes to be returned
     * @return List of quizzes
     */
    List<Quiz> getCreatedQuizzes(Account account, int limit);

    /**
     * Returns quizzes created by given user account, amount limited by
     * specified parameters.
     *
     * @param account   Target user account.
     * @param limitFrom Index to get quizzes from this point onwards
     * @param limitTo   Stop index
     * @return List of quizzes
     */
    List<Quiz> getCreatedQuizzes(Account account, int limitFrom, int limitTo);

    /**
     * Returns recently taken quiz results, amount limited by specified parameter.
     *
     * @param quiz  Target quiz
     * @param limit Number of consecutive quiz results to be returned
     * @return List of quiz results
     */
    List<QuizResult> getRecentlyTakenResults(Quiz quiz, int limit);

    /**
     * Returns recently taken quiz results, amount limited by specified parameters.
     *
     * @param quiz      Target quiz
     * @param limitFrom Index to get quiz results from this point onwards
     * @param limitTo   Stop index
     * @return List of quiz results
     */
    List<QuizResult> getRecentlyTakenResults(Quiz quiz, int limitFrom, int limitTo);

    /**
     * Returns all taken quizzes, amount limited by specified parameter.
     *
     * @param limit Number of consecutive quizzes to be returned
     * @return List of quizzes
     */
    List<Quiz> getTakenQuizzes(int limit);

    /**
     * Returns all taken quizzes, amount limited by specified parameters.
     *
     * @param limitFrom Index to get quizzes from this point onwards
     * @param limitTo   Stop index
     * @return List of quizzes
     */
    List<Quiz> getTakenQuizzes(int limitFrom, int limitTo);

}
