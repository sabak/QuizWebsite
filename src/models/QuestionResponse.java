package models;


import java.util.ArrayList;

/**
 * Question-Response class
 * text question
 * multiple correct answers
 * user enters single correct answer
 */
public class QuestionResponse extends Question{

    //list of all the correct answers
    private ArrayList<String> Answers = new ArrayList<>();

    public ArrayList<String> getcAnswers() {
        return Answers;
    }

    public void setAnswers(ArrayList<String> Answers) {
        this.Answers = Answers;
    }

    public void addAnswer(String answer){
        this.Answers.add(answer);
    }

    public void removeAnswer(String answer){
        this.Answers.remove(answer);
    }

    public boolean checkAnswer(String answer){
        if (Answers.contains(answer))
            return true;
        return false;
    }
}