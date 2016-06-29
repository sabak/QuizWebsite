package ge.freeuni.quizwebsite.model;

import java.sql.Timestamp;

/**
 * Created by Saba on 6/14/2016.
 */
public class Announcement {

    private Integer id;
    private String text;
    private Account author;
    private Timestamp datePosted;

    public Announcement(String text, Account author, Timestamp datePosted) {
        this(null, text, author, datePosted);
    }

    public Announcement(Integer id, String text, Account author, Timestamp datePosted) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.datePosted = datePosted;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Account getAuthor() {
        return author;
    }

    public Timestamp getDatePosted() {
        return datePosted;
    }

    @Override
    public String toString() {
        return "ID: " + id + "; Announcement: " + text + "; Date posted: " + datePosted + ";";
    }

}
