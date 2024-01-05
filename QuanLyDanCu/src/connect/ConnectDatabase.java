package QuanLyDanCu.src.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    public String getStringURL() {
        String URL = "jdbc:postgresql://localhost:5432/postgres";
        return URL;
    }
    public Connection getConnectDatabase() throws SQLException {
        String URL = getStringURL();
        Connection connection = DriverManager.getConnection(URL, "postgres", "anhbopcolen");
        return connection;
    }
}
