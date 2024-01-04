package quanlydancu.src.quanlyhokhau;

import quanlydancu.src.giaodien.GiaoDienChung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoiChuHo extends GiaoDienChung {

    private JTextField txtMaHoKhau;
    private JTextField txtMaChuHoCu;
    private JTextField txtMaChuHoMoi;
    private JButton btnDoiChuHo;

    public DoiChuHo() {
        super();

        txtMaHoKhau = new JTextField(50);
        txtMaChuHoCu = new JTextField(50);
        txtMaChuHoMoi = new JTextField(50);
        btnDoiChuHo = new JButton("Đổi Chủ Hộ");


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2));
        inputPanel.add(createLabel("Mã Hộ Khẩu:"));
        inputPanel.add(txtMaHoKhau);
        inputPanel.add(createLabel("Mã Chủ Hộ Cũ:"));
        inputPanel.add(txtMaChuHoCu);
        inputPanel.add(createLabel("Mã Chủ Hộ Mới:"));
        inputPanel.add(txtMaChuHoMoi);
        inputPanel.add(new JLabel());
        inputPanel.add(btnDoiChuHo);

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

        btnDoiChuHo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maHoKhau = txtMaHoKhau.getText().trim();
                String maChuHoCu = txtMaChuHoCu.getText().trim();
                String maChuHoMoi = txtMaChuHoMoi.getText().trim();

                if (maHoKhau.isEmpty() || maChuHoCu.isEmpty() || maChuHoMoi.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Vui lòng nhập đầy đủ thông tin.");
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
                    if (checkNhanKhauExistence(connection, Integer.parseInt(maChuHoMoi))) {
                        if (checkChuHoMatch(connection, Integer.parseInt(maHoKhau), Integer.parseInt(maChuHoCu))) {
                            if (checkNhanKhauInHoKhau(connection, Integer.parseInt(maChuHoMoi), Integer.parseInt(maHoKhau))) {
                                updateChuHo(connection, Integer.parseInt(maHoKhau), Integer.parseInt(maChuHoCu), Integer.parseInt(maChuHoMoi));
                                JOptionPane.showMessageDialog(frame, "Đổi Chủ Hộ thành công!");
                            } else {
                                JOptionPane.showMessageDialog(frame, "Nhân khẩu không nằm trong hộ khẩu.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "Thông tin Mã Hộ Khẩu và Mã Chủ Hộ Cũ không khớp.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Mã Chủ Hộ Mới không tồn tại trong bảng Nhan_khau.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Lỗi khi đổi Chủ Hộ: " + ex.getMessage());
                }
            }
        });

        rightPanel.add(inputPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void quayVeQuanLyHoKhau() {
        new QuanLyHoKhau();
        frame.dispose();
    }

    private boolean checkNhanKhauExistence(Connection connection, int maChuHoMoi) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Nhan_khau WHERE Ma_nhan_khau = ?")) {
            preparedStatement.setInt(1, maChuHoMoi);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    private boolean checkChuHoMatch(Connection connection, int maHoKhau, int maChuHoCu) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Chu_ho WHERE Ma_ho_khau = ? AND Ma_nhan_khau = ?")) {
            preparedStatement.setInt(1, maHoKhau);
            preparedStatement.setInt(2, maChuHoCu);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    private void updateChuHo(Connection connection, int maHoKhau, int maChuHoCu, int maChuHoMoi) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Chu_ho SET Ma_nhan_khau = ? WHERE Ma_ho_khau = ? AND Ma_nhan_khau = ?")) {
            preparedStatement.setInt(1, maChuHoMoi);
            preparedStatement.setInt(2, maHoKhau);
            preparedStatement.setInt(3, maChuHoCu);
            preparedStatement.executeUpdate();
        }
    }

    private boolean checkNhanKhauInHoKhau(Connection connection, int maNhanKhau, int maHoKhau) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Nhan_khau WHERE Ma_nhan_khau = ? AND Ma_ho_khau = ?")) {
            preparedStatement.setInt(1, maNhanKhau);
            preparedStatement.setInt(2, maHoKhau);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(Color.BLACK);
        return label;
    }
}
