package ge.freeuni.quizwebsite.model.message;

import ge.freeuni.quizwebsite.model.Account;

import java.sql.Timestamp;

/**
 * Created by Saba on 6/16/2016.
 */
public abstract class Message {

    protected Integer id;
    protected Account from;
    protected Account to;
    protected Timestamp dateSent;

    public Message(Account from, Account to, Timestamp dateSent) {
        this(null, from, to, dateSent);
    }

    public Message(Integer id, Account from, Account to, Timestamp dateSent) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.dateSent = dateSent;
    }

    public Integer getId() {
        return id;
    }

    public Account getSender() {
        return from;
    }

    public Account getTarget() {
        return to;
    }

    public Timestamp getDateSent() {
        return dateSent;
    }

    @Override
    public String toString() {
        return "{ ID: " + id + ", Sent From: " + from.getUsername() + ", Sent To: "
                + to.getUsername() + ", Date Sent: " + dateSent + " }";
    }

}
