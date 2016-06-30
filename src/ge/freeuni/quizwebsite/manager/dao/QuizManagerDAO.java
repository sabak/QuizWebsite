package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AccountManager;
import ge.freeuni.quizwebsite.manager.QuizManager;
import ge.freeuni.quizwebsite.manager.dao.db.DbContract;
import ge.freeuni.quizwebsite.model.*;
import ge.freeuni.quizwebsite.util.MapUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Saba on 19-06-2016.
 */
public class QuizManagerDAO extends AbstractManagerDAO implements QuizManager {

    public static final String ATTRIBUTE_NAME = "quiz_manager";

    private AccountManager accountManager;

    private Map<QuestionType, String> questionTypes;

    public QuizManagerDAO(DataSource dataSource, AccountManager accountManager) {
        super(dataSource);
        this.accountManager = accountManager;
        questionTypes = new HashMap<>();
        questionTypes.put(QuestionType.FILL_IN_THE_BLANK, "Fill in the Blank");
        questionTypes.put(QuestionType.MULTIPLE_CHOICE, "Multiple Choice");
        questionTypes.put(QuestionType.PICTURE_RESPONSE, "Picture-Response");
        questionTypes.put(QuestionType.QUESTION_RESPONSE, "Question-Response");
    }

    @Override
    public Quiz getQuiz(Integer id) {
        Quiz quiz = null;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM " + DbContract.Quiz.TABLE_NAME + " WHERE "
                    + DbContract.Quiz.COLUMN_NAME_QUIZ_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String name = rs.getString(DbContract.Quiz.COLUMN_NAME_NAME);
                String description = rs.getString(DbContract.Quiz.COLUMN_NAME_DESCRIPTION);
                Timestamp dateCreated = rs.getTimestamp(DbContract.Quiz.COLUMN_NAME_DATE_CREATED);
                boolean hasRandomOrder = rs.getBoolean(DbContract.Quiz.COLUMN_NAME_HAS_RANDOM);
                boolean isMultiplePage = rs.getBoolean(DbContract.Quiz.COLUMN_NAME_MULTIPLE_PAGE);
                boolean isImmediatelyCorrected = rs.getBoolean(DbContract.Quiz.COLUMN_NAME_IMMEDIATE_CORRECTION);

                quiz = new Quiz(id, name, description, hasRandomOrder,
                        isImmediatelyCorrected, isMultiplePage ? PageType.MULTI_PAGE : PageType.ONE_PAGE,
                        dateCreated);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quiz;
    }

