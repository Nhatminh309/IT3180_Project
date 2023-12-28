package dangnhap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import giaodien.*;
public class DangKy extends GiaoDien {
    private static JTextField userNameField;
    private static JPasswordField pswField;
    public void displaySignUp() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();
        JPanel panel = new JPanel();
        panel.setBounds(300, 100, 500, 550);
        panel.setLayout(null);

        JLabel signUpLabel = new JLabel("Đăng ký tài khoản");
        JLabel teleNumLabel = new JLabel("Số điện thoại: ");
        JTextField teleNumField = new JTextField(20);
        JLabel userNameLabel = new JLabel("Tên tài khoản: ");
        userNameField = new JTextField(20);
        JLabel pswLabel = new JLabel("Password: ");
        pswField = new JPasswordField(20);
        JButton signUpButton = new JButton("Đăng ký");

        //Set position
        signUpLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        teleNumLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        userNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        pswLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        signUpButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        signUpLabel.setBounds(100, 50, 300, 50);
        teleNumLabel.setBounds(100, 100, 200, 50);
        teleNumField.setBounds(100, 150, 300, 50);
        userNameLabel.setBounds(100, 200, 200, 50);
        userNameField.setBounds(100, 250, 300, 50);
        pswLabel.setBounds(100, 300, 200, 50);
        pswField.setBounds(100, 350, 300, 50);
        signUpButton.setBounds(100, 440, 300, 50);

        //Action Listener
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Compare with SQL here
                if(teleNumField.getText().equals("") || userNameField.getText().equals("") || pswField.getPassword().equals("")) {
                    JOptionPane.showMessageDialog(panelBoard, "Bạn chưa nhập thông tin");
                } else {
                    addSignInToSQL();
                    JOptionPane.showMessageDialog(panelBoard, "Đăng ký tài khoản thành công");
                    new DangNhap().displaySignIn();
                }

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
        //panel.setBackground(Color.LIGHT_GRAY);

        panelBoard.setLayout(null);
        panelBoard.add(panel);

        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel
    }
    public void addSignInToSQL() {
        try {
            Connection connection = getConnectDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO dangnhap VALUES(?, ?, ?)");
            preparedStatement.setString(1, userNameField.getText());
            preparedStatement.setString(2, String.valueOf(pswField.getPassword()));
            preparedStatement.setString(3, "admin");
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
