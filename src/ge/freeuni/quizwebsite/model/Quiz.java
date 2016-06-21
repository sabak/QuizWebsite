package ge.freeuni.quizwebsite.model;

import java.sql.Timestamp;
import java.util.List;

public class Quiz {

    private Integer id;
    private String name;
    private String description;
    private boolean hasRandomOrder;
    private boolean isImmediatelyCorrected;
    private PageType pageType;
    private Timestamp dateCreated;
    private List<Question> questions;

    public Quiz(String name, String description, boolean hasRandomOrder,
                boolean isImmediatelyCorrected, PageType pageType, Timestamp dateCreated,
                List<Question> questions) {
        this(null, name, description, hasRandomOrder, isImmediatelyCorrected, pageType, dateCreated, questions);
    }

    public Quiz(Integer id, String name, String description, boolean hasRandomOrder,
                boolean isImmediatelyCorrected, PageType pageType, Timestamp dateCreated,
                List<Question> questions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hasRandomOrder = hasRandomOrder;
        this.isImmediatelyCorrected = isImmediatelyCorrected;
        this.pageType = pageType;
        this.dateCreated = dateCreated;
        this.questions = questions;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns if the quiz is set to either randomize the order of the questions or
     * to always present them in the same order.
     *
     * @return true if randomized, false otherwise.
     */
    public boolean hasHasRandomOrder() {
        return hasRandomOrder;
    }

    /**
     * For multiple page quizzes, this setting determines whether the user will receive
     * immediate feedback on an answer, or if the quiz will only be graded once all the
     * questions have been seen and responded to. The immediate correction option will
     * work in conjunction with picture-response questions to create a flash- card type
     * quiz. The computer will bring up a flash card (i.e., a picture) the user will
     * respond with the answer and the computer will immediately provide feedback on
     * whether the answer was correct or not.
     *
     * @return true if immediately corrected, false otherwise
     */
    public boolean isImmediatelyCorrected() {
        return isImmediatelyCorrected;
    }

    public PageType getPageType() {
        return pageType;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "ID: " + id + "; Quiz: " + name + "; Description: " + description + "; " +
                (hasRandomOrder ? "Is random ordered; " : "Is not random ordered; ") +
                (isImmediatelyCorrected ? "Is immediately corrected; " : "Is not immediately corrected; ") +
                "Page Type: " + pageType + "; Date created: " + dateCreated + "; Questions {" + questions + "};";
    }

}

// Shame! Shame! Shame!
//package ge.freeuni.quizwebsite.model;
//
//import java.util.ArrayList;
//import java.util.Date;
//
///**
// * Each quiz has the following
// * 1. boolean that stores whether the questions of the quiz shall appear in a randomized order or not
// * 2. whether the quiz is displayed on a single page or not
// * 3. whether the response is graded immediately or after it's submitted
// * 4. whether the quiz is in practice mode or not
// * as well as quiz's name and description
// * and collection of questions
// * link to creator's userpage
// * list of best performers (of all time and last day)
// */
//public class Quiz {
//
//    private String name = "";
//    private String description = "";
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    private boolean randomized = false;
//    private boolean singlePage = false;
//    private boolean immediateGrade = false;
//    private boolean practiceMode = false;
//
//    public boolean isRandomized() {
//        return randomized;
//    }
//
//    public void setRandomized(boolean randomized) {
//        this.randomized = randomized;
//    }
//
//    public boolean isSinglePage() {
//        return singlePage;
//    }
//// Shame! Shame! Shame!
//    public void setSinglePage(boolean singlePage) {
//        this.singlePage = singlePage;
//    }
//
//    public boolean isImmediateGrade() {
//        return immediateGrade;
//    }
//
//    public void setImmediateGrade(boolean immediateGrade) {
//        this.immediateGrade = immediateGrade;
//    }
//
//    public boolean isPracticeMode() {
//        return practiceMode;
//    }
//
//    public void setPracticeMode(boolean practiceMode) {
//        this.practiceMode = practiceMode;
//    }
//
//    private ArrayList<Question> questions = new ArrayList<>();
//
//    public ArrayList<Question> getQuestions() {
//        return questions;
//    }
//
//    public void setQuestions(ArrayList<Question> questions) {
//        this.questions = questions;
//    }
//
//    public void addQuestion(Question question) {
//        this.questions.add(question);
//    }
//
//    public void removeQuestion(Question question) {
//        this.questions.remove(question);
//    }
//
//    private ArrayList<Score> scores = new ArrayList<>(); //always sorted by... well, score. highest first
//
//    public void addScore(Score score) {
//        if (this.scores.size() == 0) {
//            scores.add(score);
//        } else {
//            for (int i = 0; i < this.scores.size(); i++) {
//                if (score.getScore() >= this.scores.get(i).getScore()) {
//                    this.scores.add(i, score);
//                    break;
//                }
//            }
//            if (score.getScore() < this.scores.get(this.scores.size() - 1).getScore()) {
//                scores.add(score);
//            }
//        }
//    }
//
//    private int nHighScore = 10; // number of highest scores to be returened. 10 by default
//
//    public void setnHighScore(int n) {
//        nHighScore = n;
//    }
//
//    public int getnHighScore() {
//        return nHighScore;
//    }
//
//    public ArrayList<Score> getScoresAllTime() {
//        if (this.scores.size() <= nHighScore)
//            return this.scores;
//
//        ArrayList<Score> hs = new ArrayList<>();
//        for (int i = 0; i < nHighScore; i++) {
//            hs.add(scores.get(i));
//        }
//        return hs;
//    }
//
//    public ArrayList<Score> getScoresLastDay() {
//        ArrayList<Score> hs = new ArrayList<>();
//        hs.addAll(this.scores);
//        hs = removeOld(hs);
//
//        for (int i = nHighScore; i < hs.size(); i++) {
//            hs.remove(i);
//        }
//
//        return hs;
//    }
//
//    private ArrayList<Score> removeOld(ArrayList<Score> hs) {
//        Date date = new Date();//current time
//        date = new Date(date.getTime() - 1 * 24 * 3600 * 1000);
//
//        for (int i = 0; i < hs.size(); i++) {
//            Date temp = hs.get(i).getDateOfQuiz();
//            if (temp.before(date))
//                hs.remove(i);
//        }
//        return hs;
//    }
//
//} Shame! Shame! Shame!
