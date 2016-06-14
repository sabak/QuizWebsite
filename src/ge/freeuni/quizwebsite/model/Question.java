package ge.freeuni.quizwebsite.model;

import java.util.List;

/**
 * Parent Class of all question-type classes
 */
public class Question {

    private Integer id;
    private QuestionType questionType;
    private String text;
    private int index;
    private List<Answer> answers;


    public Question(QuestionType questionType, String text, int index, List<Answer> answers) {
        this(null, questionType, text, index, answers);
    }

    public Question(Integer id, QuestionType questionType, String text, int index, List<Answer> answers) {
        this.id = id;
        this.questionType = questionType;
        this.text = text;
        this.index = index;
        this.answers = answers;
    }

    public Integer getId() {
        return id;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public QuestionType getType() {
        return questionType;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return index;
    }

}