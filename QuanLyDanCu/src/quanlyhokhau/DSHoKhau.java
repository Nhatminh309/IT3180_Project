package QuanLyDanCu.src.quanlyhokhau;

import QuanLyDanCu.src.giaodien.GiaoDienChung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DSHoKhau extends GiaoDienChung {

    private JTable table;

    public DSHoKhau() {
        super();

        // Create table and set its properties
        table = new JTable();
        table.setModel(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(table);

        // Add components to the panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add the panel to the rightPanel
        rightPanel.add(panel, BorderLayout.CENTER);

        // Load data into the table
        loadData();

        frame.setVisible(true);
    }

    private void loadData() {
        Connection connection = null;
        try {
            connection = getConnectDatabase();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Lỗi kết nối đến cơ sở dữ liệu.");
            return;
        }

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM ho_khau")
        ) {
            // Create a DefaultTableModel with columns
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Mã hộ khẩu");
            model.addColumn("Số nhà");
            model.addColumn("Tên đường");
            model.addColumn("Tên phường");
            model.addColumn("Tên quận");
            model.addColumn("Tên thành phố");
            model.addColumn("Đã xác nhận");
            model.addColumn("Số lượng xe máy");
            model.addColumn("Số lượng ô tô");
            model.addColumn("Diện tích");

            // Populate the table with data
            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("Ma_ho_khau"),
                        resultSet.getString("So_nha"),
                        resultSet.getString("Ten_duong"),
                        resultSet.getString("Ten_phuong"),
                        resultSet.getString("Ten_quan"),
                        resultSet.getString("Ten_thanh_pho"),
                        resultSet.getBoolean("Da_xac_nhan"),
                        resultSet.getInt("So_luong_xe_may"),
                        resultSet.getInt("So_luong_o_to"),
                        resultSet.getInt("Dien_tich")
                };
                model.addRow(row);
            }

            // Set the model to the table
            table.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Lỗi khi tải dữ liệu hộ khẩu: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new DSHoKhau();
    }
}
