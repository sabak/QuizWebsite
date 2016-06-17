package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.message.FriendRequest;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface FriendManager {

    public FriendRequest getFriendRequest(Integer id);

    public List<FriendRequest> getFriendRequests(Account account);

    public void requestFriendship(Account sender, Account receiver);

    public void confirmFriendship(FriendRequest request);

    public void declineFriendship(FriendRequest request);

    public void removeFriend(Account acc, Account toRemove);

    public List<Account> getFriends(Account acc);

}
