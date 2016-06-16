package ge.freeuni.quizwebsite.model.message;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Quiz;

import java.sql.Timestamp;

/**
 * Created by Saba on 6/14/2016.
 */
public class Challenge extends Message {

    private Quiz quiz;

    public Challenge(Quiz quiz, Account from, Account to, Timestamp dateSent, boolean isRead) {
        this(null, quiz, from, to, dateSent, isRead);
    }

    public Challenge(Integer id, Quiz quiz, Account from, Account to, Timestamp dateSent, boolean isRead) {
        super(id, from, to, dateSent);
        this.quiz = quiz;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    @Override
    public String toString() {
        return "Challenge sent at: " + dateSent + ";";
    }

}