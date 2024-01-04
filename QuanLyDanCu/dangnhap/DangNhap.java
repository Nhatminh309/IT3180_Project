package quanlydancu.src.dangnhap;

import quanlydancu.src.connect.ConnectDatabase;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import quanlydancu.src.giaodien.GiaoDienBanDau;

public class DangNhap extends ConnectDatabase {
    private static JPanel topPanel;
    private static JPanel mainPanel;
    private static JFrame frame;
    private static JLabel taiKhoanLabel;
    private static JTextField taiKhoanField;
    private static JLabel matKhauLabel;
    private static JPasswordField matKhauField;
    private static JPanel dangKyPanel;
    private static JButton quenMatKhauButton;
    private static JButton dangNhapButton;
    public DangNhap() {
        frame = new JFrame("Đăng nhập");
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
        ImageIcon icon = new ImageIcon("QuanLyDanCu/src/icon/goBackIcon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(35, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        backButton.setIcon(scaledIcon);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(backButton, BorderLayout.WEST);

        JLabel dangNhapLabel = new JLabel("Đăng nhập");
        dangNhapLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        dangNhapLabel.setHorizontalAlignment(SwingConstants.CENTER); // Align label to the center
        dangNhapLabel.setForeground(Color.decode("#38B6FF"));
        topPanel.add(dangNhapLabel, BorderLayout.CENTER);

        mainPanel = new JPanel();
        //mainPanel.setLayout(null);
        //mainPanel.setBackground(Color.decode("#FBFCFC"));

        Font newFont = new Font("Arial", Font.PLAIN, 24);
        taiKhoanLabel = new JLabel("Tài khoản:");
        taiKhoanField = new JTextField(20);
        matKhauLabel = new JLabel("Mật khẩu:");
        matKhauField = new JPasswordField(20);
        quenMatKhauButton = new JButton("Quên mật khẩu ?");
        dangNhapButton = new JButton("Đăng nhập");

        taiKhoanLabel.setFont(newFont);
        matKhauLabel.setFont(newFont);
        quenMatKhauButton.setFont(new Font("Arial", Font.PLAIN, 15));
        dangNhapButton.setFont(new Font("Arial", Font.BOLD, 20));
        dangNhapButton.setBackground(Color.decode("#38B6FF"));
        dangNhapButton.setOpaque(true);
        dangNhapButton.setBorder(BorderFactory.createEmptyBorder());
        quenMatKhauButton.setBorder(BorderFactory.createEmptyBorder());


        dangKyPanel = new JPanel();
        dangKyPanel.setLayout(new BoxLayout(dangKyPanel, BoxLayout.X_AXIS));
        dangKyPanel.setBorder(BorderFactory.createEmptyBorder());
        dangKyPanel.setOpaque(false);
        JLabel askLabel = new JLabel("Chưa có tài khoản ? ");
        askLabel.setFont(newFont);
        JButton dangKyButton = new JButton("Đăng ký");
        dangKyButton.setFont(newFont);
        dangKyButton.setForeground(Color.decode("#38B6FF"));
        dangKyButton.setBorder(BorderFactory.createEmptyBorder());
        dangKyButton.setBorderPainted(false);
        dangKyButton.setFocusable(false);

        dangKyPanel.add(askLabel);
        dangKyPanel.add(dangKyButton);

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Setting insets for components

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(taiKhoanLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components stretch horizontally
        mainPanel.add(taiKhoanField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(matKhauLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        mainPanel.add(matKhauField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE; // Set to none for this button
        mainPanel.add(quenMatKhauButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components stretch horizontally
        mainPanel.add(dangNhapButton, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        mainPanel.add(dangKyPanel, gbc);

        taiKhoanLabel.setPreferredSize(new Dimension(300, 30));
        taiKhoanField.setPreferredSize(new Dimension(300, 50));
        matKhauLabel.setPreferredSize(new Dimension(300, 30));
        matKhauField.setPreferredSize(new Dimension(300, 50));
        dangNhapButton.setPreferredSize(new Dimension(300, 50));

        dangNhapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(taiKhoanField.getText().equals("") || String.valueOf(matKhauField.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Đăng nhập không thành công");
                } else if(validateSignIn(taiKhoanField.getText(), matKhauField.getText()) == null) {
                    JOptionPane.showMessageDialog(mainPanel, "Tài khoản hoặc mật khẩu chưa chính xác");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Đăng nhập thành công");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GiaoDienBanDau();
                frame.dispose();
            }
        });
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public String validateSignIn(String username, String password) {
        String role = null;
        try {
            Connection connection = getConnectDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM dangnhap WHERE username = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                role = resultSet.getString("vaitro");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
    public static void main(String[] args) {
        new DangNhap();
    }
}

