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

import static com.sun.glass.ui.Cursor.setVisible;

public class TaoChuHo extends GiaoDienChung {

    private JTextField txtMaHoKhau;
    private JTextField txtMaChuHo;
    private JButton btnTaoChuHo;

    public TaoChuHo() {
        super();

        txtMaHoKhau = new JTextField(15);
        txtMaChuHo = new JTextField(15);
        btnTaoChuHo = new JButton("Tạo Chủ Hộ");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.add(createLabel("Mã Hộ Khẩu:"));
        inputPanel.add(txtMaHoKhau);
        inputPanel.add(createLabel("Mã Chủ Hộ:"));
        inputPanel.add(txtMaChuHo);
        inputPanel.add(new JLabel());
        inputPanel.add(btnTaoChuHo);

        btnTaoChuHo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maHoKhau = txtMaHoKhau.getText().trim();
                String maChuHo = txtMaChuHo.getText().trim();

                if (maHoKhau.isEmpty() || maChuHo.isEmpty()) {
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
                    if (!checkChuHoExistence(connection, Integer.parseInt(maHoKhau))) {
                        if (checkHoKhauExistence(connection, Integer.parseInt(maHoKhau))) {
                            if (checkNhanKhauExistence(connection, Integer.parseInt(maChuHo))) {
                                insertChuHo(connection, Integer.parseInt(maHoKhau), Integer.parseInt(maChuHo));
                                JOptionPane.showMessageDialog(frame, "Tạo Chủ Hộ thành công!");
                            } else {
                                JOptionPane.showMessageDialog(frame, "Mã Chủ Hộ không tồn tại trong bảng Nhan_khau.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "Mã Hộ Khẩu không tồn tại trong bảng Ho_khau.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Mã Hộ Khẩu đã có Chủ Hộ.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Lỗi khi tạo Chủ Hộ: " + ex.getMessage());
                }
            }
        });

        rightPanel.add(inputPanel, BorderLayout.CENTER);

        frame.setVisible(true);
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

    private boolean checkChuHoExistence(Connection connection, int maHoKhau) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Chu_ho WHERE Ma_ho_khau = ?")) {
            preparedStatement.setInt(1, maHoKhau);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    private boolean checkHoKhauExistence(Connection connection, int maHoKhau) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Ho_khau WHERE Ma_ho_khau = ?")) {
            preparedStatement.setInt(1, maHoKhau);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    private boolean checkNhanKhauExistence(Connection connection, int maChuHo) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Nhan_khau WHERE Ma_nhan_khau = ?")) {
            preparedStatement.setInt(1, maChuHo);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    private void insertChuHo(Connection connection, int maHoKhau, int maChuHo) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Chu_ho (Ma_ho_khau, Ma_nhan_khau, Ngay_tao, Da_xac_nhan) VALUES (?, ?, CURRENT_DATE, TRUE)")) {
            preparedStatement.setInt(1, maHoKhau);
            preparedStatement.setInt(2, maChuHo);
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
