package quanlydancu.src.quanlyhokhau;

import quanlydancu.src.giaodien.GiaoDienChung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DSThanhVien extends GiaoDienChung {

    private JTextField txtMaHoKhau;
    private JButton btnTim;

    private JTable table;

    public DSThanhVien() {
        super();

        txtMaHoKhau = new JTextField(15);
        btnTim = new JButton("Tìm");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(createLabel("Mã Hộ Khẩu:"));
        inputPanel.add(txtMaHoKhau);
        inputPanel.add(btnTim);

        // Add components to the panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputPanel, BorderLayout.NORTH);

        table = new JTable();
        table.setModel(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add the panel to the rightPanel
        rightPanel.add(panel, BorderLayout.CENTER);

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

        // Set action for the "Tìm" button
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timNhanKhau();
            }
        });

        frame.setVisible(true);
    }

    private void timNhanKhau() {
        String maHoKhau = txtMaHoKhau.getText().trim();

        if (maHoKhau.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Vui lòng nhập Mã Hộ Khẩu.");
            return;
        }

        Connection connection = null;
        try {
            connection = getConnectDatabase();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Lỗi kết nối đến cơ sở dữ liệu.");
            return;
        }

        try {
            if (checkHoKhauExistence(connection, Integer.parseInt(maHoKhau))) {
                loadNhanKhauData(connection, Integer.parseInt(maHoKhau));
            } else {
                JOptionPane.showMessageDialog(frame, "Mã Hộ Khẩu không tồn tại.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Lỗi khi tìm Nhân Khẩu: " + ex.getMessage());
        }
    }

    private boolean checkHoKhauExistence(Connection connection, int maHoKhau) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Ho_khau WHERE Ma_ho_khau = ?")) {
            preparedStatement.setInt(1, maHoKhau);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    private void loadNhanKhauData(Connection connection, int maHoKhau) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT Ma_nhan_khau, Ho_va_ten FROM Nhan_khau WHERE Ma_ho_khau = ?")) {
            preparedStatement.setInt(1, maHoKhau);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create a DefaultTableModel with columns
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Mã Nhân Khẩu");
            model.addColumn("Họ và Tên");

            // Populate the table with data
            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("Ma_nhan_khau"),
                        resultSet.getString("Ho_va_ten"),
                };
                model.addRow(row);
            }

            // Set the model to the table
            table.setModel(model);
        }
    }

    private void quayVeQuanLyHoKhau() {
        new QuanLyHoKhau();
        frame.dispose();
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, leftPanel.getHeight() / 30));
        label.setForeground(Color.BLACK);
        return label;
    }
}
