package QuanLyDanCu.src.quanlyhokhau;

import QuanLyDanCu.src.giaodien.GiaoDienChung;

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

public class DangKyTamVang extends GiaoDienChung {

    private JTextField txtMaNhanKhau;
    private JFormattedTextField txtNgayTamVang;
    private JTextField txtNoiDen;
    private JButton btnDangKyTamVang;

    public DangKyTamVang() {
        super();

        txtMaNhanKhau = new JTextField();
        txtNgayTamVang = createFormattedTextField();
        txtNoiDen = new JTextField();
        btnDangKyTamVang = new JButton("Đăng ký tạm vắng");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2));
        inputPanel.add(createLabel("Mã nhân khẩu:"));
        inputPanel.add(txtMaNhanKhau);
        inputPanel.add(createLabel("Ngày tạm vắng:"));
        inputPanel.add(txtNgayTamVang);
        inputPanel.add(createLabel("Nơi đến:"));
        inputPanel.add(txtNoiDen);
        inputPanel.add(new JLabel());
        inputPanel.add(btnDangKyTamVang);

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

        btnDangKyTamVang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmResult = JOptionPane.showConfirmDialog(frame, "Bạn có chắc chắn muốn đăng ký tạm vắng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

                if (confirmResult == JOptionPane.YES_OPTION) {
                    String maNhanKhau = txtMaNhanKhau.getText();
                    String ngayTamVang = txtNgayTamVang.getText();
                    String noiDen = txtNoiDen.getText();

                    if (maNhanKhau.isEmpty() || ngayTamVang.isEmpty() || noiDen.isEmpty()) {
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

                    // Kiểm tra xem Ma_nhan_khau có tồn tại trong bảng Nhan_khau không
                    try {
                        if (!checkNhanKhauExistence(connection, Integer.parseInt(maNhanKhau))) {
                            JOptionPane.showMessageDialog(frame, "Mã nhân khẩu không tồn tại trong bảng Nhan_khau.");
                            return;
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    try (
                            // Truy vấn cơ sở dữ liệu để lấy giá trị ID hiện tại
                            Statement statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM tam_vang");
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tam_vang (id, Ma_nhan_khau, Ngay_tam_vang, Noi_den, Da_xac_nhan) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
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
                        preparedStatement.setInt(2, Integer.parseInt(maNhanKhau));
                        preparedStatement.setDate(3, Date.valueOf(ngayTamVang));
                        preparedStatement.setString(4, noiDen);
                        preparedStatement.setBoolean(5, true);

                        int affectedRows = preparedStatement.executeUpdate();

                        if (affectedRows > 0) {
                            // Retrieve the automatically generated ID
                            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                            if (generatedKeys.next()) {
                                int generatedId = generatedKeys.getInt(1);
                                System.out.println("ID tạo tự động: " + generatedId);
                            }

                            JOptionPane.showMessageDialog(frame, "Đăng ký tạm vắng thành công!");

                            // Làm sạch trường sau khi thêm dữ liệu
                            txtMaNhanKhau.setText("");
                            txtNgayTamVang.setValue(null);
                            txtNoiDen.setText("");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Lỗi khi thêm dữ liệu, không có dòng nào bị ảnh hưởng.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Lỗi thực hiện đăng ký tạm vắng: " + ex.getMessage());
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

    private boolean checkNhanKhauExistence(Connection connection, int maNhanKhau) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Nhan_khau WHERE Ma_nhan_khau = ?")) {
            preparedStatement.setInt(1, maNhanKhau);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
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

