package ge.freeuni.quizwebsite.model.message;

import ge.freeuni.quizwebsite.model.Account;

import java.sql.Timestamp;

/**
 * Created by Saba on 6/16/2016.
 */
public class FriendRequest extends Message {

    public FriendRequest(Account from, Account to, Timestamp dateSent) {
        super(from, to, dateSent);
    }

    public FriendRequest(Integer id, Account from, Account to, Timestamp dateSent) {
        super(id, from, to, dateSent);
    }

    @Override
    public String toString() {
        return "{ ID: " + id + ", Friend request sent from: " + from.getUsername() + ", Sent to: "
                + to.getUsername() + ", Date sent: " + dateSent + " }";
    }

}
