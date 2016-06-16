package ge.freeuni.quizwebsite.model;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Saba on 6/14/2016.
 */
public class QuizResult {
    private Integer id;
    private int score;
    private String username;
    private Time timeTaken;
    private Timestamp resultSubmitDate;

    public QuizResult(int score, String username, Time timeTaken, Timestamp resultSubmitDate) {
        this(null, score, username, timeTaken, resultSubmitDate);
    }

    public QuizResult(Integer id, int score, String username, Time timeTaken, Timestamp resultSubmitDate) {
        this.id = id;
        this.score = score;
        this.username = username;
        this.timeTaken = timeTaken;
        this.resultSubmitDate = resultSubmitDate;
    }

    public Integer getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }

    public Time getTimeTaken() {
        return timeTaken;
    }

    public Timestamp getResultSubmitDate() {
        return resultSubmitDate;
    }

    @Override
    public String toString() {
        return "Score: " + score + "; Completed by: " + username +
                "; Time taken to complete quiz: " + timeTaken +
                "; Submitted at: " + resultSubmitDate + ";";
    }

}
