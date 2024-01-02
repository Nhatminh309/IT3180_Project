package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    public String getStringURL() {
        String URL = "jdbc:postgresql://localhost:5432/QuanLyDanCu";
        return URL;
    }
    public Connection getConnectDatabase() throws SQLException {
        String URL = getStringURL();
        Connection connection = DriverManager.getConnection(URL, "postgres", "271203");
        return connection;
    }
}
