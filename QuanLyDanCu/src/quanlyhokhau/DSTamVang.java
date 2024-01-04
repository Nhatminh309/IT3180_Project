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

public class DSTamVang extends GiaoDienChung {

    private JTable table;

    public DSTamVang() {
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
                ResultSet resultSet = statement.executeQuery("SELECT id, ma_nhan_khau, ngay_tam_vang, noi_den FROM tam_vang")
        ) {
            // Create a DefaultTableModel with columns
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Mã nhân khẩu");
            model.addColumn("Ngày tạm vắng");
            model.addColumn("Nơi đến");


            // Populate the table with data
            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("ID"),
                        resultSet.getInt("Ma_nhan_khau"),
                        resultSet.getDate("Ngay_tam_vang"),
                        resultSet.getString("Noi_den"),

                };
                model.addRow(row);
            }

            // Set the model to the table
            table.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Lỗi khi tải dữ liệu tạm vắng: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new DSTamVang();
    }
}
