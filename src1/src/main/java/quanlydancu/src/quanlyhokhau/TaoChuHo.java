package quanlydancu.src.quanlyhokhau;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

import static com.sun.glass.ui.Cursor.setVisible;

public class TaoChuHo extends MainBoard {
    private JTextField txtMaHoKhau;
    private JButton btnTim, btnTach;
    private static JTextField ngayDKField;
    private JTable table;

    public TaoChuHo() throws SQLException {

        super();
        initComponents();
    }

    private void initComponents() throws SQLException {
        ngayDKField = new JTextField();
        txtMaHoKhau = new JTextField(15);
        btnTim = new JButton("Tìm kiếm");
        table = new JTable(new DefaultTableModel());
        btnTach = new JButton("Tạo chủ hộ");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.add(new JLabel("Mã Hộ Khẩu:"));
        inputPanel.add(txtMaHoKhau);
        inputPanel.add(btnTim);
        inputPanel.add(btnTach);

        Connection connection = getConnectDatabase();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(Ma_ho_khau) FROM ho_khau");
        resultSet.next();
        int maHKauto = resultSet.getInt(1);
        txtMaHoKhau.setText(String.valueOf(maHKauto));

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
                taoCHuHo();
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


    public void showFrame() {
        // Make the frame visible
        setVisible(true);
    }

    private void timNhanKhauTheoMaHoKhau() {
        String maHoKhau = txtMaHoKhau.getText().trim();

        int count = demNhanKhauTheoMaHoKhau(maHoKhau);

        if (count == 0) {
            JOptionPane.showMessageDialog(frame, "Không có nhân khẩu trong hộ khẩu " + maHoKhau, "Lỗi", JOptionPane.ERROR_MESSAGE);
            try {
                new ThemThanhVien();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }  else {
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

    private void taoCHuHo() {
        String maHoKhau = txtMaHoKhau.getText().trim();

        // Thực hiện các bước B4 và B5
        try (Connection connection = getConnectDatabase()) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Duyệt qua tất cả các dòng trong bảng để kiểm tra ô "Chọn"
            for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
                Boolean chon = (Boolean) model.getValueAt(rowIndex, 2);  // Ô "Chọn" nằm ở cột thứ 2

                if (chon != null && chon) {
                    // Nếu ô "Chọn" được đánh dấu, cập nhật ma_ho_khau của nhân khẩu
                    String maNhanKhau = (String) model.getValueAt(rowIndex, 0);  // Cột mã nhân khẩu nằm ở cột thứ 0
                    capNhatChuHo(connection, maHoKhau, maNhanKhau);
                    break;
                }
            }


            // Hiển thị thông báo tách thành công
            JOptionPane.showMessageDialog(frame, "Tạo chủ hộ thành công" ,"Thành công", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void capNhatChuHo(Connection connection, String maHoKhau, String maNhanKhau) throws SQLException {
        // Lấy model của bảng để có thể xem dữ liệu hiện tại
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Duyệt qua tất cả các dòng trong bảng để kiểm tra ô "Chọn"
        for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
            Boolean chon = (Boolean) model.getValueAt(rowIndex, 2);  // Ô "Chọn" nằm ở cột thứ 2

            if (chon != null && chon) {
                // Nếu ô "Chọn" được đánh dấu, cập nhật ma_ho_khau của nhân khẩu
                //Set date for ngayDkField
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date today = new java.util.Date();
                String formattedDate = dateFormat.format(today);
                ngayDKField.setText(formattedDate);


                try (PreparedStatement preparedStatement = connection.prepareStatement("Insert into chu_ho (ma_ho_khau, ma_nhan_khau, ngay_tao) values (?, ?, ?)")) {
                    preparedStatement.setInt(1, Integer.parseInt(maHoKhau));
                    preparedStatement.setInt(2, Integer.parseInt(maNhanKhau));
                    preparedStatement.setDate(3, java.sql.Date.valueOf(ngayDKField.getText()));
                    preparedStatement.executeUpdate();
                }
            }
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new TaoChuHo();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}