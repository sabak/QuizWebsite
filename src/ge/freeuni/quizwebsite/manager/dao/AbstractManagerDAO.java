package ge.freeuni.quizwebsite.manager.dao;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Date;

public abstract class AbstractManagerDAO {

    protected DataSource dataSource;

    public AbstractManagerDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void changeDataSource(DataSource newDataSource) {
        this.dataSource = newDataSource;
    }

    protected Timestamp getCurrentTimestamp() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

}
