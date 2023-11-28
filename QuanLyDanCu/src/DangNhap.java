import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DangNhap extends GiaoDien {

    public void displaySignIn() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();
        JPanel panel = new JPanel();
        panel.setBounds(350, 100, 600, 600);
        panel.setLayout(null);

        JLabel signInLabel = new JLabel("Đăng nhập");
        JLabel userNameLabel = new JLabel("Tên tài khoản: ");
        JTextField userNameField = new JTextField(20);
        JLabel pswLabel = new JLabel("Mật khẩu: ");
        JTextField pswField = new JPasswordField(20);
        JButton signInButton = new JButton("Đăng nhập");
        JButton forgotPswButton = new JButton("Quên mật khẩu ?");

        //Set position
        signInLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        userNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        pswLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        forgotPswButton.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
        signInButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));


        signInLabel.setBounds(150, 100, 300, 50);
        userNameLabel.setBounds(150, 150, 200, 50);
        userNameField.setBounds(150, 200, 300, 50);
        pswLabel.setBounds(150, 250, 200, 50);
        pswField.setBounds(150, 300, 300, 50);
        forgotPswButton.setBounds(65, 350, 300, 20);
        forgotPswButton.setBorderPainted(false);
        forgotPswButton.setFocusable(false);
        signInButton.setBounds(150, 400, 300, 50);


        ImageIcon icon = new ImageIcon("/Users/macbookair/2023.1/IT3180_Project/nhapmoncnpm/QuanLyDanCu/src/avatar.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(60, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        signInLabel.setIcon(scaledIcon);
        signInButton.setIconTextGap(50);

        //Action Listener for signInButton
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                String password = pswField.getText();
                //Compare with data in sql here

            }
        });
        //Action Listener for forgotPsw
        forgotPswButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        panel.setBackground(Color.lightGray);

        panelBoard.setLayout(null);
        panelBoard.add(panel);

        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel
    }
}
