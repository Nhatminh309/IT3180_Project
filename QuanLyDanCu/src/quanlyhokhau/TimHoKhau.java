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

import static com.sun.glass.ui.Cursor.setVisible;

public class TimHoKhau extends GiaoDienChung {

    private JTextField txtMaHoKhau;
    private JButton btnTimHoKhau;
    private JTable table;

    public TimHoKhau() {
        super();

        txtMaHoKhau = new JTextField(15);
        btnTimHoKhau = new JButton("Tìm kiếm");
        table = new JTable(new DefaultTableModel());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.add(new JLabel("Mã Hộ Khẩu:"));
        inputPanel.add(txtMaHoKhau);
        inputPanel.add(btnTimHoKhau);

        btnTimHoKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maHoKhau = txtMaHoKhau.getText().trim();

                if (maHoKhau.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Vui lòng nhập mã Hộ Khẩu.");
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
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setColumnCount(0);
                    model.setRowCount(0);

                    String[] columnNames = {"Thông tin", "Giá trị"};
                    model.setColumnIdentifiers(columnNames);

                    Object[] rowData = findHoKhauInfo(connection, maHoKhau);
                    if (rowData != null) {
                        model.addRow(new Object[]{"Mã Hộ Khẩu", rowData[0]});
                        model.addRow(new Object[]{"Số nhà", rowData[1]});
                        model.addRow(new Object[]{"Tên đường", rowData[2]});
                        model.addRow(new Object[]{"Tên phường", rowData[3]});
                        model.addRow(new Object[]{"Tên quận", rowData[4]});
                        model.addRow(new Object[]{"Tên thành phố", rowData[5]});
                        model.addRow(new Object[]{"Số lượng xe máy", rowData[6]});
                        model.addRow(new Object[]{"Số lượng ô tô", rowData[7]});
                        model.addRow(new Object[]{"Diện tích", rowData[8]});
                    } else {
                        JOptionPane.showMessageDialog(frame, "Không tìm thấy thông tin cho mã Hộ Khẩu " + maHoKhau + ".");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Lỗi khi tìm kiếm thông tin Hộ Khẩu: " + ex.getMessage());
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        rightPanel.add(panel, BorderLayout.CENTER);

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

    private Object[] findHoKhauInfo(Connection connection, String maHoKhau) throws SQLException {
        String query = "SELECT * FROM ho_khau WHERE Ma_ho_khau = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(maHoKhau));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getInt("Ma_ho_khau"),
                            resultSet.getString("So_nha"),
                            resultSet.getString("Ten_duong"),
                            resultSet.getString("Ten_phuong"),
                            resultSet.getString("Ten_quan"),
                            resultSet.getString("Ten_thanh_pho"),
                            resultSet.getInt("So_luong_xe_may"),
                            resultSet.getInt("So_luong_o_to"),
                            resultSet.getInt("Dien_tich")
                    };
                    return rowData;
                } else {
                    return null;
                }
            }
        }
    }

}
