package ge.freeuni.quizwebsite.model;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Saba on 6/14/2016.
 */
public class QuizResult {
    private Integer id;
    private Time timeTaken;
    private Timestamp resultSubmitDate;

    public QuizResult(Time timeTaken, Timestamp resultSubmitDate) {
        this(null, timeTaken, resultSubmitDate);
    }

    public QuizResult(Integer id, Time timeTaken, Timestamp resultSubmitDate) {
        this.id = id;
        this.timeTaken = timeTaken;
        this.resultSubmitDate = resultSubmitDate;
    }

    public Integer getId() {
        return id;
    }

    public Time getTimeTaken() {
        return timeTaken;
    }

    public Timestamp getResultSubmitDate() {
        return resultSubmitDate;
    }
}
