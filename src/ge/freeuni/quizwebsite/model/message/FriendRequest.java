package ge.freeuni.quizwebsite.model.message;

import ge.freeuni.quizwebsite.model.Account;

import java.sql.Timestamp;

/**
 * Created by Saba on 6/16/2016.
 */
public class FriendRequest extends Message {

    public FriendRequest(Account from, Account to, Timestamp dateSent) {
        this(null, from, to, dateSent);
    }

    public FriendRequest(Integer id, Account from, Account to, Timestamp dateSent) {
        super(id, from, to, dateSent);
    }

    @Override
    public String toString() {
        return "Friend Request from: " + from + "; to: " + to + "; " + "Sent at: " + dateSent + ";";
    }

}
