package ge.freeuni.quizwebsite.model;

import java.util.Date;

/**
 * stores a certain user's score of a certain quiz
 */
public class Score {

    private int timeTaken = 0;
    private double score = 0; //percentage
    private Date dateOfQuiz = new Date();

    public int getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getDateOfQuiz() {
        return dateOfQuiz;
    }

    public void setDateOfQuiz(Date dateOfQuiz) {
        this.dateOfQuiz = dateOfQuiz;
    }
}