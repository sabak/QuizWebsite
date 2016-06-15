package ge.freeuni.quizwebsite.model;

/**
 * Created by Saba on 6/14/2016.
 */
public enum PageType {
    /**
     * Allow the quiz writer to determine if all the questions should appear on a
     * single webpage, with a single submit button, or if the quiz should display
     * a single question allow the user to submit the answer, then display another
     * question. The one-question-per-page approach will work well for a flash-card
     * style quiz, where the website flashes up an image or photograph and asks for
     * a response, followed by a new page with a new image. Single-page quizzes will
     * be good for most other quiz types.
     */
    ONE_PAGE,

    /**
     * Allow the quiz writer to determine if all the questions should appear on a
     * single webpage, with a single submit button, or if the quiz should display
     * a single question allow the user to submit the answer, then display another
     * question. The one-question-per-page approach will work well for a flash-card
     * style quiz, where the website flashes up an image or photograph and asks for
     * a response, followed by a new page with a new image. Single-page quizzes will
     * be good for most other quiz types.
     */
    MULTI_PAGE
}
