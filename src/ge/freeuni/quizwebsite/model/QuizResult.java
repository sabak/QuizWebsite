package ge.freeuni.quizwebsite.model;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Saba on 6/14/2016.
 */
public class QuizResult {
    private Integer id;
    private int score;
    private Account account;
    private Time timeTaken;
    private Timestamp resultSubmitDate;

    public QuizResult(int score, Account account, Time timeTaken, Timestamp resultSubmitDate) {
        this(null, score, account, timeTaken, resultSubmitDate);
    }

    public QuizResult(Integer id, int score, Account account, Time timeTaken, Timestamp resultSubmitDate) {
        this.id = id;
        this.score = score;
        this.account = account;
        this.timeTaken = timeTaken;
        this.resultSubmitDate = resultSubmitDate;
    }

    public Integer getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public Account getAccount() {
        return account;
    }

    public Time getTimeTaken() {
        return timeTaken;
    }

    public Timestamp getResultSubmitDate() {
        return resultSubmitDate;
    }

    @Override
    public String toString() {
        return "Score: " + score + "; Completed by {" + account +
                "}; Time taken to complete quiz: " + timeTaken +
                "; Submitted at: " + resultSubmitDate + ";";
    }

}
