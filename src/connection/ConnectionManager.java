package connection;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/game?autoReconnect=true&useSSL=false&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static DataSource dataSource;

    static {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
        poolProperties.setUrl(DB_URL);
        poolProperties.setUsername(USER);
        poolProperties.setPassword(PASSWORD);
        dataSource = new DataSource(poolProperties);
    }

    private ConnectionManager() {}

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