    @Override
    public boolean createQuiz(Quiz quiz, Account account) {
        try {
            Connection con = dataSource.getConnection();
            String query = "INSERT INTO " + DbContract.Quiz.TABLE_NAME + " (" +
                    DbContract.Quiz.COLUMN_NAME_ACCOUNT_ID + ", " +
                    DbContract.Quiz.COLUMN_NAME_DATE_CREATED + ", " +
                    DbContract.Quiz.COLUMN_NAME_NAME + ", " +
                    DbContract.Quiz.COLUMN_NAME_DESCRIPTION + ", " +
                    DbContract.Quiz.COLUMN_NAME_HAS_RANDOM + ", " +
                    DbContract.Quiz.COLUMN_NAME_MULTIPLE_PAGE + ", " +
                    DbContract.Quiz.COLUMN_NAME_IMMEDIATE_CORRECTION + ") VALUES ( ?, ?, ?, ?, ?, ?, ?); ";
            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1, account.getId());
            statement.setTimestamp(2, quiz.getDateCreated());
            statement.setString(3, quiz.getName());
            statement.setString(4, quiz.getDescription());
            statement.setBoolean(5, quiz.hasHasRandomOrder());
            statement.setBoolean(6, quiz.getPageType() == PageType.MULTI_PAGE);
            statement.setBoolean(7, quiz.isImmediatelyCorrected());
            statement.executeUpdate();

            con.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Question> getQuestions(Quiz quiz) {
        List<Question> questions = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT * FROM " + DbContract.Question.TABLE_NAME + " WHERE "
                    + DbContract.Question.COLUMN_NAME_QUIZ_ID + " = ? ORDER BY "
                    + DbContract.Question.COLUMN_NAME_QUESTION_IDX + " ASC;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, quiz.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer questionId = rs.getInt(DbContract.Question.COLUMN_NAME_QUESTION_ID);
                Integer typeId = rs.getInt(DbContract.Question.COLUMN_NAME_TYPE_ID);
                String questionText = rs.getString(DbContract.Question.COLUMN_NAME_QUESTION_TEXT);
                int questionIdx = rs.getInt(DbContract.Question.COLUMN_NAME_QUESTION_IDX);

                QuestionType type = getQuestionType(typeId);
                List<Answer> answers = getAnswers(questionId);
                Question question = new Question(questionId, type, questionText, questionIdx, answers);
                questions.add(question);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    private QuestionType getQuestionType(Integer typeId) {
        QuestionType type = null;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM " + DbContract.QuestionType.TABLE_NAME + " WHERE "
                    + DbContract.QuestionType.COLUMN_NAME_TYPE_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, typeId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String questionType = rs.getString(DbContract.QuestionType.COLUMN_NAME_TYPE);
                type = MapUtils.getKeyByValue(questionTypes, questionType);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return type;
    }

    private List<Answer> getAnswers(Integer questionId) {
        List<Answer> answers = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT * FROM " + DbContract.Answer.TABLE_NAME + " WHERE " +
                    DbContract.Answer.COLUMN_NAME_QUESTION_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, questionId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer answerId = rs.getInt(DbContract.Answer.COLUMN_NAME_ANSWER_ID);
                String answerText = rs.getString(DbContract.Answer.COLUMN_NAME_ANSWER);
                boolean isCorrect = rs.getBoolean(DbContract.Answer.COLUMN_NAME_CORRECT);

                Answer answer = new Answer(answerId, answerText, isCorrect);
                answers.add(answer);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    @Override
    public void addQuestions(Quiz quiz, List<Question> questions) {
        for (Question question : questions) {
            addQuestion(quiz, question);
        }
    }

    @Override
    public void addQuestion(Quiz quiz, Question question) {
        Integer id = insertQuestion(quiz, question);
        insertAnswers(id, question.getAnswers());
    }

    private Integer insertQuestion(Quiz quiz, Question question) {
        Integer newId = null;
        try {
            Connection con = dataSource.getConnection();
            String query = "INSERT INTO " + DbContract.Question.TABLE_NAME + " (" +
                    DbContract.Question.COLUMN_NAME_QUIZ_ID + ", " +
                    DbContract.Question.COLUMN_NAME_QUESTION_TEXT + ", " +
                    DbContract.Question.COLUMN_NAME_QUESTION_IDX + ", " +
                    DbContract.Question.COLUMN_NAME_TYPE_ID + ") VALUES (?, ?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, quiz.getId());
            statement.setString(2, question.getText());
            statement.setInt(3, question.getIndex());
            statement.setInt(4, questionTypeToId(question.getType()));
            statement.executeUpdate();
            ResultSet generatedKeysResultSet = statement.getGeneratedKeys();
            generatedKeysResultSet.next();
            newId = generatedKeysResultSet.getInt(1);

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }

    private void insertAnswers(Integer id, List<Answer> answers) {
        for (Answer answer : answers) {
            insertAnswer(id, answer);
        }
    }

    private void insertAnswer(Integer id, Answer answer) {
        try {
            Connection con = dataSource.getConnection();
            String query = "INSERT INTO " + DbContract.Answer.TABLE_NAME + " (" +
                    DbContract.Answer.COLUMN_NAME_QUESTION_ID + ", " +
                    DbContract.Answer.COLUMN_NAME_ANSWER + ", " +
                    DbContract.Answer.COLUMN_NAME_CORRECT + ") VALUES (?, ?, ?); ";
            PreparedStatement statement = con.prepareStatement(query);


            statement.setInt(1, id);
            statement.setString(2, answer.getText());
            statement.setBoolean(3, answer.isCorrect());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Integer questionTypeToId(QuestionType type) {
        Integer id = null;
        try {
            Connection con = dataSource.getConnection();
            String questionTypeStr = questionTypes.get(type);
            String query = "SELECT * FROM " + DbContract.QuestionType.TABLE_NAME + " WHERE "
                    + DbContract.QuestionType.COLUMN_NAME_TYPE + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, questionTypeStr);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                id = rs.getInt(DbContract.QuestionType.COLUMN_NAME_TYPE_ID);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public void removeQuiz(Quiz quiz) {
        try (Connection con = dataSource.getConnection()) {
            String query = "DELETE FROM " + DbContract.Quiz.TABLE_NAME + " WHERE "
                    + DbContract.Quiz.COLUMN_NAME_QUIZ_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, quiz.getId());
            statement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Quiz> getRecentlyCreatedQuizzes(int limit) {
        return getRecentlyCreatedQuizzes(0, limit);
    }

    @Override
    public List<Quiz> getRecentlyCreatedQuizzes(int limitFrom, int limitTo) {
        List<Quiz> quizzes = new ArrayList<>();
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT "+ DbContract.Quiz.COLUMN_NAME_QUIZ_ID +" FROM " + DbContract.Quiz.TABLE_NAME + " WHERE " +
                    DbContract.Quiz.COLUMN_NAME_QUIZ_ID  +
                    " ORDER BY " + DbContract.Quiz.COLUMN_NAME_DATE_CREATED + " DESC LIMIT ?, ? ;";

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, limitFrom);
            statement.setInt(2, limitTo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Quiz quiz = getQuiz(rs.getInt(DbContract.Quiz.COLUMN_NAME_QUIZ_ID));
                quizzes.add(quiz);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizzes;

    }

    @Override
    public List<Quiz> getTakenQuizzes(Account account, int limit) {
        return getTakenQuizzes(account, 0, limit);
    }

    @Override
    public List<Quiz> getTakenQuizzes(Account account, int limitFrom, int limitTo) {
        List<Quiz> quizzes = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT DISTINCT (" + DbContract.QuizResult.COLUMN_NAME_QUIZ_ID
                    + ") FROM " + DbContract.QuizResult.TABLE_NAME + " WHERE "
                    + DbContract.QuizResult.COLUMN_NAME_ACCOUNT_ID + " = ? LIMIT ?, ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account.getId());
            statement.setInt(2, limitFrom);
            statement.setInt(3, limitTo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Quiz quiz = getQuiz(rs.getInt(DbContract.QuizResult.COLUMN_NAME_QUIZ_ID));
                quizzes.add(quiz);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    @Override
    public List<Quiz> getCreatedQuizzes(Account account, int limit) {
        return getCreatedQuizzes(account, 0, limit);
    }

    @Override
    public List<Quiz> getCreatedQuizzes(Account account, int limitFrom, int limitTo) {
        List<Quiz> quizzes = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT " + DbContract.Quiz.COLUMN_NAME_QUIZ_ID
                    + " FROM " + DbContract.Quiz.TABLE_NAME + " WHERE "
                    + DbContract.Quiz.COLUMN_NAME_ACCOUNT_ID + " = ? LIMIT ?, ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account.getId());
            statement.setInt(2, limitFrom);
            statement.setInt(3, limitTo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Quiz quiz = getQuiz(rs.getInt(DbContract.Quiz.COLUMN_NAME_QUIZ_ID));
                quizzes.add(quiz);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    @Override
    public List<QuizResult> getRecentlyTakenResults(Quiz quiz, int limit) {
        return getRecentlyTakenResults(quiz, 0, limit);
    }

    @Override
    public QuizResult getQuizResultById(Integer id) {
        QuizResult quizResult = null;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM " + DbContract.QuizResult.TABLE_NAME + " WHERE "
                    + DbContract.QuizResult.COLUMN_NAME_RESULT_ID + " = ? ;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Integer score = rs.getInt(DbContract.QuizResult.COLUMN_NAME_SCORE);
                Integer accountId = rs.getInt(DbContract.QuizResult.COLUMN_NAME_ACCOUNT_ID);
                Time timeTaken = rs.getTime(DbContract.QuizResult.COLUMN_NAME_TIME_TAKEN);
                Timestamp submitDate = rs.getTimestamp(DbContract.QuizResult.COLUMN_NAME_SUBMIT_DATE);
                Account account = accountManager.getAccount(accountId);
                quizResult = new QuizResult(id, score, account, timeTaken, submitDate);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return quizResult;
    }

    @Override
    public List<QuizResult> getRecentlyTakenResults(Quiz quiz, int limitFrom, int limitTo) {
        List<QuizResult> quizResults = new ArrayList<>();
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT " + DbContract.QuizResult.COLUMN_NAME_RESULT_ID + " FROM " +
                    DbContract.QuizResult.TABLE_NAME + " WHERE "
                    + DbContract.QuizResult.COLUMN_NAME_QUIZ_ID + " = ? " +
                    "ORDER BY " + DbContract.QuizResult.COLUMN_NAME_SUBMIT_DATE + " DESC LIMIT ?, ?;";


            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, quiz.getId());
            statement.setInt(2, limitFrom);
            statement.setInt(3, limitTo);


            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(DbContract.QuizResult.COLUMN_NAME_RESULT_ID);
                QuizResult quizResult = getQuizResultById(id);
                quizResults.add(quizResult);
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizResults;
    }


    @Override
    public List<Quiz> getTakenQuizzes(int limit) {
        return getTakenQuizzes(0, limit);
    }

    @Override
    public List<Quiz> getTakenQuizzes(int limitFrom, int limitTo) {
        return null;
    }

}
