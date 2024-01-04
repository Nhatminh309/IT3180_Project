package quanlydancu.src.quanlyhokhau;

import quanlydancu.src.giaodien.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sun.glass.ui.Cursor.setVisible;

public class TachHoKhau extends GiaoDienChung {
    private JTextField txtMaHoKhau;
    private JButton btnTim, btnTach;
    private JTable table;

    public TachHoKhau() {

        super();
        initComponents();
    }

    private void initComponents() {
        txtMaHoKhau = new JTextField(15);
        btnTim = new JButton("Tìm kiếm");
        table = new JTable(new DefaultTableModel());
        btnTach = new JButton("Tách");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.add(new JLabel("Mã Hộ Khẩu:"));
        inputPanel.add(txtMaHoKhau);
        inputPanel.add(btnTim);
        inputPanel.add(btnTach);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        rightPanel.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);



        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timNhanKhauTheoMaHoKhau();
            }
        });

        btnTach.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tachHoKhau();
            }
        });

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

    private void timNhanKhauTheoMaHoKhau() {
        String maHoKhau = txtMaHoKhau.getText().trim();

        int count = demNhanKhauTheoMaHoKhau(maHoKhau);

        if (count == 0) {
            JOptionPane.showMessageDialog(frame, "Không tồn tại hộ khẩu có mã " + maHoKhau, "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else if (count == 1) {
            JOptionPane.showMessageDialog(frame, "Không thể tách vì chỉ có một người trong hộ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            hienThiDanhSachNhanKhau(maHoKhau, count);
        }
    }

    private int demNhanKhauTheoMaHoKhau(String maHoKhau) {
        int count = 0;

        try (Connection connection = getConnectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM nhan_khau WHERE ma_ho_khau = ?")) {
            preparedStatement.setInt(1, Integer.parseInt(maHoKhau));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    private void hienThiDanhSachNhanKhau(String maHoKhau, int count) {
        try (Connection connection = getConnectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT ma_nhan_khau, ho_va_ten FROM Nhan_khau WHERE ma_ho_khau = ?")) {
            preparedStatement.setInt(1, Integer.parseInt(maHoKhau));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String[] columnNames = {"Ma_nhan_khau", "Ho_va_ten", "Chon"};
                    Object[][] data = new Object[count][3];

                    int rowIndex = 0;
                    do {
                        data[rowIndex][0] = resultSet.getString("Ma_nhan_khau");
                        data[rowIndex][1] = resultSet.getString("Ho_va_ten");
                        data[rowIndex][2] = Boolean.FALSE;  // Sửa thành Boolean.FALSE

                        rowIndex++;
                    } while (resultSet.next());

                    DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                        @Override
                        public Class<?> getColumnClass(int columnIndex) {
                            if (columnIndex == 2) {
                                return Boolean.class;
                            }
                            return super.getColumnClass(columnIndex);
                        }
                    };

                    table.setModel(model);

                    JOptionPane.showMessageDialog(frame, new JScrollPane(table), "Danh sách nhân khẩu", JOptionPane.PLAIN_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void tachHoKhau() {
        String maHoKhau = txtMaHoKhau.getText().trim();

        // Thực hiện các bước B4 và B5
        try (Connection connection = getConnectDatabase()) {
            // Bước B4: Lấy ra ma_ho_khau lớn nhất trong csdl rồi cộng với 1
            int maHoKhauMoi = layMaHoKhauLonNhat(connection) ;

            // Bước B5: Cập nhật các ma_ho_khau của nhân khẩu được "Chọn" thành mã mới vừa tạo
            capNhatMaHoKhau(connection, maHoKhau, maHoKhauMoi);

            // Hiển thị thông báo tách thành công
            JOptionPane.showMessageDialog(frame, "Tách hộ khẩu thành công với mã hộ khẩu mới là " + maHoKhauMoi, "Thành công", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int layMaHoKhauLonNhat(Connection connection) throws SQLException {
        int maxMaHoKhau = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(ma_ho_khau) FROM Ho_khau");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                maxMaHoKhau = resultSet.getInt(1);
            }
        }

        return maxMaHoKhau;
    }

    private void capNhatMaHoKhau(Connection connection, String maHoKhauCu, int maHoKhauMoi) throws SQLException {
        // Lấy model của bảng để có thể xem dữ liệu hiện tại
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Duyệt qua tất cả các dòng trong bảng để kiểm tra ô "Chọn"
        for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
            Boolean chon = (Boolean) model.getValueAt(rowIndex, 2);  // Ô "Chọn" nằm ở cột thứ 2

            if (chon != null && chon) {
                // Nếu ô "Chọn" được đánh dấu, cập nhật ma_ho_khau của nhân khẩu
                String maNhanKhau = (String) model.getValueAt(rowIndex, 0);  // Cột mã nhân khẩu nằm ở cột thứ 0
                capNhatMaHoKhauNhanKhau(connection, maNhanKhau, maHoKhauMoi);
            }
        }
    }
    private void capNhatMaHoKhauNhanKhau(Connection connection, String maNhanKhau, int maHoKhauMoi) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Nhan_khau SET ma_ho_khau = ? WHERE ma_nhan_khau = ?")) {
            preparedStatement.setInt(1, maHoKhauMoi);
            preparedStatement.setInt(2, Integer.parseInt(maNhanKhau));
            preparedStatement.executeUpdate();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TachHoKhau();
            }
        });
    }
}