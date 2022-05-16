package repository.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private Properties jdbcProperties;


    public JdbcUtils(Properties properties) {
        jdbcProperties = properties;
    }

    private Connection instance = null;

    private Connection getNewConnection() {
        String url = jdbcProperties.getProperty("jdbc.url");
        String user = jdbcProperties.getProperty("jdbc.user");
        String pass = jdbcProperties.getProperty("jdbc.pass");

        Connection connection = null;

        try {

            if (user != null && pass != null) {
                connection = DriverManager.getConnection(url, user, pass);
            }
            else {
                connection = DriverManager.getConnection(url);
            }

        } catch (SQLException e) {
            System.out.println("Error getting connection " + e);
        }

        return connection;
    }

    public Connection getConnection() {
        try {

            if (instance == null || instance.isClosed()) {
                instance = getNewConnection();
            }

        } catch (SQLException e) {
            System.out.println("Error DB: " + e);
        }

        return instance;
    }
}
