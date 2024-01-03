package dangnhap;

import connect.ConnectDatabase;
import giaodien.GiaoDienBanDau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ThayDoiMatKhau extends ConnectDatabase {
    private static JPanel topPanel;
    private static JPanel mainPanel;
    private static JFrame frame;
    private static JLabel taiKhoanLabel;
    private static JTextField taiKhoanField;
    private static JLabel matKhauMoiLabel;
    private static JPasswordField matKhauMoiField;
    private static JLabel nhapLaiMKLabel;
    private static JPasswordField nhapLaiMKField;
    private static JButton thayDoiButton;
    public ThayDoiMatKhau() {
        frame = new JFrame("Thay đổi mật khẩu");
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

        JLabel dangNhapLabel = new JLabel("Thay đổi mật khẩu");
        dangNhapLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        dangNhapLabel.setHorizontalAlignment(SwingConstants.CENTER); // Align label to the center
        dangNhapLabel.setForeground(Color.decode("#38B6FF"));
        topPanel.add(dangNhapLabel, BorderLayout.CENTER);

        mainPanel = new JPanel();

        Font newFont = new Font("Arial", Font.PLAIN, 24);
        taiKhoanLabel = new JLabel("Tài khoản:");
        taiKhoanField = new JTextField(20);
        matKhauMoiLabel = new JLabel("Mật khẩu mới:");
        matKhauMoiField = new JPasswordField(20);
        nhapLaiMKLabel = new JLabel("Nhập lại mật khẩu mới:");
        nhapLaiMKField = new JPasswordField(20);
        thayDoiButton = new JButton("Thay đổi mật khẩu");

        taiKhoanLabel.setFont(newFont);
        matKhauMoiLabel.setFont(newFont);
        nhapLaiMKLabel.setFont(newFont);
        thayDoiButton.setFont(new Font("Arial", Font.BOLD, 20));
        thayDoiButton.setBackground(Color.decode("#38B6FF"));
        thayDoiButton.setOpaque(true);
        thayDoiButton.setBorder(BorderFactory.createEmptyBorder());


        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Setting insets for components

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(taiKhoanLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components stretch horizontally
        mainPanel.add(taiKhoanField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(matKhauMoiLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(matKhauMoiField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(nhapLaiMKLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(nhapLaiMKField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components stretch horizontally
        mainPanel.add(thayDoiButton, gbc);


        thayDoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!String.valueOf(matKhauMoiField.getPassword()).equals(String.valueOf(nhapLaiMKField.getPassword()))) {
                    JLabel warnLabel = new JLabel("Mật khẩu không trùng nhau");
                    warnLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                    ImageIcon icon2 = new ImageIcon("src/icon/aboutIcon.png");
                    Image img2 = icon2.getImage();
                    Image scaledImg2 = img2.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon2 = new ImageIcon(scaledImg2);
                    warnLabel.setIcon(scaledIcon2);
                    warnLabel.setIconTextGap(10);

                    GridBagConstraints gbcWarning = new GridBagConstraints();
                    gbcWarning.gridx = 0;
                    gbcWarning.gridy = 6;
                    gbc.anchor = GridBagConstraints.WEST;
                    mainPanel.add(warnLabel, gbcWarning);

                    mainPanel.revalidate();
                    mainPanel.repaint();
                } else {
                    addNewPswToSQL();
                    new DangNhap();
                    frame.dispose();
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
        taiKhoanField.setPreferredSize(new Dimension(300, 50));
        matKhauMoiField.setPreferredSize(new Dimension(300, 50));
        nhapLaiMKField.setPreferredSize(new Dimension(300, 50));
        thayDoiButton.setPreferredSize(new Dimension(300, 50));

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public void addNewPswToSQL() {
        try {
            Connection connection = getConnectDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE dangnhap SET password = ? WHERE username = ?");
            preparedStatement.setString(1, String.valueOf(matKhauMoiField.getPassword()));
            preparedStatement.setString(2, taiKhoanField.getText());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new ThayDoiMatKhau();
    }
}
