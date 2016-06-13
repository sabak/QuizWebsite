package ge.freeuni.quizwebsite.model;

import java.util.ArrayList;

/**
 * multiple choice questions are different from all the other types,
 * by having a single correct answer and multiple choices (options)
 */
public class MultipleChoice extends Question {

    private String answer = "";
    private ArrayList<String> options = new ArrayList<>();

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        options.add(answer);
        this.answer = answer;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void addOption(String option) {
        this.options.add(option);
    }

    public void removeOption(String option) {
        this.options.remove(option);
    }

    public boolean checkAnswer(String ans) {
        if (ans == answer)
            return true;
        return false;
    }

    public boolean checkAnswer(int k) {
        if (options.get(k) == this.answer)
            return true;
        return false;
    }

}