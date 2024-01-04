package quanlynhankhau;

import connect.ConnectDatabase;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ChinhSuaNhanKhau extends ConnectDatabase {
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
    private static JLabel xacNhanLabel;
    private static JTextField xacNhanField;
    private static JLabel searchLabel;
    private static JTextField searchField;
    private static JButton searchButton;
    private static JButton changeButton;
    private static JButton removeButton;
    private static JMenuBar menu;
    private static Map<String, String> attributeMap = new HashMap<>();

    public ChinhSuaNhanKhau() {
        frame = new JFrame("Chỉnh sửa nhân khẩu");
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

        JLabel chinhSuaNKLabel = new JLabel("Chỉnh sửa nhân khẩu");
        chinhSuaNKLabel.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/20));
        chinhSuaNKLabel.setHorizontalAlignment(SwingConstants.CENTER); // Align label to the center
        chinhSuaNKLabel.setForeground(Color.decode("#38B6FF"));
        topPanel.add(chinhSuaNKLabel, BorderLayout.CENTER);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        //Search bar
        searchLabel = new JLabel("Tìm kiếm nhân khẩu theo mã nhân khẩu...");
        searchField = new JTextField();
        searchButton = new JButton();
        ImageIcon icon2 = new ImageIcon("src/icon/searchIcon.png");
        Image img2 = icon2.getImage();
        Image scaledImg2 = img2.getScaledInstance(frame.getWidth()/35, frame.getHeight()/35, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImg2);
        searchButton.setIcon(scaledIcon2);

        hoTenLabel = new JLabel("Họ và tên: ");
        hoTenField = new JTextField();
        dobLabel = new JLabel("Ngày sinh: ");
        dobField = new JTextField();
        gioiTinhLabel = new JLabel("Giới tính: ");
        gioiTinhField = new JTextField();
        noiSinhLabel = new JLabel("Nơi sinh: ");
        noiSinhField = new JTextField();
        queQuanLabel = new JLabel("Quê quán:");
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
        cccdLabel = new JLabel("Căn cước công dân:");
        cccdField = new JTextField();
        sdtLabel = new JLabel("Số điện thoại:");
        sdtField = new JTextField();
        quanHeLabel = new JLabel("Quan hệ hộ khẩu:");
        quanHeField = new JTextField();
        maHKLabel = new JLabel("Mã hộ khẩu:");
        maHKField = new JTextField();
        xacNhanLabel = new JLabel("Đã xác nhận");
        xacNhanField = new JTextField();
        changeButton = new JButton("Thay đổi");
        removeButton = new JButton("Xoá");

        Font newFont = new Font("Arial", Font.PLAIN, frame.getWidth()/50);
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
        xacNhanLabel.setFont(newFont);
        searchLabel.setFont(newFont);

        changeButton.setFont(newFont);
        changeButton.setBackground(Color.decode("#38B6FF"));
        changeButton.setOpaque(true);
        changeButton.setBorder(BorderFactory.createEmptyBorder());
        changeButton.setFocusable(true);
        removeButton.setFont(newFont);
        removeButton.setBackground(Color.decode("#38B6FF"));
        removeButton.setOpaque(true);
        removeButton.setBorder(BorderFactory.createEmptyBorder());
        changeButton.setFocusable(true);


        mainPanel.add(searchLabel);
        mainPanel.add(searchField);
        mainPanel.add(searchButton);
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
        mainPanel.add(xacNhanLabel);
        mainPanel.add(xacNhanField);




        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth()*2/7;
                int height = frame.getHeight()/20;
                int X_left = frame.getWidth()/20;
                int Y_left = frame.getHeight()/6;
                int gap = frame.getHeight()/20;

                int X_button = (frame.getWidth() - width*2)/2;
                int Y_button = frame.getHeight()/40;
                searchLabel.setBounds(X_button, Y_button, width*2, height);
                Y_button += gap;
                searchField.setBounds(X_button, Y_button, width*3/2, height);
                searchButton.setBounds(X_button + searchField.getWidth(), Y_button, width*2/7, height);
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
                xacNhanLabel.setBounds(X_left + width*2, Y_left, width, height);
                Y_left += gap;
                //Line 12
                quanHeField.setBounds(X_left, Y_left, width, height);
                maHKField.setBounds(X_left + width, Y_left, width, height);
                xacNhanField.setBounds(X_left + width*2, Y_left, width, height);
                Y_left += gap*2;

                changeButton.setBounds((frame.getWidth()-width)/2, Y_left, width/2, height);
                removeButton.setBounds((frame.getWidth()-width)/2 + changeButton.getWidth() + 20, Y_left, width/2, height);

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!searchField.getText().equals("")) {
                    searchNKInSQL(Integer.parseInt(searchField.getText()));
                    mainPanel.add(changeButton);
                    mainPanel.add(removeButton);
                    mainPanel.revalidate();
                    mainPanel.repaint();

                }
            }
        });
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(searchField.getText().equals("")) {
                    resetTextField();
                    mainPanel.remove(changeButton);
                    mainPanel.remove(removeButton);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeInSQL();
                resetTextField();
                JOptionPane.showMessageDialog(mainPanel, "Thay đổi thành công");
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(quanHeField.getText().equals("Chủ hộ")) {
                    JOptionPane.showMessageDialog(mainPanel, "Không thể xoá! Hãy đổi chủ hộ trước");
                    resetTextField();
                } else {
                    removeInSQL();
                    resetTextField();
                    JOptionPane.showMessageDialog(mainPanel, "Đã xoá nhân khẩu này");
                }
            }
        });
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public void searchNKInSQL(int maNK) {
//        Map<String, String> convertAttribute = new HashMap<>();
//        convertAttribute.put("Căn cước công dân", "so_cccd");
//        convertAttribute.put("Mã nhân khẩu", "ma_nhan_khau");
        try {
            Connection connection = getConnectDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ho_va_ten, ngay_sinh, gioi_tinh, noi_sinh, " +
                    "que_quan, dan_toc, ton_giao, nghe_nghiep, noi_lam_viec, ngay_dang_ky, so_cccd, so_dien_thoai, quan_he_voi_chu_ho, ma_ho_khau, da_xac_nhan " +
                    " FROM nhan_khau WHERE ma_nhan_khau = ?" );
            preparedStatement.setInt(1, maNK);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                hoTenField.setText(resultSet.getString("ho_va_ten"));
                dobField.setText(String.valueOf(resultSet.getDate("ngay_sinh")));
                gioiTinhField.setText(resultSet.getString("gioi_tinh"));
                noiSinhField.setText(resultSet.getString("noi_sinh"));
                queQuanField.setText(resultSet.getString("que_quan"));
                tonGiaoField.setText(resultSet.getString("ton_giao"));
                ngheNghiepField.setText(resultSet.getString("nghe_nghiep"));
                noiLamViecField.setText(resultSet.getString("noi_lam_viec"));
                ngayDKField.setText(String.valueOf(resultSet.getDate("ngay_dang_ky")));
                cccdField.setText(resultSet.getString("so_cccd"));
                sdtField.setText(resultSet.getString("so_dien_thoai"));
                quanHeField.setText(resultSet.getString("quan_he_voi_chu_ho"));
                maHKField.setText(String.valueOf(resultSet.getInt("ma_ho_khau")));
                xacNhanField.setText(String.valueOf(resultSet.getBoolean("da_xac_nhan")));

                //add text to map to store the initial value
                attributeMap.put("ho_va_ten", hoTenField.getText());
                attributeMap.put("ngay_sinh", dobField.getText());
                attributeMap.put("gioi_tinh", gioiTinhField.getText());
                attributeMap.put("noi_sinh", noiSinhField.getText());
                attributeMap.put("que_quan", queQuanField.getText());
                attributeMap.put("ton_giao", tonGiaoField.getText());
                attributeMap.put("nghe_nghiep", ngheNghiepField.getText());
                attributeMap.put("noi_lam_viec", noiLamViecField.getText());
                attributeMap.put("ngay_dang_ky", ngayDKField.getText());
                attributeMap.put("so_cccd", cccdField.getText());
                attributeMap.put("so_dien_thoai", sdtField.getText());
                attributeMap.put("quan_he_voi_chu_ho", quanHeField.getText());
                attributeMap.put("ma_ho_khau", maHKField.getText());
                attributeMap.put("da_xac_nhan", xacNhanField.getText());
            } else {
                resetTextField();
                JOptionPane.showMessageDialog(mainPanel, "Nhân khẩu không tồn tại");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void changeInSQL() {
        try {
            Connection connection = getConnectDatabase();
            StringBuilder updateQuery = new StringBuilder("UPDATE nhan_khau SET ");
            if(!hoTenField.getText().equals(attributeMap.get("ho_va_ten"))) {
                updateQuery.append("ho_va_ten = ?, ");
            }
            if(!dobField.getText().equals(attributeMap.get("ngay_sinh"))) {
                updateQuery.append("ngay_sinh = ?, ");
            }
            if(!gioiTinhField.getText().equals(attributeMap.get("gioi_tinh"))) {
                updateQuery.append("gioi_tinh = ?, ");
            }
            if(!noiSinhField.getText().equals(attributeMap.get("noi_sinh"))) {
                updateQuery.append("noi_sinh = ?, ");
            }
            if((!queQuanField.getText().equals(attributeMap.get("que_quan")))) {
                updateQuery.append("que_quan = ?, ");
            }
            if(!tonGiaoField.getText().equals(attributeMap.get("ton_giao"))) {
                updateQuery.append("ton_giao = ?, ");
            }
            if(!ngheNghiepField.getText().equals(attributeMap.get("nghe_nghiep"))) {
                updateQuery.append("nghe_nghiep = ?, ");
            }
            if(!noiLamViecField.getText().equals(attributeMap.get("noi_lam_viec"))) {
                updateQuery.append("noi_lam_viec = ?, ");
            }
            if(!ngayDKField.getText().equals(attributeMap.get("ngay_dang_ky"))) {
                updateQuery.append("ngay_dang_ky = ?, ");
            }
            if(!cccdField.getText().equals(attributeMap.get("so_cccd"))) {
                updateQuery.append("so_cccd = ?, ");
            }
            if(!sdtField.getText().equals(attributeMap.get("so_dien_thoai"))) {
                updateQuery.append("so_dien_thoai = ?, ");
            }
            if(!quanHeField.getText().equals(attributeMap.get("quan_he_voi_chu_ho"))) {
                updateQuery.append("quan_he_voi_chu_ho = ?, ");
            }
            if(!maHKField.getText().equals(attributeMap.get("ma_ho_khau"))) {
                updateQuery.append("ma_ho_khau = ?, ");
            }
            if(!xacNhanField.getText().equals(attributeMap.get("da_xac_nhan"))) {
                updateQuery.append("da_xac_nhan = ?, ");
            }
            //remove the last comma
            if(updateQuery.toString().endsWith(", ")) {
                updateQuery.setLength(updateQuery.length() - 2);
            }
            updateQuery.append(" WHERE ma_nhan_khau = ?");
            int parameter = 1;
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery.toString());
            //UPDATE SQL
            if(!hoTenField.getText().equals(attributeMap.get("ho_va_ten"))) {
                preparedStatement.setString(parameter++, hoTenField.getText());
            }
            if(!dobField.getText().equals(attributeMap.get("ngay_sinh"))) {
                preparedStatement.setDate(parameter++, Date.valueOf(dobField.getText()));
            }
            if(!gioiTinhField.getText().equals(attributeMap.get("gioi_tinh"))) {
                preparedStatement.setString(parameter++, gioiTinhField.getText());
            }
            if(!noiSinhField.getText().equals(attributeMap.get("noi_sinh"))) {
                preparedStatement.setString(parameter++, noiSinhField.getText());
            }
            if((!queQuanField.getText().equals(attributeMap.get("que_quan")))) {
                preparedStatement.setString(parameter++, queQuanField.getText());
            }
            if(!tonGiaoField.getText().equals(attributeMap.get("ton_giao"))) {
                preparedStatement.setString(parameter++, tonGiaoField.getText());
            }
            if(!ngheNghiepField.getText().equals(attributeMap.get("nghe_nghiep"))) {
                preparedStatement.setString(parameter++, ngheNghiepField.getText());
            }
            if(!noiLamViecField.getText().equals(attributeMap.get("noi_lam_viec"))) {
                preparedStatement.setString(parameter++, noiLamViecField.getText());
            }
            if(!ngayDKField.getText().equals(attributeMap.get("ngay_dang_ky"))) {
                preparedStatement.setDate(parameter++, Date.valueOf(ngayDKField.getText()));
            }
            if(!cccdField.getText().equals(attributeMap.get("so_cccd"))) {
                preparedStatement.setString(parameter++, cccdField.getText());
            }
            if(!sdtField.getText().equals(attributeMap.get("so_dien_thoai"))) {
                preparedStatement.setString(parameter++, sdtField.getText());
            }
            if(!quanHeField.getText().equals(attributeMap.get("quan_he_voi_chu_ho"))) {
                preparedStatement.setString(parameter++, quanHeField.getText());
            }
            if(!maHKField.getText().equals(attributeMap.get("ma_ho_khau"))) {
                preparedStatement.setInt(parameter, Integer.parseInt(maHKField.getText()));
            }
            if(!xacNhanField.getText().equals(attributeMap.get("da_xac_nhan"))) {
                preparedStatement.setBoolean(parameter, Boolean.parseBoolean(xacNhanField.getText()));
            }
            preparedStatement.setInt(parameter, Integer.parseInt(searchField.getText()));
            System.out.println(updateQuery.toString());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeInSQL() {
        try {
            Connection connection = getConnectDatabase();
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM tam_vang " +
                    "WHERE ma_nhan_khau = ? " +
                    "AND EXISTS ( " +
                    "SELECT 1 " +
                    "FROM nhan_khau " +
                    "WHERE ma_nhan_khau = ?" +
                    ");");
            preparedStatement1.setInt(1, Integer.parseInt(searchField.getText()));
            preparedStatement1.setInt(2, Integer.parseInt(searchField.getText()));
            preparedStatement1.executeUpdate();

            PreparedStatement preparedStatement2 = connection.prepareStatement("DELETE FROM so_tam_tru " +
                    "WHERE ma_nhan_khau = ? " +
                    "AND EXISTS ( " +
                    "SELECT 1 " +
                    "FROM nhan_khau " +
                    "WHERE ma_nhan_khau = ?" +
                    ");");
            preparedStatement2.setInt(1, Integer.parseInt(searchField.getText()));
            preparedStatement2.setInt(2, Integer.parseInt(searchField.getText()));
            preparedStatement2.executeUpdate();

            PreparedStatement preparedStatement3 = connection.prepareStatement("DELETE FROM nhan_khau WHERE ma_nhan_khau = ?");
            preparedStatement3.setInt(1, Integer.parseInt(searchField.getText()));
            preparedStatement3.executeUpdate();

            preparedStatement1.close();
            preparedStatement2.close();
            preparedStatement3.close();
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
        xacNhanField.setText("");
    }
    public static void main(String[] args) {
        new ChinhSuaNhanKhau();
    }
}
