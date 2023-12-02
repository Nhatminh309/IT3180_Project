package test;

import java.sql.Connection;
import java.sql.Statement;

import database.JDBCUtil;

public class TestJDBCUtil {
    public static void main(String[] args) {
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            String sql = "INSERT into dangnhap VALUES('administration','admin1','admin');";
            int rowsAffected = st.executeUpdate(sql);
            System.out.println("Rows affected: " + rowsAffected);
            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
