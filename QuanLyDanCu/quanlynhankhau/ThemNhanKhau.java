package quanlydancu.src.quanlynhankhau;
import quanlydancu.src.connect.ConnectDatabase;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.sql.Date;

public class ThemNhanKhau extends ConnectDatabase {
    private static JPanel topPanel;
    private static JPanel mainPanel;
    private static JFrame frame;
    private static JLabel hoTenLabel;
    private static JTextField hoTenField;
    private static JLabel dobLabel;
    private static JTextField dobField;
    private static JLabel gioiTinhLabel;
    private static JTextField gioiTinhField;
    private static JLabel noiSinhLabel;
    private static JTextField noiSinhField;
    private static JLabel queQuanLabel;
    private static JTextField queQuanField;
    private static JLabel danTocLabel;
    private static JTextField danTocField;
    private static JLabel tonGiaoLabel;
    private static JTextField tonGiaoField;
    private static JLabel ngheNghiepLabel;
    private static JTextField ngheNghiepField;
    private static JLabel noiLamViecLabel;
    private static JTextField noiLamViecField;
    private static JLabel ngayDKLabel;
    private static JTextField ngayDKField;
    private static JLabel cccdLabel;
    private static JTextField cccdField;
    private static JLabel sdtLabel;
    private static JTextField sdtField;
    private static JLabel quanHeLabel;
    private static JTextField quanHeField;
    private static JLabel maHKLabel;
    private static JTextField maHKField;
    private static JLabel dinhDangLabel;
    private static JButton addButton;
    private static JLabel warnLabel;
    private static JButton chooseFileButton;
    public ThemNhanKhau() {
        frame = new JFrame("Thêm nhân khẩu");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.getContentPane().setLayout(new BorderLayout());

        topPanel = new JPanel();
        //topPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Remove any border/margin
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton();
        ImageIcon icon = new ImageIcon("src/icon/goBackIcon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(frame.getWidth()/20, frame.getHeight()/19, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        backButton.setIcon(scaledIcon);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //back to giao dien ban dau
            }
        });
        topPanel.add(backButton, BorderLayout.WEST);

        JLabel themNKLabel = new JLabel("Thêm nhân khẩu");
        themNKLabel.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/20));
        themNKLabel.setHorizontalAlignment(SwingConstants.CENTER); // Align label to the center
        themNKLabel.setForeground(Color.decode("#38B6FF"));
        topPanel.add(themNKLabel, BorderLayout.CENTER);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        hoTenLabel = new JLabel("Họ và tên(*): ");
        hoTenField = new JTextField();
        dobLabel = new JLabel("Ngày sinh(*): ");
        dobField = new JTextField();
        gioiTinhLabel = new JLabel("Giới tính(*): ");
        gioiTinhField = new JTextField();
        noiSinhLabel = new JLabel("Nơi sinh(*): ");
        noiSinhField = new JTextField();
        queQuanLabel = new JLabel("Quê quán(*):");
        queQuanField = new JTextField();
        danTocLabel = new JLabel("Dân tộc:");
        danTocField = new JTextField();
        tonGiaoLabel = new JLabel("Tôn giáo:");
        tonGiaoField = new JTextField();
        ngheNghiepLabel = new JLabel("Nghề nghiệp:");
        ngheNghiepField = new JTextField();
        noiLamViecLabel = new JLabel("Nơi làm việc:");
        noiLamViecField = new JTextField("");
        ngayDKLabel = new JLabel("Ngày đăng ký:");
        ngayDKField = new JTextField();
        cccdLabel = new JLabel("Căn cước công dân(*):");
        cccdField = new JTextField();
        sdtLabel = new JLabel("Số điện thoại(*):");
        sdtField = new JTextField();
        quanHeLabel = new JLabel("Quan hệ hộ khẩu(*):");
        quanHeField = new JTextField();
        maHKLabel = new JLabel("Mã hộ khẩu(*):");
        maHKField = new JTextField();
        addButton = new JButton("Thêm nhân khẩu");
        chooseFileButton = new JButton("Chọn file");
        dinhDangLabel = new JLabel("Định dạng của ngày sinh: YYYY-MM-DD");
        warnLabel = new JLabel("Các ô (*) là bắt buộc điền");
        chooseFileButton = new JButton("Chọn file");


        //Set date for ngayDkField
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date today = new java.util.Date();
        String formattedDate = dateFormat.format(today);
        ngayDKField.setText(formattedDate);

        //Set icon for dinhDangLabel
        ImageIcon icon2 = new ImageIcon("src/icon/aboutIcon.png");
        Image img2 = icon2.getImage();
        Image scaledImg2 = img2.getScaledInstance(frame.getWidth()/66, frame.getWidth()/66, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImg2);
        dinhDangLabel.setIcon(scaledIcon2);
        dinhDangLabel.setIconTextGap(10);
        warnLabel.setIcon(scaledIcon2);
        warnLabel.setIconTextGap(10);

        Font newFont = new Font("Arial", Font.PLAIN, frame.getWidth()/45);
        hoTenLabel.setFont(newFont);
        dobLabel.setFont(newFont);
        gioiTinhLabel.setFont(newFont);
        noiSinhLabel.setFont(newFont);
        queQuanLabel.setFont(newFont);
        danTocLabel.setFont(newFont);
        tonGiaoLabel.setFont(newFont);
        ngheNghiepLabel.setFont(newFont);
        noiLamViecLabel.setFont(newFont);
        ngayDKLabel.setFont(newFont);
        cccdLabel.setFont(newFont);
        sdtLabel.setFont(newFont);
        queQuanLabel.setFont(newFont);
        quanHeLabel.setFont(newFont);
        maHKLabel.setFont(newFont);
        dinhDangLabel.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/55));
        warnLabel.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/55));
        addButton.setFont(newFont);
        addButton.setBackground(Color.decode("#38B6FF"));
        addButton.setOpaque(true);

        chooseFileButton.setFont(newFont);
        chooseFileButton.setBackground(Color.decode("#38B6FF"));
        chooseFileButton.setOpaque(true);
        addButton.setBorder(BorderFactory.createEmptyBorder());
        chooseFileButton.setBorder(BorderFactory.createEmptyBorder());

        //Add popMenu to gioiTinhField
        JPopupMenu popupMenu1 = new JPopupMenu();
        gioiTinhField.add(popupMenu1);
        gioiTinhField.setComponentPopupMenu(popupMenu1);

        JMenuItem gt1 = new JMenuItem("Nam");
        JMenuItem gt2 = new JMenuItem("Nữ");
        popupMenu1.add(gt1);
        popupMenu1.add(gt2);
        gt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gioiTinhField.setText(gt1.getText());
            }
        });
        gt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gioiTinhField.setText(gt2.getText());
            }
        });
        //Add popMenu to quanHeField;
        JPopupMenu popupMenu2 = new JPopupMenu();
        quanHeField.add(popupMenu2);
        quanHeField.setComponentPopupMenu(popupMenu2);

        JMenuItem qh1 = new JMenuItem("Chủ hộ");
        JMenuItem qh2 = new JMenuItem("Vợ");
        JMenuItem qh3 = new JMenuItem("Con");
        JMenuItem qh4 = new JMenuItem("Họ hàng");
        popupMenu2.add(qh1);
        popupMenu2.add(qh2);
        popupMenu2.add(qh3);
        popupMenu2.add(qh4);
        qh1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quanHeField.setText(qh1.getText());
            }
        });
        qh2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quanHeField.setText(qh2.getText());
            }
        });
        qh3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quanHeField.setText(qh3.getText());
            }
        });
        qh4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quanHeField.setText(qh4.getText());
            }
        });
        mainPanel.add(hoTenLabel);
        mainPanel.add(hoTenField);
        mainPanel.add(dobLabel);
        mainPanel.add(dobField);
        mainPanel.add(gioiTinhLabel);
        mainPanel.add(gioiTinhField);
        mainPanel.add(noiSinhLabel);
        mainPanel.add(noiSinhField);
        mainPanel.add(queQuanLabel);
        mainPanel.add(queQuanField);
        mainPanel.add(danTocLabel);
        mainPanel.add(danTocField);
        mainPanel.add(tonGiaoLabel);
        mainPanel.add(tonGiaoField);
        mainPanel.add(ngheNghiepLabel);
        mainPanel.add(ngheNghiepField);
        mainPanel.add(noiLamViecLabel);
        mainPanel.add(noiLamViecField);
        mainPanel.add(ngayDKLabel);
        mainPanel.add(ngayDKField);
        mainPanel.add(cccdLabel);
        mainPanel.add(cccdField);
        mainPanel.add(sdtLabel);
        mainPanel.add(sdtField);
        mainPanel.add(quanHeLabel);
        mainPanel.add(quanHeField);
        mainPanel.add(maHKLabel);
        mainPanel.add(maHKField);
        mainPanel.add(dinhDangLabel);
        mainPanel.add(warnLabel);
        mainPanel.add(addButton);
        mainPanel.add(chooseFileButton);


        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn file Excel");
                fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));

                int userSelection = fileChooser.showOpenDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    importDataFromExcel(selectedFile);
                }
            }
        });

        // ... (existing code)

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel);
        frame.setVisible(true);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth()*2/7;
                int height = frame.getHeight()/20;
                int X_left = frame.getWidth()/20;
                int Y_left = frame.getHeight()/40;
                int gap = frame.getHeight()/17;

                int X_button = (frame.getWidth() - width)/2;

                //Line 1
                hoTenLabel.setBounds(X_left, Y_left, width, height);
                dobLabel.setBounds(X_left + width, Y_left, width, height);
                gioiTinhLabel.setBounds(X_left + width*2, Y_left, width, height);
                Y_left += gap;
                //Line 2
                hoTenField.setBounds(X_left, Y_left, width, height);
                dobField.setBounds(X_left + width, Y_left, width, height);
                gioiTinhField.setBounds(X_left + width*2, Y_left, width, height);
                Y_left += gap;
                //Line 3
                noiSinhLabel.setBounds(X_left, Y_left, width*3, height);
                Y_left += gap;
                //Line 4
                noiSinhField.setBounds(X_left, Y_left, width*3, height);
                Y_left += gap;
                //Line 5
                queQuanLabel.setBounds(X_left, Y_left, width*3, height);
                Y_left += gap;
                //Line 6
                queQuanField.setBounds(X_left, Y_left, width*3, height);
                Y_left += gap;
                //Line 7
                danTocLabel.setBounds(X_left, Y_left, width, height);
                tonGiaoLabel.setBounds(X_left + width, Y_left, width, height);
                ngheNghiepLabel.setBounds(X_left + width*2, Y_left, width, height);
                Y_left += gap;
                //Line 8
                danTocField.setBounds(X_left, Y_left, width, height);
                tonGiaoField.setBounds(X_left + width, Y_left, width, height);
                ngheNghiepField.setBounds(X_left + width * 2, Y_left, width, height);
                Y_left += gap;
                //Line 9
                ngayDKLabel.setBounds(X_left, Y_left, width, height);
                cccdLabel.setBounds(X_left + width, Y_left, width, height);
                sdtLabel.setBounds(X_left + width*2, Y_left, width, height);
                Y_left += gap;
                //Line 10
                ngayDKField.setBounds(X_left, Y_left, width, height);
                cccdField.setBounds(X_left + width, Y_left, width, height);
                sdtField.setBounds(X_left + width*2, Y_left, width, height);
                Y_left += gap;
                //Line 11
                quanHeLabel.setBounds(X_left, Y_left, width, height);
                maHKLabel.setBounds(X_left + width, Y_left , width, height);
                Y_left += gap;
                //Line 12
                quanHeField.setBounds(X_left, Y_left, width, height);
                maHKField.setBounds(X_left + width, Y_left, width, height);
                Y_left += gap;
                //Line 13
                dinhDangLabel.setBounds(X_left, Y_left, width * 2, height);
                warnLabel.setBounds(X_left + width*2, Y_left, width*2, height);
                Y_left += gap;

                //Line 14
                addButton.setBounds(X_button, Y_left, width, height);
                Y_left += gap;
                chooseFileButton.setBounds(X_button, Y_left, width, height);


            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNKToSQL();
                JOptionPane.showMessageDialog(mainPanel, "Đã thêm nhân khẩu thành công");
                resetTextField();
            }
        });

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel);
        frame.setVisible(true);
    }


    public void addNKToSQL() {
        try {
            Connection connection = getConnectDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO nhan_khau(ho_va_ten, ngay_sinh, gioi_tinh, noi_sinh, " +
                    "que_quan, dan_toc, ton_giao, nghe_nghiep, noi_lam_viec, ngay_dang_ky, so_cccd, so_dien_thoai, quan_he_voi_chu_ho, ma_ho_khau, da_xac_nhan) "+
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, hoTenField.getText());
            preparedStatement.setDate(2, java.sql.Date.valueOf(dobField.getText()));
            preparedStatement.setString(3, gioiTinhField.getText());
            preparedStatement.setString(4, noiSinhField.getText());
            preparedStatement.setString(5, queQuanField.getText());
            preparedStatement.setString(6, danTocField.getText());
            preparedStatement.setString(7, tonGiaoField.getText());
            preparedStatement.setString(8, ngheNghiepField.getText());
            preparedStatement.setString(9, noiLamViecField.getText());
            preparedStatement.setDate(10, java.sql.Date.valueOf(ngayDKField.getText()));
            preparedStatement.setString(11, cccdField.getText());
            preparedStatement.setString(12, sdtField.getText());
            preparedStatement.setString(13, quanHeField.getText());
            preparedStatement.setInt(14, Integer.parseInt(maHKField.getText()));
            preparedStatement.setBoolean(15, true);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetTextField() {
        hoTenField.setText("");
        dobField.setText("");
        gioiTinhField.setText("");
        noiSinhField.setText("");
        queQuanField.setText("");
        tonGiaoField.setText("");
        ngheNghiepField.setText("");
        noiLamViecField.setText("");
        ngayDKField.setText("");
        cccdField.setText("");
        sdtField.setText("");
        quanHeField.setText("");
        maHKField.setText("");
    }

    private void importDataFromExcel(File file) {
        try (FileInputStream inputStream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            // Validate header row
            if (iterator.hasNext()) {
                Row headerRow = iterator.next();
                if (!isValidExcelHeader(headerRow)) {
                    JOptionPane.showMessageDialog(mainPanel, "File Excel không đúng định dạng.");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(mainPanel, "File Excel không có dữ liệu.");
                return;
            }

            // Continue with importing data...
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                String hoTen = cellIterator.next().getStringCellValue();
                String dob = new SimpleDateFormat("yyyy-MM-dd").format(cellIterator.next().getDateCellValue());
                String gioiTinh = cellIterator.next().getStringCellValue();
                String noiSinh = cellIterator.next().getStringCellValue();
                String queQuan = cellIterator.next().getStringCellValue();
                String danToc = cellIterator.next().getStringCellValue();
                String tonGiao = cellIterator.next().getStringCellValue();
                String ngheNghiep = cellIterator.next().getStringCellValue();
                String noiLamViec = cellIterator.next().getStringCellValue();
                String ngayDK = new SimpleDateFormat("yyyy-MM-dd").format(cellIterator.next().getDateCellValue());
                String cccd = cellIterator.next().getStringCellValue();
                String sdt = cellIterator.next().getStringCellValue();
                String quanHe = cellIterator.next().getStringCellValue();
                String maHK = String.valueOf((int) cellIterator.next().getNumericCellValue());

                try {
                    Connection connection = getConnectDatabase();
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "INSERT INTO nhan_khau(ho_va_ten, ngay_sinh, gioi_tinh, noi_sinh, " +
                                    "que_quan, dan_toc, ton_giao, nghe_nghiep, noi_lam_viec, ngay_dang_ky, so_cccd, so_dien_thoai, quan_he_voi_chu_ho, ma_ho_khau, da_xac_nhan) " +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    preparedStatement.setString(1, hoTen);
                    preparedStatement.setDate(2, java.sql.Date.valueOf(dob));
                    preparedStatement.setString(3, gioiTinh);
                    preparedStatement.setString(4, noiSinh);
                    preparedStatement.setString(5, queQuan);
                    preparedStatement.setString(6, danToc);
                    preparedStatement.setString(7, tonGiao);
                    preparedStatement.setString(8, ngheNghiep);
                    preparedStatement.setString(9, noiLamViec);
                    preparedStatement.setDate(10, java.sql.Date.valueOf(ngayDK));
                    preparedStatement.setString(11, cccd);
                    preparedStatement.setString(12, sdt);
                    preparedStatement.setString(13, quanHe);
                    preparedStatement.setInt(14, Integer.parseInt(maHK));
                    preparedStatement.setBoolean(15, true);

                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Lỗi khi thêm dữ liệu vào cơ sở dữ liệu: " + e.getMessage());
                    return;
                }
            }

            JOptionPane.showMessageDialog(mainPanel, "Đã thêm nhân khẩu từ file Excel thành công");
            resetTextField();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainPanel, "Lỗi khi đọc file Excel hoặc thêm dữ liệu: " + e.getMessage());
        }
    }

    private boolean isValidExcelHeader(Row headerRow) {
        // Define the expected headers
        String[] expectedHeaders = {"HoTen", "NgaySinh", "GioiTinh", "NoiSinh", "QueQuan", "DanToc", "TonGiao", "NgheNghiep", "NoiLamViec", "NgayDangKy", "CCCD", "SDT", "QuanHe", "MaHoKhau"};

        // Check if the number of columns in the header row matches the expected number
        if (headerRow.getPhysicalNumberOfCells() != expectedHeaders.length) {
            return false;
        }

        // Check if the headers match the expected order and names
        for (int i = 0; i < expectedHeaders.length; i++) {
            String expectedHeader = expectedHeaders[i];
            Cell cell = headerRow.getCell(i);
            if (cell == null || !expectedHeader.equals(cell.getStringCellValue())) {
                return false;
            }
        }

        return true;
    }




    public static void main(String[] args) {
        new ThemNhanKhau();
    }
}
