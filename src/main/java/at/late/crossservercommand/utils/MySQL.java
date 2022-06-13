package at.late.crossservercommand.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private final String host = Config.configuration.getString("MSQL." + "host", "localhost");
    private final String port = Config.configuration.getString("MSQL." + "port", "3306");
    private final String database = Config.configuration.getString("MSQL." + "database", "database");
    private final String username = Config.configuration.getString("MSQL." + "username", "root");
    private final String password = Config.configuration.getString("MSQL." + "password", "password");

    private Connection connection;

    public boolean isConnected() {
        return (connection  == null ? false: true);
    }
    public void connect() throws ClassNotFoundException, SQLException {
        if(!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", username, password);
        }
    }
    public void disconnect() {
        if(isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
