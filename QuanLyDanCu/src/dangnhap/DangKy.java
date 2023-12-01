package dangnhap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import giaodien.*;
public class DangKy extends GiaoDien {
    public void displaySignUp() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();
        JPanel panel = new JPanel();
        panel.setBounds(350, 100, 600, 600);
        panel.setLayout(null);

        JLabel signUpLabel = new JLabel("Đăng ký tài khoản");
        JLabel teleNumLabel = new JLabel("Số điện thoại: ");
        JTextField teleNumField = new JTextField(20);
        JLabel userNameLabel = new JLabel("Tên tài khoản: ");
        JTextField userNameField = new JTextField(20);
        JLabel pswLabel = new JLabel("Password: ");
        JPasswordField pswField = new JPasswordField(20);
        JButton signUpButton = new JButton("Đăng nhập");

        //Set position
        signUpLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        teleNumLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        userNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        pswLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        signUpButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        signUpLabel.setBounds(150, 50, 300, 50);
        teleNumLabel.setBounds(150, 100, 200, 50);
        teleNumField.setBounds(150, 150, 300, 50);
        userNameLabel.setBounds(150, 200, 200, 50);
        userNameField.setBounds(150, 250, 300, 50);
        pswLabel.setBounds(150, 300, 200, 50);
        pswField.setBounds(150, 350, 300, 50);
        signUpButton.setBounds(150, 420, 300, 50);

        //Action Listener
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Compare with SQL here

                JOptionPane.showMessageDialog(panelBoard, "Đăng ký tài khoản thành công");
                new DangNhap().displaySignIn();
            }
        });

        //Add to panel
        panel.setLayout(null);
        panel.add(signUpLabel);
        panel.add(teleNumLabel);
        panel.add(teleNumField);
        panel.add(userNameLabel);
        panel.add(userNameField);
        panel.add(pswLabel);
        panel.add(pswField);
        panel.add(signUpButton);
        panel.setBackground(Color.LIGHT_GRAY);

        panelBoard.setLayout(null);
        panelBoard.add(panel);

        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel
    }
}
