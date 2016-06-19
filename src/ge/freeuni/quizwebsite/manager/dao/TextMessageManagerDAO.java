package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.TextMessageManager;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.message.Message;
import ge.freeuni.quizwebsite.model.message.TextMessage;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Saba on 19-06-2016.
 */
public class TextMessageManagerDAO extends AbstractManagerDAO implements TextMessageManager {

    public static final String ATTRIBUTE_NAME = "text_msg_manager";

    public TextMessageManagerDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public TextMessage getMessage(Integer id) {
        return null;
    }

    @Override
    public List<Message> getMessages(Account acc) {
        return null;
    }

    @Override
    public List<Message> getMessages(Account to, Account from) {
        return null;
    }

    @Override
    public void sendMessage(Message msg) {

    }

    @Override
    public void markRead(Message msg) {

    }

    @Override
    public void deleteMessage(Message msg) {

    }

}
