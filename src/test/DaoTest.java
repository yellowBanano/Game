package test;

import connection.ConnectionManager;
import dao.AccountDao;
import entity.Account;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DaoTest {

    @Test
    public void getAllAccountsFromDao() {
        List<Account> all = AccountDao.getInstance().findAll();
        all.forEach(System.out::println);
        Assert.assertNotNull(all);
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        System.out.println(connection);
        Assert.assertNotNull(connection);
    }
}
