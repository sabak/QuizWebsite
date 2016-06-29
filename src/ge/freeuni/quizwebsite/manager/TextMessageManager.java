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
     * Returns all sent messages for given account.
     *
     * @param acc Target user account.
     * @return List of messages.
     */
    List<TextMessage> getSentMessages(Account acc);

    /**
     * Returns all received messages for given account.
     *
     * @param acc Target user account.
     * @return List of messages.
     */
    List<TextMessage> getReceivedMessages(Account acc);

    /**
     * Returns conversation members for given account.
     *
     * @param acc Target user account
     * @return List if accounts having conversation with
     */
    List<Account> getConversationsMember(Account acc);

    /**
     * Returns a conversation between two accounts.
     *
     * @param to   First user account
     * @param from Second user account
     * @return List of messages between two accounts
     */
    List<TextMessage> getMessages(Account to, Account from);

    /**
     * Sends message.
     *
     * @param msg Message to be sent
     * @return true if message was successfully sent, false otherwise
     */
    boolean sendMessage(TextMessage msg);

    /**
     * Marks message as read.
     *
     * @param msg Message to be marked as read
     * @return updated text message
     */
    TextMessage markRead(TextMessage msg);

    /**
     * Deletes message.
     *
     * @param msg Message to be deleted.
     */
    void deleteMessage(TextMessage msg);

}
