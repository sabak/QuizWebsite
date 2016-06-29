package ge.freeuni.quizwebsite.manager.dao.db;

/**
 * Created by Saba on 20-06-2016.
 */
public final class DbContract {
    /*
     * To prevent someone from accidentally instantiating the contract class,
     * give it an empty constructor
     */
    public DbContract() {
    }

    /* Account table */
    public abstract static class Account {
        public static final String TABLE_NAME = "account";
        public static final String COLUMN_NAME_ACCOUNT_ID = "id";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_HASHED_PASSWORD = "hashed_password";
        public static final String COLUMN_NAME_EMAIL = "email_address";
        public static final String COLUMN_NAME_FIRST_NAME = "first_name";
        public static final String COLUMN_NAME_LAST_NAME = "last_name";
    }

    /* Admin table */
    public abstract static class Admin {
        public static final String TABLE_NAME = "admin";
        public static final String COLUMN_NAME_ADMIN_ID = "account_id";
    }

    /* Quiz table */
    public abstract static class Quiz {
        public static final String TABLE_NAME = "quiz";
        public static final String COLUMN_NAME_QUIZ_ID = "id";
        public static final String COLUMN_NAME_ACCOUNT_ID = "account_id";
        public static final String COLUMN_NAME_DATE_CREATED = "date_created";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_MULTIPLE_PAGE = "is_multiple_page";
        public static final String COLUMN_NAME_IMMEDIATE_CORRECTION = "is_immediate_correction";
    }

    /* Question Type table */
    public abstract static class QuestionType {
        public static final String TABLE_NAME = "question_type";
        public static final String COLUMN_NAME_TYPE_ID = "id";
        public static final String COLUMN_NAME_TYPE = "type";
    }

    /* Question table */
    public abstract static class Question {
        public static final String TABLE_NAME = "question";
        public static final String COLUMN_NAME_QUESTION_ID = "id";
        public static final String COLUMN_NAME_QUIZ_ID = "quiz_id";
        public static final String COLUMN_NAME_TYPE_ID = "type_id";
        public static final String COLUMN_NAME_QUESTION_TEXT = "question_text";
        public static final String COLUMN_NAME_QUESTION_IDX = "question_index";
    }

    /* Answers table */
    public abstract static class Answer {
        public static final String TABLE_NAME = "answer";
        public static final String COLUMN_NAME_ANSWER_ID = "id";
        public static final String COLUMN_NAME_QUESTION_ID = "question_id";
        public static final String COLUMN_NAME_ANSWER = "answer";
        public static final String COLUMN_NAME_CORRECT = "is_correct";
    }

    /* Quiz results table */
    public abstract static class QuizResult {
        public static final String TABLE_NAME = "quiz_result";
        public static final String COLUMN_NAME_RESULT_ID = "id";
        public static final String COLUMN_NAME_QUIZ_ID = "quiz_id";
        public static final String COLUMN_NAME_ACCOUNT_ID = "account_id";
        public static final String COLUMN_NAME_SCORE = "score";
        public static final String COLUMN_NAME_TIME_TAKEN = "time_taken";
        public static final String COLUMN_NAME_SUBMIT_DATE = "result_submit_date";
    }

    /* Question Type table */
    public abstract static class Friend {
        public static final String TABLE_NAME = "friend";
        public static final String COLUMN_NAME_ACCOUNT_1 = "account_id_1";
        public static final String COLUMN_NAME_ACCOUNT_2 = "account_id_2";
    }

    /* Achievement Type table */
    public abstract static class AchievementType {
        public static final String TABLE_NAME = "achievement_type";
        public static final String COLUMN_NAME_TYPE_ID = "id";
        public static final String COLUMN_NAME_ACHIEVEMENT = "name";
    }

    /* Achievement table */
    public abstract static class Achievement {
        public static final String TABLE_NAME = "account_id";
        public static final String COLUMN_NAME_TYPE_ID = "type_id";
        public static final String COLUMN_NAME_UNLOCK_DATE = "unlock_date";
    }

    /* Friend request table */
    public abstract static class FriendRequest {
        public static final String TABLE_NAME = "friend_request";
        public static final String COLUMN_NAME_REQUEST_ID = "id";
        public static final String COLUMN_NAME_SENDER_ID = "sender_id";
        public static final String COLUMN_NAME_RECEIVER_ID = "receiver_id";
        public static final String COLUMN_NAME_SEND_DATE = "send_date";
    }

    /* Challenge request table */
    public abstract static class Challenge {
        public static final String TABLE_NAME = "challenge_request";
        public static final String COLUMN_NAME_CHALLENGE_ID = "id";
        public static final String COLUMN_NAME_SENDER_ID = "sender_id";
        public static final String COLUMN_NAME_RECEIVER_ID = "receiver_id";
        public static final String COLUMN_NAME_SEND_DATE = "send_date";
        public static final String COLUMN_NAME_QUIZ_ID = "quiz_id";
    }

    /* Message table */
    public abstract static class Message {
        public static final String TABLE_NAME = "message";
        public static final String COLUMN_NAME_MESSAGE_ID = "id";
        public static final String COLUMN_NAME_SENDER_ID = "sender_id";
        public static final String COLUMN_NAME_RECEIVER_ID = "receiver_id";
        public static final String COLUMN_NAME_SEND_DATE = "send_date";
        public static final String COLUMN_NAME_MESSAGE_TEXT = "message_text";
        public static final String COLUMN_NAME_IS_READ = "is_read";
    }

    /* Announcement table */
    public abstract static class Announcement {
        public static final String TABLE_NAME = "announcement";
        public static final String COLUMN_NAME_ANNOUNCEMENT_ID = "id";
        public static final String COLUMN_NAME_ACCOUNT_ID = "account_id";
        public static final String COLUMN_NAME_ANNOUNCEMENT = "announcement_text";
        public static final String COLUMN_NAME_POST_DATE = "post_date";
    }

}
