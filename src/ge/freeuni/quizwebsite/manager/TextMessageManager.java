package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.message.Message;
import ge.freeuni.quizwebsite.model.message.TextMessage;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface TextMessageManager {

    /**
     * Returns message by given unique identifier.
     *
     * @param id Unique identifier
     * @return Found message, null if not found
     */
    TextMessage getMessage(Integer id);

    /**
     * Returns all messages for given account (sent and received).
     *
     * @param acc Target user account.
     * @return List of messages.
     */
    List<Message> getMessages(Account acc);

    /**
     * Returns a conversation between two accounts.
     *
     * @param to   First user account
     * @param from Second user account
     * @return List of messages between two accounts
     */
    List<Message> getMessages(Account to, Account from);

    /**
     * Sends message.
     *
     * @param msg Message to be sent
     */
    void sendMessage(Message msg);

    /**
     * Marks message as read.
     *
     * @param msg Message to be marked as read
     */
    void markRead(Message msg);

    /**
     * Deletes message.
     *
     * @param msg Message to be deleted.
     */
    void deleteMessage(Message msg);

}
