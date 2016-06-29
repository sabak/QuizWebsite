package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AccountManager;
import ge.freeuni.quizwebsite.manager.FriendManager;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.message.FriendRequest;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Saba on 19-06-2016.
 */
public class FriendManagerDAO extends AbstractManagerDAO implements FriendManager {

    public static final String ATTRIBUTE_NAME = "friend_manager";

    private AccountManager accountManager;

    public FriendManagerDAO(DataSource dataSource, AccountManager accountManager) {
        super(dataSource);
        this.accountManager = accountManager;
    }

    @Override
    public FriendRequest getFriendRequest(Integer id) {
        return null;
    }

    @Override
    public List<FriendRequest> getFriendRequests(Account account) {
        return null;
    }

    @Override
    public void requestFriendship(Account sender, Account receiver) {

    }

    @Override
    public void confirmFriendship(FriendRequest request) {

    }

    @Override
    public void declineFriendship(FriendRequest request) {

    }

    @Override
    public void removeFriend(Account account, Account toRemove) {

    }

    @Override
    public List<Account> getFriends(Account account) {
        return null;
    }

}
