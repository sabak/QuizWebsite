package ge.freeuni.quizwebsite.model;

import java.sql.Timestamp;

public class Quiz {

    private Integer id;
    private String name;
    private String description;
    private boolean hasRandomOrder;
    private boolean isImmediatelyCorrected;
    private PageType pageType;
    private Timestamp dateCreated;

    public Quiz(String name, String description, boolean hasRandomOrder,
                boolean isImmediatelyCorrected, PageType pageType, Timestamp dateCreated) {
        this(null, name, description, hasRandomOrder, isImmediatelyCorrected, pageType, dateCreated);
    }

    public Quiz(Integer id, String name, String description, boolean hasRandomOrder,
                boolean isImmediatelyCorrected, PageType pageType, Timestamp dateCreated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hasRandomOrder = hasRandomOrder;
        this.isImmediatelyCorrected = isImmediatelyCorrected;
        this.pageType = pageType;
        this.dateCreated = dateCreated;
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

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Description: " + description + ", Is Random Ordered: " +
                hasRandomOrder + ", Is Immediately Corrected: " + isImmediatelyCorrected +
                ", Page Type: " + pageType + ", Date Created: " + dateCreated;
    }

}
