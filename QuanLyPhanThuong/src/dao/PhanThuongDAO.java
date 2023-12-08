package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;

import database.JDBCUtil;
import modern.PhanThuong;

public class PhanThuongDAO implements DAOInterface<PhanThuong>{

    public static PhanThuongDAO getInstance(){
        return new PhanThuongDAO();
    }

    @Override
    public int insert(PhanThuong t) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO phan_thuong VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
        
            st.setInt(1, t.getMaSoPhanThuong());
            st.setString(2, t.getDipLe());
            st.setString(3, t.getLoaiPhanThuong());
            st.setDouble(4, t.getGiaTri());
            st.setDate(5, t.getNgayDangKy());
            st.setInt(6, t.getMaNhanKhau());
            st.setInt(7, t.getSoLuong());
        
            int rowsAffected = st.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        
            JDBCUtil.closeConnection(connection);
        
            return rowsAffected;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    

    @Override
    public int delete(PhanThuong t) {

        return 0;
    }

    @Override
    public ArrayList<PhanThuong> selectAll() {
        ArrayList<PhanThuong> result = new ArrayList<>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM phan_thuong";
            PreparedStatement st = connection.prepareStatement(sql);
    
            ResultSet rs = st.executeQuery();
    
            while (rs.next()) {
                int maPhanThuong = rs.getInt(1);
                String diple = rs.getString(2);
                String loaiPhanThuong = rs.getString(3);
                int giaTri = rs.getInt(4);
                Date date = rs.getDate(5);
                int maNhanKhau = rs.getInt(6);
                int soLuong = rs.getInt(7);
                
                PhanThuong temp = new PhanThuong(diple, loaiPhanThuong, date, soLuong, maNhanKhau);
                result.add(temp);
            }
    
            JDBCUtil.closeConnection(connection);
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public ArrayList<PhanThuong> selectByCondition(String condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PhanThuong selectByID(PhanThuong t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int update(PhanThuong t) {
        // TODO Auto-generated method stub
        return 0;
    }

}
