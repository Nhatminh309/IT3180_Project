package quanlydancu.src.quanlyhokhau;

import quanlydancu.src.giaodien.GiaoDienChung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sun.glass.ui.Cursor.setVisible;

public class DoiChuHo extends GiaoDienChung {

    private JTextField txtMaHoKhau;
    private JTextField txtMaChuHoCu;
    private JTextField txtMaChuHoMoi;
    private JButton btnDoiChuHo;

    public DoiChuHo() {
        super();

        txtMaHoKhau = new JTextField(15);
        txtMaChuHoCu = new JTextField(15);
        txtMaChuHoMoi = new JTextField(15);
        btnDoiChuHo = new JButton("Đổi Chủ Hộ");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
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
                quayVeQuanLyHoKhau();
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
                            updateChuHo(connection, Integer.parseInt(maHoKhau), Integer.parseInt(maChuHoCu), Integer.parseInt(maChuHoMoi));
                            JOptionPane.showMessageDialog(frame, "Đổi Chủ Hộ thành công!");
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
        // Tạo đối tượng QuanLyHoKhau và hiển thị nó
        QuanLyHoKhau quanLyHoKhau = new QuanLyHoKhau();
        showFrame();
        frame.dispose(); // Đóng frame hiện tại nếu cần
    }
    public void showFrame() {
        // Make the frame visible
        setVisible(true);
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

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, leftPanel.getHeight() / 30));
        label.setForeground(Color.BLACK);
        return label;
    }

}
