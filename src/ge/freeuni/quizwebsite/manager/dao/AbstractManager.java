package ge.freeuni.quizwebsite.manager.dao;

import javax.sql.DataSource;

public abstract class AbstractManager {
    protected DataSource dataSource;

    public AbstractManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void changeDataSource(DataSource newDataSource) {
        this.dataSource = newDataSource;
    }

}
