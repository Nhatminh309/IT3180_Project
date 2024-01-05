package QuanLyDanCu.src.dangky;

import QuanLyDanCu.src.connect.ConnectDatabase;
import QuanLyDanCu.src.dangnhap.DangNhap;
import QuanLyDanCu.src.giaodien.GiaoDienBanDau;

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
    private static JLabel nhapMatKhauLabel;
    private static JPasswordField nhapMatKhauField;
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
        backButton.setBackground(Color.WHITE);
        ImageIcon icon = new ImageIcon("QuanLyDanCu/src/icon/goBackIcon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(frame.getWidth()/20, frame.getHeight()/19, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        backButton.setIcon(scaledIcon);
        backButton.setBorder(null);
        backButton.setBorderPainted(false);
        backButton.setFocusable(false);
        backButton.setContentAreaFilled(false);

        topPanel.add(backButton, BorderLayout.WEST);

        JLabel dangNhapLabel = new JLabel("Đăng ký");
        dangNhapLabel.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/20));
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
        taiKhoanLabel = new JLabel("Tên tài khoản:");
        taiKhoanField = new JTextField();
        matKhauLabel = new JLabel("Mật khẩu: ");
        matKhauField = new JPasswordField();
        nhapMatKhauLabel = new JLabel("Nhập lại mật khẩu:");
        nhapMatKhauField = new JPasswordField();
        dinhDangLabel = new JLabel("Định dạng: YYYY-MM-DD");
        dangKyButton = new JButton("Đăng ký");

        //Set icon for dinhDangLabel
        ImageIcon icon2 = new ImageIcon("QuanLyDanCu/src/icon/aboutIcon.png");
        Image img2 = icon2.getImage();
        Image scaledImg2 = img2.getScaledInstance(frame.getWidth()/50, frame.getHeight()/40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImg2);
        dinhDangLabel.setIcon(scaledIcon2);
        dinhDangLabel.setIconTextGap(10);

        Font newFont = new Font("Arial", Font.PLAIN, frame.getWidth()/45);
        hoTenLabel.setFont(newFont);
        dobLabel.setFont(newFont);
        gioiTinhLabel.setFont(newFont);
        chucVuLabel.setFont(newFont);
        taiKhoanLabel.setFont(newFont);
        matKhauLabel.setFont(newFont);
        nhapMatKhauLabel.setFont(newFont);
        dinhDangLabel.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/55));
        dangKyButton.setFont(newFont);
        dangKyButton.setBackground(Color.decode("#38B6FF"));
        dangKyButton.setOpaque(true);
        dangKyButton.setBorder(BorderFactory.createEmptyBorder());

//        dangNhapPanel = new JPanel();
//        dangNhapPanel.setLayout(new BoxLayout(dangNhapPanel, BoxLayout.X_AXIS));
//        dangNhapPanel.setBorder(BorderFactory.createEmptyBorder());
//        dangNhapPanel.setOpaque(false);
        JLabel askLabel = new JLabel("Đã có tài khoản ? ");
        askLabel.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/60));
        JButton dangNhapButton = new JButton("Đăng nhập") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(frame.getWidth()/10, frame.getHeight()/20);
            }

        };
        dangNhapButton.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/60));
        dangNhapButton.setForeground(Color.decode("#38B6FF"));
        dangNhapButton.setBorder(null);
        dangNhapButton.setBorderPainted(false);
        dangNhapButton.setBackground(Color.decode("#EDEDED"));
        dangNhapButton.setFocusable(false);
        dangNhapButton.setContentAreaFilled(false);
        dangNhapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DangNhap();
                frame.dispose();
            }
        });

//        dangNhapPanel.add(askLabel);
//        dangNhapPanel.add(dangNhapButton);

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

        JMenuItem cv1 = new JMenuItem("Tổ trưởng tổ dân phố");
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
        mainPanel.add(nhapMatKhauLabel);
        mainPanel.add(nhapMatKhauField);
        mainPanel.add(dangKyButton);
        mainPanel.add(askLabel);
        mainPanel.add(dangNhapButton);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth()*2/7;
                int height = frame.getHeight()/15;
                int X_left = (frame.getWidth() - width*2)/2;
                int Y_left = frame.getHeight()/20;
                int gap = frame.getHeight()/17;
                //Line 1
                hoTenLabel.setBounds(X_left, Y_left, width, height);
                dobLabel.setBounds(X_left + hoTenLabel.getWidth(), Y_left, width, height);
                Y_left += gap;
                //Line 2
                hoTenField.setBounds(X_left, Y_left, width, height);
                dobField.setBounds(X_left + hoTenField.getWidth(), Y_left, width, height);
                Y_left += gap;
                //Line 3
                gioiTinhLabel.setBounds(X_left, Y_left, width, height);
                chucVuLabel.setBounds(X_left + gioiTinhLabel.getWidth(), Y_left, width, height);
                Y_left += gap;
                //Line 4
                gioiTinhField.setBounds(X_left, Y_left, width, height);
                chucVuField.setBounds(X_left + gioiTinhField.getWidth(), Y_left, width, height);
                Y_left += gap;
                //Line 5
                taiKhoanLabel.setBounds(X_left, Y_left, width, height);
                Y_left += gap;
                //Line 6
                taiKhoanField.setBounds(X_left, Y_left, width*2, height);
                Y_left += gap;
                //Line 7
                matKhauLabel.setBounds(X_left, Y_left, width, height);
                Y_left += gap;
                //Line 8
                matKhauField.setBounds(X_left, Y_left, width*2, height);
                Y_left += gap;
                //Line 9
                nhapMatKhauLabel.setBounds(X_left, Y_left, width, height);
                Y_left += gap;
                //Line 10
                nhapMatKhauField.setBounds(X_left, Y_left, width*2, height);
                Y_left += gap;

                int Y_dinhDang = Y_left;
                nhapMatKhauField.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {

                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if(!String.valueOf(matKhauField.getPassword()).equals(String.valueOf(nhapMatKhauField.getPassword()))) {
                            JLabel warnLabel  = new JLabel("Mật khẩu không trùng nhau");
                            mainPanel.add(warnLabel);
                            warnLabel.setIcon(scaledIcon2);
                            warnLabel.setIconTextGap(10);
                            warnLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                            warnLabel.setBounds(X_left, Y_dinhDang, width*2, height);
                        }

                    }
                });
                Y_left += gap;
                int X_center = (frame.getWidth() - width) / 2;
                dangKyButton.setBounds(X_center, Y_left, width, height);
                Y_left += gap;
                askLabel.setBounds(X_center, Y_left , width/2, height);
                dangNhapButton.setBounds(X_center + askLabel.getWidth()*2/3, Y_left, width/2, height);

            }
        });

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
                if(hoTenField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(dobField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(gioiTinhField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(chucVuField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(taiKhoanField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(String.valueOf(matKhauField.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(String.valueOf(nhapMatKhauField.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else {
                    if(String.valueOf(matKhauField.getPassword()).equals(String.valueOf(nhapMatKhauField.getPassword()))) {
                        addSignInToSQL();
                        JOptionPane.showMessageDialog(mainPanel, "Đăng ký thành công");
                        new DangNhap();
                        frame.dispose();
                    }
                }



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
            preparedStatement2.setString(3, chucVuField.getText());
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