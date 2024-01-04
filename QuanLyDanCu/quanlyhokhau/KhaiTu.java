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
import java.text.SimpleDateFormat;

import static com.sun.glass.ui.Cursor.setVisible;

public class KhaiTu extends GiaoDienChung {

    private JTextField txtMaNhanKhau;
    private JFormattedTextField txtNgayMat;
    private JTextField txtNoiMat;
    private JTextField txtLyDoMat;
    private JButton btnKhaiTu;

    public KhaiTu() {
        super();

        txtMaNhanKhau = new JTextField();
        txtNgayMat = createFormattedTextField();
        txtNoiMat = new JTextField();
        txtLyDoMat = new JTextField();
        btnKhaiTu = new JButton("Khai tử");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2));
        inputPanel.add(createLabel("Mã nhân khẩu:"));
        inputPanel.add(txtMaNhanKhau);
        inputPanel.add(createLabel("Ngày mất:"));
        inputPanel.add(txtNgayMat);
        inputPanel.add(createLabel("Nơi mất:"));
        inputPanel.add(txtNoiMat);
        inputPanel.add(createLabel("Lý do mất:"));
        inputPanel.add(txtLyDoMat);
        inputPanel.add(new JLabel());
        inputPanel.add(btnKhaiTu);

        btnKhaiTu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maNhanKhau = txtMaNhanKhau.getText();
                String ngayMat = txtNgayMat.getText();
                String noiMat = txtNoiMat.getText();
                String lyDoMat = txtLyDoMat.getText();

                if (maNhanKhau.isEmpty() || ngayMat.isEmpty() || noiMat.isEmpty() || lyDoMat.isEmpty()) {
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

                boolean nhanKhauTonTai = false;

                try (PreparedStatement checkStatement = connection.prepareStatement("SELECT COUNT(*) FROM nhan_khau WHERE ma_nhan_khau = ?")) {
                    checkStatement.setInt(1, Integer.parseInt(maNhanKhau));
                    try (ResultSet resultSet = checkStatement.executeQuery()) {
                        if (resultSet.next() && resultSet.getInt(1) > 0) {
                            nhanKhauTonTai = true;
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Lỗi kiểm tra mã nhân khẩu.");
                }

                if (nhanKhauTonTai) {
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO khai_tu (ma_nhan_khau, ngay_mat, noi_mat, ly_do, da_xac_nhan) VALUES (?, ?, ?, ?, ?)");

                    ) {
                        preparedStatement1.setInt(1, Integer.parseInt(maNhanKhau));
                        preparedStatement1.setDate(2, Date.valueOf(ngayMat));
                        preparedStatement1.setString(3, noiMat);
                        preparedStatement1.setString(4, lyDoMat);
                        preparedStatement1.setBoolean(5, true);

                        preparedStatement1.executeUpdate();

                        JOptionPane.showMessageDialog(frame, "Khai tử thành công!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Lỗi thực hiện khai tử." + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Mã nhân khẩu không tồn tại.");
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
