package ge.freeuni.quizwebsite.manager.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager extends AbstractManager {
    public static final String ATTRIBUTE_NAME = "account_manager";

    public AccountManager(DataSource dataSource) {
        super(dataSource);
    }

    public boolean usernameExists(String username) {
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM account WHERE username=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, "saba");
            ResultSet result = statement.executeQuery();
            boolean contains = result.next();
            con.close();

            return contains;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
