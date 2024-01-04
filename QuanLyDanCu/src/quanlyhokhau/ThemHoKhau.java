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

public class ThemHoKhau extends GiaoDienChung {

    private JTextField txtSoNha;
    private JTextField txtTenDuong;
    private JTextField txtTenPhuong;
    private JTextField txtTenQuan;
    private JTextField txtTenThanhPho;
    private JTextField txtSoLuongXeMay;
    private JTextField txtSoLuongOTo;
    private JTextField txtDienTich;
    private JButton btnThemHoKhau;

    public ThemHoKhau() {
        super();

        txtSoNha = new JTextField();
        txtTenDuong = new JTextField();
        txtTenPhuong = new JTextField();
        txtTenQuan = new JTextField();
        txtTenThanhPho = new JTextField();
        txtSoLuongXeMay = new JTextField();
        txtSoLuongOTo = new JTextField();
        txtDienTich = new JTextField();
        btnThemHoKhau = new JButton("Thêm hộ khẩu");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(9, 2));
        inputPanel.add(createLabel("Số nhà:"));
        inputPanel.add(txtSoNha);
        inputPanel.add(createLabel("Tên đường:"));
        inputPanel.add(txtTenDuong);
        inputPanel.add(createLabel("Tên phường:"));
        inputPanel.add(txtTenPhuong);
        inputPanel.add(createLabel("Tên quận:"));
        inputPanel.add(txtTenQuan);
        inputPanel.add(createLabel("Tên thành phố:"));
        inputPanel.add(txtTenThanhPho);
        inputPanel.add(createLabel("Số lượng xe máy:"));
        inputPanel.add(txtSoLuongXeMay);
        inputPanel.add(createLabel("Số lượng ô tô:"));
        inputPanel.add(txtSoLuongOTo);
        inputPanel.add(createLabel("Diện tích:"));
        inputPanel.add(txtDienTich);
        inputPanel.add(new JLabel());
        inputPanel.add(btnThemHoKhau);

        btnThemHoKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmResult = JOptionPane.showConfirmDialog(frame, "Bạn có chắc chắn muốn thêm hộ khẩu không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

                if (confirmResult == JOptionPane.YES_OPTION) {
                    String soNha = txtSoNha.getText();
                    String tenDuong = txtTenDuong.getText();
                    String tenPhuong = txtTenPhuong.getText();
                    String tenQuan = txtTenQuan.getText();
                    String tenThanhPho = txtTenThanhPho.getText();
                    String soLuongXeMay = txtSoLuongXeMay.getText();
                    String soLuongOTo = txtSoLuongOTo.getText();
                    String dienTich = txtDienTich.getText();

                    if (soNha.isEmpty() || tenDuong.isEmpty() || tenPhuong.isEmpty() || tenQuan.isEmpty() || tenThanhPho.isEmpty() || soLuongXeMay.isEmpty() || soLuongOTo.isEmpty() || dienTich.isEmpty()) {
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
                            ResultSet resultSet = statement.executeQuery("SELECT MAX(Ma_ho_khau) FROM ho_khau");
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ho_khau (Ma_ho_khau, So_nha, Ten_duong, Ten_phuong, Ten_quan, Ten_thanh_pho, Da_xac_nhan, So_luong_xe_may, So_luong_o_to, Dien_tich) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
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
                        preparedStatement.setString(2, soNha);
                        preparedStatement.setString(3, tenDuong);
                        preparedStatement.setString(4, tenPhuong);
                        preparedStatement.setString(5, tenQuan);
                        preparedStatement.setString(6, tenThanhPho);
                        preparedStatement.setBoolean(7, true);
                        preparedStatement.setInt(8, Integer.parseInt(soLuongXeMay));
                        preparedStatement.setInt(9, Integer.parseInt(soLuongOTo));
                        preparedStatement.setInt(10, Integer.parseInt(dienTich));

                        int affectedRows = preparedStatement.executeUpdate();

                        if (affectedRows > 0) {
                            // Retrieve the automatically generated ID
                            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                            if (generatedKeys.next()) {
                                int generatedId = generatedKeys.getInt(1);
                                System.out.println("ID tạo tự động: " + generatedId);
                            }

                            JOptionPane.showMessageDialog(frame, "Thêm hộ khẩu thành công!");

                            // Làm sạch trường sau khi thêm dữ liệu
                            txtSoNha.setText("");
                            txtTenDuong.setText("");
                            txtTenPhuong.setText("");
                            txtTenQuan.setText("");
                            txtTenThanhPho.setText("");
                            txtSoLuongXeMay.setText("");
                            txtSoLuongOTo.setText("");
                            txtDienTich.setText("");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Lỗi khi thêm dữ liệu, không có dòng nào bị ảnh hưởng.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Lỗi thực hiện thêm hộ khẩu: " + ex.getMessage());
                    }
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
                quayVeQuanLyHoKhau();
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



}
