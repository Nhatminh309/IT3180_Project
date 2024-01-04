package quanlydancu.src.connect;

import quanlydancu.src.dangnhap.DangNhap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    public String getStringURL() {
        String URL = "jdbc:postgresql://localhost:5432/quanlydancu";
        return URL;
    }
    public Connection getConnectDatabase() throws SQLException {
        String URL = getStringURL();
        Connection connection = DriverManager.getConnection(URL, "postgres", "Nhatminh@202");
        return connection;
    }

    public static void main(String[] args) {
        new ConnectDatabase();
    }
}