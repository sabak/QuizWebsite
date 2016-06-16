package ge.freeuni.quizwebsite.model;

/**
 * Created by Saba on 16-06-2016.
 */
public class QuizSummary {
    private int timesTaken;
    private float successPercentage;

    public QuizSummary(int timesTaken, float successPercentage) {
        this.timesTaken = timesTaken;
        this.successPercentage = successPercentage;
    }

    public int getTimesTaken() {
        return timesTaken;
    }

    public float getSuccessPercentage() {
        return successPercentage;
    }

}
