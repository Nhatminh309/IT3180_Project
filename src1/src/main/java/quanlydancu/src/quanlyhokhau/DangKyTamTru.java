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
import java.sql.Statement;
import java.text.SimpleDateFormat;

import static com.sun.glass.ui.Cursor.setVisible;

public class DangKyTamTru extends MainBoard {

    private JTextField txtDiaChiThuongTru;
    private JFormattedTextField txtNgayDangKy;
    private JFormattedTextField txtThoiHan;
    private JTextField txtMaNhanKhau;
    private JButton btnDangKyTamTru;

    public DangKyTamTru() {
        super();

        txtDiaChiThuongTru = new JTextField();
        txtNgayDangKy = createFormattedTextField();
        txtThoiHan = createFormattedTextField();
        txtMaNhanKhau = new JTextField();
        btnDangKyTamTru = new JButton("Đăng ký tạm trú");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2));
        inputPanel.add(createLabel("Địa chỉ thường trú:"));
        inputPanel.add(txtDiaChiThuongTru);
        inputPanel.add(createLabel("Ngày đăng ký:"));
        inputPanel.add(txtNgayDangKy);
        inputPanel.add(createLabel("Thời hạn:"));
        inputPanel.add(txtThoiHan);
        inputPanel.add(createLabel("Mã nhân khẩu:"));
        inputPanel.add(txtMaNhanKhau);
        inputPanel.add(new JLabel());
        inputPanel.add(btnDangKyTamTru);

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

        btnDangKyTamTru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmResult = JOptionPane.showConfirmDialog(frame, "Bạn có chắc chắn muốn đăng ký tạm trú không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

                if (confirmResult == JOptionPane.YES_OPTION) {
                    String diaChiThuongTru = txtDiaChiThuongTru.getText();
                    String ngayDangKy = txtNgayDangKy.getText();
                    String thoiHan = txtThoiHan.getText();
                    String maNhanKhau = txtMaNhanKhau.getText();

                    if (diaChiThuongTru.isEmpty() || ngayDangKy.isEmpty() || thoiHan.isEmpty() || maNhanKhau.isEmpty()) {
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

                    try (
                            // Truy vấn cơ sở dữ liệu để lấy giá trị ID hiện tại
                            Statement statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM so_tam_tru");
                            // Thêm RETURNING ID để nhận giá trị ID tự động tăng
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO so_tam_tru (id, dia_chi_thuong_tru, ngay_dang_ky, thoi_han, ma_nhan_khau, da_xac_nhan) VALUES (?, ?, ?, ?, ?, ?)")
                    ) {
                        int currentMaxId = 0;

                        // Lấy giá trị ID hiện tại
                        if (resultSet.next()) {
                            currentMaxId = resultSet.getInt(1);
                        }

                        // Tăng giá trị ID lấy được thêm 1
                        int yourSpecificId = currentMaxId + 1;

                        // Truyền giá trị ID cụ thể vào câu lệnh SQL
                        preparedStatement.setInt(1, yourSpecificId);
                        preparedStatement.setString(2, diaChiThuongTru);
                        preparedStatement.setDate(3, Date.valueOf(ngayDangKy));
                        preparedStatement.setDate(4, Date.valueOf(thoiHan));
                        preparedStatement.setInt(5, Integer.parseInt(maNhanKhau));
                        preparedStatement.setBoolean(6, true);

                        // Thực hiện lấy giá trị ID từ kết quả của câu lệnh SQL
                        int affectedRows = preparedStatement.executeUpdate();

                        if (affectedRows > 0) {
                            JOptionPane.showMessageDialog(frame, "Đăng ký tạm trú thành công!");

                            // Làm sạch trường sau khi thêm dữ liệu
                            txtDiaChiThuongTru.setText("");
                            txtNgayDangKy.setValue(null);
                            txtThoiHan.setValue(null);
                            txtMaNhanKhau.setText("");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Lỗi khi thêm dữ liệu, không có dòng nào bị ảnh hưởng.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Lỗi thực hiện đăng ký tạm trú: " + ex.getMessage());
                    }
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


    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, leftPanel.getHeight() / 30));
        label.setForeground(Color.BLACK);
        return label;
    }

    private JFormattedTextField createFormattedTextField() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        JFormattedTextField formattedTextField = new JFormattedTextField(dateFormat);
        formattedTextField.setColumns(10);
        return formattedTextField;
    }


}