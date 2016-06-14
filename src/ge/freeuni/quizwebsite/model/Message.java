package ge.freeuni.quizwebsite.model;

import java.sql.Timestamp;

/**
 * Created by Saba on 6/14/2016.
 */
public class Message {
    private Integer id;
    private String text;
    private Timestamp dateSent;
    private boolean isRead;

    public Message(Timestamp dateSent, String text, boolean isRead) {
        this(null, text, dateSent, isRead);
    }

    public Message(Integer id, String text, Timestamp dateSent, boolean isRead) {
        this.id = id;
        this.text = text;
        this.dateSent = dateSent;
        this.isRead = isRead;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Timestamp getDateSent() {
        return dateSent;
    }

    public boolean isRead() {
        return isRead;
    }
}
