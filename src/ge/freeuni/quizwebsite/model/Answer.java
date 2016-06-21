package ge.freeuni.quizwebsite.model;

/**
 * Created by Saba on 6/14/2016.
 */
public class Answer {

    private Integer id;
    private String text;
    private boolean isCorrect;

    public Answer(String text, boolean isCorrect) {
        this(null, text, isCorrect);
    }

    public Answer(Integer id, String text, boolean isCorrect) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    @Override
    public String toString() {
        return "ID: " + id + "; Answer: " + text + "; " + (isCorrect ? "Correct answer;" : "Incorrect answer");
    }

}
