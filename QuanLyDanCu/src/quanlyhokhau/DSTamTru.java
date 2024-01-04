package QuanLyDanCu.src.quanlyhokhau;

import QuanLyDanCu.src.giaodien.GiaoDienChung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DSTamTru extends GiaoDienChung {

    private JTable table;

    public DSTamTru() {
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
                ResultSet resultSet = statement.executeQuery("SELECT id, dia_chi_thuong_tru, ngay_dang_ky, thoi_han, ma_nhan_khau FROM so_tam_tru")
        ) {
            // Create a DefaultTableModel with columns
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Địa chỉ thường trú");
            model.addColumn("Ngày đăng ký");
            model.addColumn("Thời hạn");
            model.addColumn("Mã nhân khẩu");


            // Populate the table with data
            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("ID"),
                        resultSet.getString("Dia_chi_thuong_tru"),
                        resultSet.getDate("Ngay_dang_ky"),
                        resultSet.getDate("Thoi_han"),
                        resultSet.getInt("Ma_nhan_khau"),

                };
                model.addRow(row);
            }

            // Set the model to the table
            table.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Lỗi khi tải dữ liệu tạm trú: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new DSTamTru();
    }
}
