package QuanLyDanCu.src.quanlyhokhau;

import QuanLyDanCu.src.giaodien.GiaoDienChung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.sun.glass.ui.Cursor.setVisible;

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

        // Add "Quay về" button
        JButton btnQuayVe = new JButton("Quay về");
        btnQuayVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new QuanLyHoKhau();
                frame.dispose();
            }
        });
        // Add the "Quay về" button to the rightPanel
        rightPanel.add(btnQuayVe, BorderLayout.SOUTH);

        frame.setVisible(true);

        frame.setVisible(true);
    }

    private void quayVeQuanLyHoKhau() {
        // Tạo đối tượng QuanLyHoKhau và hiển thị nó
        QuanLyHoKhau quanLyHoKhau = new QuanLyHoKhau();
        showFrame();
        frame.dispose(); // Đóng frame hiện tại nếu cần
    }
    public void showFrame() {
        // Make the frame visible
        setVisible(true);
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

}
