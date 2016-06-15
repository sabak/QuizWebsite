package ge.freeuni.quizwebsite.model;

/**
 * Created by Saba on 6/14/2016.
 */

public enum QuestionType {
    /**
     * Question-Response—This is a standard text question with an appropriate text response.
     * For example: Who was President during the Bay of Pigs fiasco?
     */
    QUESTION_RESPONSE,

    /**
     * Fill in the Blank—This is similar to standard Question-Response, except a blank can go
     * anywhere within a question. For example: “One of President Lincoln’s most famous
     * speeches was the __________ Address.”
     */
    FILL_IN_THE_BLANK,

    /**
     * Multiple Choice—Allow the user to select from one of a number of possible provided answers.
     * Please present multiple-choice questions using radio buttons—this should not be treated as
     * a Question-Response question where the user enters an “A”, “B”, or “C” into a blank textfield.
     */
    MULTIPLE_CHOICE,

    /**
     * Picture-Response Questions—In a picture response question, the system will display an image,
     * and the user will provide a text response to the image. Here are some examples of
     * picture-response questions. The system displays an image of a bird, the user responds with the
     * name of the bird species; the system displays an image of a US President, the user responds with
     * the name of the president; the system displays a chemical structure of a molecule, the user
     * responds with the name of the molecule.
     */
    PICTURE_RESPONSE
}
