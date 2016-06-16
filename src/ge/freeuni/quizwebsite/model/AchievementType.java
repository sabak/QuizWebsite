package ge.freeuni.quizwebsite.model;

/**
 * Created by Saba on 6/14/2016.
 */
public enum AchievementType {
    /**
     * The user created a quiz.
     */
    AMATEUR_AUTHOR,

    /**
     * The user created five quizzes.
     */
    PROLIFIC_AUTHOR,

    /**
     * The user created ten quizzes.
     */
    PRODIGIOUS_AUTHOR,

    /**
     * The user took ten quizzes.
     */
    QUIZ_MACHINE,

    /**
     * The user had the highest score on a quiz. Note, once earned this
     * achievement does not go away if someone else later bests the high score.
     */
    I_AM_THE_GREATEST,

    /**
     * The user took a quiz in practice mode.
     */
    PRACTICE_MAKES_PERFECT
}
