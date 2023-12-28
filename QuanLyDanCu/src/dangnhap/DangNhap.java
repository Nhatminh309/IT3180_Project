package dangnhap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import giaodien.*;
public class DangNhap extends GiaoDien  {

    private static JTextField userNameField;
    private static JTextField pswField;
    private static JButton signInButton;
    private static JButton forgotPswButton;
    public JTextField getUserNameField() {
        return userNameField;
    }
    public void displaySignIn() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();
        JPanel panel = new JPanel();
        panel.setBounds(300, 100, 500, 480);
        panel.setLayout(null);

        JLabel signInLabel = new JLabel("Đăng nhập");
        JLabel userNameLabel = new JLabel("Tên tài khoản: ");
        userNameField = new JTextField(20);
        JLabel pswLabel = new JLabel("Mật khẩu: ");
        pswField = new JPasswordField(20);
        signInButton = new JButton("Đăng nhập");
        forgotPswButton = new JButton("Quên mật khẩu ?");

        //Set position
        signInLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        userNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        pswLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        forgotPswButton.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
        signInButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));


        signInLabel.setBounds(100, 50, 300, 50);
        userNameLabel.setBounds(100, 100, 200, 50);
        userNameField.setBounds(100, 150, 300, 50);
        pswLabel.setBounds(100, 200, 200, 50);
        pswField.setBounds(100, 250, 300, 50);
        forgotPswButton.setBounds(65, 300, 200, 20);
        forgotPswButton.setBorderPainted(false);
        forgotPswButton.setFocusable(false);
        signInButton.setBounds(100, 350, 300, 50);


        ImageIcon icon = new ImageIcon("/Users/macbookair/2023.1/nhapmoncnpm/IT3180_Project/QuanLyDanCu/src/icon/avatar.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        signInLabel.setIcon(scaledIcon);
        signInLabel.setIconTextGap(20);

        //Action Listener for signInButton
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                String password = pswField.getText();
                //Compare with data in sql here
                Connection connection = connectSQL(userName, password);
                if(validateSignIn(userName, password) == null) {
                    System.out.println("Đăng nhập không thành công");
                    JOptionPane.showMessageDialog(panelBoard, "Tên tài khoản hoặc mật khẩu không chính xác");
                } else if(validateSignIn(userName, password).equals("admin")) {
                    System.out.println("Đăng nhập thành công với vai trò admin");
                    JOptionPane.showMessageDialog(panelBoard, "Đăng nhập thành công");
                    JFrame frame = getFrame();
                    frame.dispose();
                    new GiaoDienQuanLy();
                } else if(validateSignIn(userName, password).equals("user")) {
                    System.out.println("Đăng nhập thành công với vai trò user");
                    JOptionPane.showMessageDialog(panelBoard, "Đăng nhập thành công");

                }

            }
        });
        //Action Listener for forgotPsw
        forgotPswButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().dispose();
                new MatKhauMoi().changPassword();
            }
        });

        //Add to panel
        panel.setLayout(null);
        panel.add(signInLabel);
        panel.add(userNameLabel);
        panel.add(userNameField);
        panel.add(pswLabel);
        panel.add(pswField);
        panel.add(signInButton);
        panel.add(forgotPswButton);
        //panel.setBackground(Color.lightGray);

        panelBoard.setLayout(null);
        panelBoard.add(panel);

        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel
    }
    public Connection connectSQL(String username, String password) {
        //String URL = "jdbc:postgresql://localhost:5432/QuanLyDanCu";
        Connection connection = null;
        try {
            connection = getConnectDatabase();
            System.out.println("Connect successfully");
        } catch (SQLException e) {
            System.out.println("Connection failed");;
            e.printStackTrace();
        }
        return connection;
    }
    public String validateSignIn(String username, String password) {
        String URL = "jdbc:postgresql://localhost:5432/QuanLyDanCu";
        String querry = "SELECT * FROM DangNhap WHERE username = ? AND password = ?";
        String role = null;
        try(Connection connection = DriverManager.getConnection(URL, "postgres", "271203")) {
            PreparedStatement pS = connection.prepareStatement(querry);
            pS.setString(1, username);
            pS.setString(2, password);
            try(ResultSet resultSet = pS.executeQuery()) {
                if(resultSet.next()) {
                    role = resultSet.getString("vaitro");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
