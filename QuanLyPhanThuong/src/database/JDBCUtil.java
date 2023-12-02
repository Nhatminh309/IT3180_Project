package database;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil{
    public static Connection getConnection() {
        Connection connection = null;

        try {
            // Đăng ký Driver
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/quan_ly_cap_phat_thuong";
            String username = "postgres";
            String password = "truong2??3@";

            // Tạo kết nối
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ theo ý của bạn
        }

        return connection;
    }

    public static void closeConnection(Connection connection){
        try {
            if(connection!=null){connection.close();}
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
