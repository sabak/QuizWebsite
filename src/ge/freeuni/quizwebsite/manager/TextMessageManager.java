package ge.freeuni.quizwebsite.manager;

import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.message.Message;
import ge.freeuni.quizwebsite.model.message.TextMessage;

import java.util.List;

/**
 * Created by Saba on 6/17/2016.
 */
public interface TextMessageManager {

    public TextMessage getMessage(Integer id);

    public List<Message> getMessages(Account acc);

    public List<Message> getMessages(Account to, Account from);

    public void sendMessage(Message msg);

    public void markRead(Message msg);

    public void deleteMessage(Message msg);

}
