package ge.freeuni.quizwebsite.model;

import java.sql.Timestamp;

/**
 * Created by Saba on 6/14/2016.
 */
public class Announcement {
    private Integer id;
    private String text;
    private Timestamp datePosted;

    public Announcement(String text, Timestamp datePosted) {
        this(null, text, datePosted);
    }

    public Announcement(Integer id, String text, Timestamp datePosted) {
        this.id = id;
        this.text = text;
        this.datePosted = datePosted;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Timestamp getDatePosted() {
        return datePosted;
    }

    @Override
    public String toString() {
        return "Announcement: " + text + "; Date posted: " + datePosted + ";";
    }
    
}
