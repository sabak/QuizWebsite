package ge.freeuni.quizwebsite.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Each quiz has the following
 * 1. boolean that stores whether the questions of the quiz shall appear in a randomized order or not
 * 2. whether the quiz is displayed on a single page or not
 * 3. whether the response is graded immediately or after it's submitted
 * 4. whether the quiz is in practice mode or not
 * as well as quiz's name and description
 * and collection of questions
 * link to creator's userpage
 * list of best performers (of all time and last day)
 */
public class Quiz {

    private String name = "";
    private String description = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private boolean randomized = false;
    private boolean singlePage = false;
    private boolean immediateGrade = false;
    private boolean practiceMode = false;

    public boolean isRandomized() {
        return randomized;
    }

    public void setRandomized(boolean randomized) {
        this.randomized = randomized;
    }

    public boolean isSinglePage() {
        return singlePage;
    }

    public void setSinglePage(boolean singlePage) {
        this.singlePage = singlePage;
    }

    public boolean isImmediateGrade() {
        return immediateGrade;
    }

    public void setImmediateGrade(boolean immediateGrade) {
        this.immediateGrade = immediateGrade;
    }

    public boolean isPracticeMode() {
        return practiceMode;
    }

    public void setPracticeMode(boolean practiceMode) {
        this.practiceMode = practiceMode;
    }

    private ArrayList<Question> questions = new ArrayList<>();

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void removeQuestion(Question question) {
        this.questions.remove(question);
    }

    private ArrayList<Score> scores = new ArrayList<>(); //always sorted by... well, score. highest first

    public void addScore(Score score) {
        if (this.scores.size() == 0) {
            scores.add(score);
        } else {
            for (int i = 0; i < this.scores.size(); i++) {
                if (score.getScore() >= this.scores.get(i).getScore()) {
                    this.scores.add(i, score);
                    break;
                }
            }
            if (score.getScore() < this.scores.get(this.scores.size() - 1).getScore()) {
                scores.add(score);
            }
        }
    }

    private int nHighScore = 10; // number of highest scores to be returened. 10 by default

    public void setnHighScore(int n) {
        nHighScore = n;
    }

    public int getnHighScore() {
        return nHighScore;
    }

    public ArrayList<Score> getScoresAllTime() {
        if (this.scores.size() <= nHighScore)
            return this.scores;

        ArrayList<Score> hs = new ArrayList<>();
        for (int i = 0; i < nHighScore; i++) {
            hs.add(scores.get(i));
        }
        return hs;
    }

    public ArrayList<Score> getScoresLastDay() {
        ArrayList<Score> hs = new ArrayList<>();
        hs.addAll(this.scores);
        hs = removeOld(hs);

        for (int i = nHighScore; i < hs.size(); i++) {
            hs.remove(i);
        }

        return hs;
    }

    private ArrayList<Score> removeOld(ArrayList<Score> hs) {
        Date date = new Date();//current time
        date = new Date(date.getTime() - 1 * 24 * 3600 * 1000);

        for (int i = 0; i < hs.size(); i++) {
            Date temp = hs.get(i).getDateOfQuiz();
            if (temp.before(date))
                hs.remove(i);
        }
        return hs;
    }

}