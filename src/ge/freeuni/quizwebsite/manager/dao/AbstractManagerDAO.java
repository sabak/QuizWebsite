package ge.freeuni.quizwebsite.manager.dao;

import javax.sql.DataSource;

public abstract class AbstractManagerDAO {
    protected DataSource dataSource;

    public AbstractManagerDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void changeDataSource(DataSource newDataSource) {
        this.dataSource = newDataSource;
    }

}
