package QuanLyDanCu.src.quanlyhokhau;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import QuanLyDanCu.src.giaodien.GiaoDienChung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DSTamTru extends GiaoDienChung {

    private JTable table;

    public DSTamTru() {
        super();

        // Create table and set its properties
        table = new JTable();
        table.setModel(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(table);

        // Add components to the panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add the panel to the rightPanel
        rightPanel.add(panel, BorderLayout.CENTER);

        // Load data into the table
        loadData();

        frame.setVisible(true);

        // Add buttons to a new panel with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Add "Quay về" button
        JButton btnQuayVe = new JButton("Quay về");
        btnQuayVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quayVeQuanLyHoKhau();
            }
        });
        buttonPanel.add(btnQuayVe);

        // Add "Xuất file" button
        JButton btnXuatFile = new JButton("Xuất file");
        btnXuatFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuatFileExcel();
            }
        });
        buttonPanel.add(btnXuatFile);

        // Add the buttonPanel to the rightPanel
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void quayVeQuanLyHoKhau() {
        // Tạo đối tượng QuanLyHoKhau và hiển thị nó
        frame.dispose();
        new QuanLyHoKhau();
        frame.dispose(); // Đóng frame hiện tại nếu cần
    }

    private void loadData() {
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
                ResultSet resultSet = statement.executeQuery("SELECT id, dia_chi_thuong_tru, ngay_dang_ky, thoi_han, ma_nhan_khau FROM so_tam_tru")
        ) {
            // Create a DefaultTableModel with columns
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Địa chỉ thường trú");
            model.addColumn("Ngày đăng ký");
            model.addColumn("Thời hạn");
            model.addColumn("Mã nhân khẩu");

            // Populate the table with data
            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("ID"),
                        resultSet.getString("Dia_chi_thuong_tru"),
                        resultSet.getDate("Ngay_dang_ky"),
                        resultSet.getDate("Thoi_han"),
                        resultSet.getInt("Ma_nhan_khau"),

                };
                model.addRow(row);
            }

            // Set the model to the table
            table.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Lỗi khi tải dữ liệu tạm trú: " + ex.getMessage());
        }
    }

    private void xuatFileExcel() {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
            int userSelection = fileChooser.showSaveDialog(frame);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                // Tạo workbook và sheet mới
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Danh sách Tạm trú");

                // Tạo dòng đầu tiên (header)
                Row headerRow = sheet.createRow(0);
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Cell cell = headerRow.createCell(col);
                    cell.setCellValue(model.getColumnName(col));
                }

                // Tạo các dòng dữ liệu
                for (int row = 0; row < model.getRowCount(); row++) {
                    Row dataRow = sheet.createRow(row + 1);
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        Cell cell = dataRow.createCell(col);
                        cell.setCellValue(String.valueOf(model.getValueAt(row, col)));
                    }
                }

                // Lưu workbook vào file
                try (FileOutputStream fileOut = new FileOutputStream(filePath + ".xlsx")) {
                    workbook.write(fileOut);
                    JOptionPane.showMessageDialog(frame, "Xuất file Excel thành công!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Lỗi khi lưu file Excel: " + ex.getMessage());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Lỗi khi xuất file Excel: " + ex.getMessage());
        }
    }
}
