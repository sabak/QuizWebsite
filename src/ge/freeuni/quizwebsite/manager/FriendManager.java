package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.message.FriendRequest;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface FriendManager {

    /**
     * Returns friend request by given unique identifier.
     *
     * @param id Unique identifier
     * @return Found friend request, null if not found
     */
    FriendRequest getFriendRequest(Integer id);

    /**
     * Returns all friend requests applicable to given user
     *
     * @param account Target user account
     * @return List of friend requests
     */
    List<FriendRequest> getFriendRequests(Account account);

    /**
     * Requests friendship between two user accounts.
     *
     * @param sender   User account who requested
     * @param receiver Target user account
     */
    void requestFriendship(Account sender, Account receiver);

    /**
     * Confirms friend request.
     *
     * @param request Request to be confirmed.
     */
    void confirmFriendship(FriendRequest request);

    /**
     * Declines friend request.
     *
     * @param request Request to be declined.
     */
    void declineFriendship(FriendRequest request);

    /**
     * Removes friend.
     *
     * @param account  Given user account
     * @param toRemove Friend to be removed
     */
    void removeFriend(Account account, Account toRemove);

    /**
     * Returns all friends for given account
     *
     * @param account Target user account
     * @return List of friends
     */
    List<Account> getFriends(Account account);

}
