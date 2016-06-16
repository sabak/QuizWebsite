package ge.freeuni.quizwebsite.model.message;

import ge.freeuni.quizwebsite.model.Account;

import java.sql.Timestamp;

/**
 * Created by Saba on 6/14/2016.
 */
public class TextMessage extends Message {
    private String text;
    private boolean isRead;

    public TextMessage(String text, Account from, Account to, Timestamp dateSent, boolean isRead) {
        this(null, text, from, to, dateSent, isRead);
    }

    public TextMessage(Integer id, String text, Account from, Account to, Timestamp dateSent, boolean isRead) {
        super(id, from, to, dateSent);
        this.text = text;
        this.isRead = isRead;
    }

    public String getText() {
        return text;
    }

    public boolean isRead() {
        return isRead;
    }

    @Override
    public String toString() {
        return "TextMessage: " + text + "; Sent at: " + dateSent + "; " + (isRead ? "Seen;" : "Unread;");
    }

}
