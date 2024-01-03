package dangky;

import connect.ConnectDatabase;
import dangnhap.DangNhap;
import giaodien.GiaoDienBanDau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DangKy extends ConnectDatabase {
    private static JPanel topPanel;
    private static JPanel mainPanel;
    private static JFrame frame;
    private static JLabel hoTenLabel;
    private static JTextField hoTenField;
    private static JLabel dobLabel;
    private static JTextField dobField;
    private static JLabel gioiTinhLabel;
    private static JTextField gioiTinhField;
    private static JLabel chucVuLabel;
    private static JTextField chucVuField;
    private static JLabel dinhDangLabel;
    private static JLabel taiKhoanLabel;
    private static JTextField taiKhoanField;
    private static JLabel matKhauLabel;
    private static JPasswordField matKhauField;
    private static JButton dangKyButton;
    private static JPanel dangNhapPanel;
    public DangKy() {
        frame = new JFrame("Đăng ký");
        frame.setSize(1000, 578);
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
        Image scaledImg = img.getScaledInstance(35, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        backButton.setIcon(scaledIcon);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(backButton, BorderLayout.WEST);

        JLabel dangNhapLabel = new JLabel("Đăng ký");
        dangNhapLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        dangNhapLabel.setHorizontalAlignment(SwingConstants.CENTER); // Align label to the center
        dangNhapLabel.setForeground(Color.decode("#38B6FF"));
        topPanel.add(dangNhapLabel, BorderLayout.CENTER);

        //Main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        hoTenLabel = new JLabel("Họ và tên: ");
        hoTenField = new JTextField();
        dobLabel = new JLabel("Ngày sinh: ");
        dobField = new JTextField();
        gioiTinhLabel = new JLabel("Giới tính: ");
        gioiTinhField = new JTextField();
        chucVuLabel = new JLabel("Chức vụ: ");
        chucVuField = new JTextField();
        taiKhoanLabel = new JLabel("Tên tài khoản (*)");
        taiKhoanField = new JTextField();
        matKhauLabel = new JLabel("Mật khẩu (*): ");
        matKhauField = new JPasswordField();
        dinhDangLabel = new JLabel("Định dạng: YYYY-MM-DD");
        dangKyButton = new JButton("Đăng ký");

        //Set icon for dinhDangLabel
        ImageIcon icon2 = new ImageIcon("src/icon/aboutIcon.png");
        Image img2 = icon2.getImage();
        Image scaledImg2 = img2.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImg2);
        dinhDangLabel.setIcon(scaledIcon2);
        dinhDangLabel.setIconTextGap(10);

        Font newFont = new Font("Arial", Font.PLAIN, 24);
        hoTenLabel.setFont(newFont);
        dobLabel.setFont(newFont);
        gioiTinhLabel.setFont(newFont);
        chucVuLabel.setFont(newFont);
        taiKhoanLabel.setFont(newFont);
        matKhauLabel.setFont(newFont);
        dinhDangLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        dangKyButton.setFont(newFont);
        dangKyButton.setBackground(Color.decode("#38B6FF"));
        dangKyButton.setOpaque(true);
        dangKyButton.setBorder(BorderFactory.createEmptyBorder());

        dangNhapPanel = new JPanel();
        dangNhapPanel.setLayout(new BoxLayout(dangNhapPanel, BoxLayout.X_AXIS));
        dangNhapPanel.setBorder(BorderFactory.createEmptyBorder());
        dangNhapPanel.setOpaque(false);
        JLabel askLabel = new JLabel("Đã tài khoản ? ");
        askLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JButton dangNhapButton = new JButton("Đăng nhập");
        dangNhapButton.setFont(new Font("Arial", Font.PLAIN, 16));
        dangNhapButton.setForeground(Color.decode("#38B6FF"));
        dangNhapButton.setBorder(BorderFactory.createEmptyBorder());
        dangNhapButton.setBorderPainted(false);
        dangNhapButton.setFocusable(false);
        dangNhapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DangNhap();
                frame.dispose();
            }
        });

        dangNhapPanel.add(askLabel);
        dangNhapPanel.add(dangNhapButton);

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

        //Add popMenu to chucVuField
        JPopupMenu popupMenu2 = new JPopupMenu();
        chucVuField.add(popupMenu2);
        chucVuField.setComponentPopupMenu(popupMenu2);

        JMenuItem cv1 = new JMenuItem("Tổ trưởng dân phố");
        JMenuItem cv2 = new JMenuItem("Quản lý chung cư");
        popupMenu2.add(cv1);
        popupMenu2.add(cv2);
        cv1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chucVuField.setText(cv1.getText());
            }
        });
        cv2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chucVuField.setText(cv2.getText());
            }
        });

        mainPanel.add(hoTenLabel);
        mainPanel.add(hoTenField);
        mainPanel.add(dobLabel);
        mainPanel.add(dobField);
        mainPanel.add(dinhDangLabel);
        mainPanel.add(gioiTinhLabel);
        mainPanel.add(gioiTinhField);
        mainPanel.add(chucVuLabel);
        mainPanel.add(chucVuField);
        mainPanel.add(taiKhoanLabel);
        mainPanel.add(taiKhoanField);
        mainPanel.add(matKhauLabel);
        mainPanel.add(matKhauField);
        mainPanel.add(dangKyButton);
        mainPanel.add(dangNhapPanel);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int X_left = frame.getWidth()/10;
                int Y_left = frame.getHeight()/15;
                hoTenLabel.setBounds(X_left, Y_left, 200, 40);
                Y_left += 40;
                hoTenField.setBounds(X_left, Y_left, 200, 40);
                Y_left += 40;
                dobLabel.setBounds(X_left, Y_left, 200, 40);
                Y_left += 40;
                dobField.setBounds(X_left, Y_left, 200, 40);
                dinhDangLabel.setBounds(X_left + 220, Y_left, 300, 40);
                Y_left += 40;
                gioiTinhLabel.setBounds(X_left, Y_left, 200, 40);
                Y_left += 40;
                gioiTinhField.setBounds(X_left, Y_left, 200, 40);
                Y_left += 40;
                chucVuLabel.setBounds(X_left, Y_left, 200,40);
                Y_left += 40;
                chucVuField.setBounds(X_left, Y_left, 200, 40);

                int X_right = frame.getWidth()*3/4;
                int Y_right = frame.getHeight()/15;
                taiKhoanLabel.setBounds(X_right, Y_right, 200, 40);
                Y_right += 40;
                taiKhoanField.setBounds(X_right, Y_right, 200, 40);
                Y_right += 40;
                matKhauLabel.setBounds(X_right, Y_right, 200, 40);
                Y_right += 40;
                matKhauField.setBounds(X_right, Y_right, 200, 40);

                int X_center = (frame.getWidth() - 200) / 2;
                int Y_center = frame.getHeight() * 2 / 3;
                dangKyButton.setBounds(X_center, Y_center, 200, 40);
                Y_center += 40;
                dangNhapPanel.setBounds(X_center, Y_center , 200, 40);

            }
        });
        dangNhapPanel.setBounds((frame.getWidth() - 200) / 2, (frame.getHeight() * 2 / 3) + 40 , 200, 40);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GiaoDienBanDau();
                frame.dispose();
            }
        });
        dangKyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSignInToSQL();
                new DangNhap();
                frame.dispose();
            }
        });

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public void addSignInToSQL() {
        try {
            Connection connection = getConnectDatabase();
            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO can_bo(ho_ten, ngay_sinh, gioi_tinh, chuc_vu) VALUES(?, ?, ?, ?)");
            preparedStatement1.setString(1, hoTenField.getText());
            preparedStatement1.setDate(2, Date.valueOf(dobField.getText()));
            preparedStatement1.setString(3, gioiTinhField.getText());
            preparedStatement1.setString(4, chucVuField.getText());
            preparedStatement1.executeUpdate();

            PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO dangnhap VALUES(?, ?, ?)");
            preparedStatement2.setString(1, taiKhoanField.getText());
            preparedStatement2.setString(2, String.valueOf(matKhauField.getPassword()));
            preparedStatement2.setString(3, "admin");
            preparedStatement2.executeUpdate();

            preparedStatement1.close();
            preparedStatement2.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new DangKy();
    }
}
