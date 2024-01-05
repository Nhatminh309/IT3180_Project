package QuanLyDanCu.src.quanlyhokhau;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import QuanLyDanCu.src.giaodien.*;
import QuanLyDanCu.src.quanlyhokhau.QuanLyHoKhau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    private JComboBox<String> cboDiaDiem;
    private JButton btnThemHoKhau;
    private JButton btnChonFile;
    private JButton btnQuayVe;

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
        cboDiaDiem = new JComboBox<>(new String[]{"Chung cư BlueMoon", "Tổ dân phố 7"});
        btnThemHoKhau = new JButton("Thêm hộ khẩu");
        btnChonFile = new JButton("Chọn file");
        btnQuayVe = new JButton("Quay về");

        JPanel inputPanel = new JPanel(new GridLayout(11, 2, 10, 10));

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
        inputPanel.add(createLabel("Địa điểm:"));
        inputPanel.add(cboDiaDiem);
        inputPanel.add(createLabel("Số lượng xe máy:"));
        inputPanel.add(txtSoLuongXeMay);
        inputPanel.add(createLabel("Số lượng ô tô:"));
        inputPanel.add(txtSoLuongOTo);
        inputPanel.add(createLabel("Diện tích:"));
        inputPanel.add(txtDienTich);
        inputPanel.add(new JLabel());
        inputPanel.add(btnThemHoKhau);

        btnChonFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chonFile();
            }
        });
        inputPanel.add(new JLabel());
        inputPanel.add(btnChonFile);

        btnThemHoKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmResult = JOptionPane.showConfirmDialog(frame, "Bạn có chắc chắn muốn thêm hộ khẩu không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

                if (confirmResult == JOptionPane.YES_OPTION) {
                    // Lấy giá trị từ các trường nhập liệu
                    String soNha = txtSoNha.getText();
                    String tenDuong = txtTenDuong.getText();
                    String tenPhuong = txtTenPhuong.getText();
                    String tenQuan = txtTenQuan.getText();
                    String tenThanhPho = txtTenThanhPho.getText();
                    String diaDiem = (String) cboDiaDiem.getSelectedItem();
                    String soLuongXeMay = txtSoLuongXeMay.getText();
                    String soLuongOTo = txtSoLuongOTo.getText();
                    String dienTich = txtDienTich.getText();

                    // Kiểm tra xem có trường nào trống không
                    if (soNha.isEmpty() || tenDuong.isEmpty() || tenPhuong.isEmpty() || tenQuan.isEmpty() || tenThanhPho.isEmpty() || diaDiem.isEmpty() || soLuongXeMay.isEmpty() || soLuongOTo.isEmpty() || dienTich.isEmpty()) {
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
                            Statement statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery("SELECT MAX(Ma_ho_khau) FROM ho_khau");
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ho_khau (Ma_ho_khau, Dia_diem, So_nha, Ten_duong, Ten_phuong, Ten_quan, Ten_thanh_pho, Da_xac_nhan, So_luong_xe_may, So_luong_o_to, Dien_tich) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
                    ) {
                        int currentMaxId = 0;

                        if (resultSet.next()) {
                            currentMaxId = resultSet.getInt(1);
                        }

                        int yourSpecificId = currentMaxId + 1;

                        preparedStatement.setInt(1, yourSpecificId);
                        preparedStatement.setString(2, diaDiem);
                        preparedStatement.setString(3, soNha);
                        preparedStatement.setString(4, tenDuong);
                        preparedStatement.setString(5, tenPhuong);
                        preparedStatement.setString(6, tenQuan);
                        preparedStatement.setString(7, tenThanhPho);
                        preparedStatement.setBoolean(8, true);
                        preparedStatement.setInt(9, Integer.parseInt(soLuongXeMay));
                        preparedStatement.setInt(10, Integer.parseInt(soLuongOTo));
                        preparedStatement.setInt(11, Integer.parseInt(dienTich));

                        int affectedRows = preparedStatement.executeUpdate();

                        if (affectedRows > 0) {
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
                            cboDiaDiem.setSelectedIndex(0);
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

        JButton btnQuayVe = new JButton("Quay về");
        btnQuayVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new QuanLyHoKhau();
                frame.dispose();
            }
        });

        rightPanel.add(btnQuayVe, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void chonFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file Excel");
        int userSelection = fileChooser.showOpenDialog(frame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            importDataFromExcel(filePath);
        }
    }

    private void importDataFromExcel(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath));
             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            Connection connection = getConnectDatabase();

            // Lấy giá trị Ma_ho_khau lớn nhất từ cơ sở dữ liệu
            int maxMaHoKhau = getMaxMaHoKhau(connection);

            // Chuẩn bị câu lệnh INSERT
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO ho_khau (Ma_ho_khau, Dia_diem, So_nha, Ten_duong, Ten_phuong, Ten_quan, Ten_thanh_pho, Da_xac_nhan, So_luong_xe_may, So_luong_o_to, Dien_tich) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                String soNha = getCellValueAsString(row.getCell(0));
                String tenDuong = getCellValueAsString(row.getCell(1));
                String tenPhuong = getCellValueAsString(row.getCell(2));
                String tenQuan = getCellValueAsString(row.getCell(3));
                String tenThanhPho = getCellValueAsString(row.getCell(4));
                String diaDiem = getCellValueAsString(row.getCell(5));
                String soLuongXeMay = getCellValueAsString(row.getCell(6));
                String soLuongOTo = getCellValueAsString(row.getCell(7));
                String dienTich = getCellValueAsString(row.getCell(8));

                if (soNha.isEmpty() || tenDuong.isEmpty() || tenPhuong.isEmpty() || tenQuan.isEmpty() || tenThanhPho.isEmpty() || diaDiem.isEmpty() || soLuongXeMay.isEmpty() || soLuongOTo.isEmpty() || dienTich.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Dữ liệu trong file không hợp lệ. Vui lòng kiểm tra và thử lại.");
                    return;
                }

                try {
                    // Tăng giá trị Ma_ho_khau lên 1
                    maxMaHoKhau++;

                    preparedStatement.setInt(1, maxMaHoKhau);
                    preparedStatement.setString(2, diaDiem);
                    preparedStatement.setString(3, soNha);
                    preparedStatement.setString(4, tenDuong);
                    preparedStatement.setString(5, tenPhuong);
                    preparedStatement.setString(6, tenQuan);
                    preparedStatement.setString(7, tenThanhPho);
                    preparedStatement.setBoolean(8, true);
                    preparedStatement.setInt(9, Integer.parseInt(soLuongXeMay));
                    preparedStatement.setInt(10, Integer.parseInt(soLuongOTo));
                    preparedStatement.setInt(11, Integer.parseInt(dienTich));

                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Lỗi khi thêm dữ liệu vào cơ sở dữ liệu: " + ex.getMessage());
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Nhập dữ liệu từ file Excel thành công!");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Lỗi khi đọc file Excel hoặc kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // Phương thức để lấy giá trị Ma_ho_khau lớn nhất từ cơ sở dữ liệu
    private int getMaxMaHoKhau(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT MAX(Ma_ho_khau) FROM ho_khau")) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                // Nếu không có dữ liệu, trả về 0 hoặc một giá trị mặc định khác tùy thuộc vào yêu cầu của bạn
                return 0;
            }
        }
    }



    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    private void showFrame() {
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, leftPanel.getHeight() / 30));
        label.setForeground(Color.BLACK);
        return label;
    }
}
