package dangnhap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import giaodien.*;
public class MatKhauMoi extends GiaoDien {
    private static JTextField userNameField;
    private static JTextField newPswField;
    private static JPasswordField repeatNewPswField;
    public void changPassword() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();
        JPanel panel = new JPanel();
        panel.setBounds(300, 100, 500, 550);
        panel.setLayout(null);

        JLabel changePswLabel = new JLabel("Thay đổi mật khẩu");
        JLabel userNameLabel = new JLabel("Tên tài khoản: ");
        userNameField = new JTextField(20);
        JLabel newPswLabel = new JLabel("Mật khẩu mới: ");
        newPswField = new JPasswordField(20);
        JLabel repeatNewPswLabel = new JLabel("Nhập lại mật khẩu mới: ");
        repeatNewPswField = new JPasswordField(20);
        JButton changeButton = new JButton("Thay đổi");

        //Set position
        changePswLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        userNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        newPswLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        repeatNewPswLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        changeButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        changePswLabel.setBounds(100, 50, 300, 50);
        userNameLabel.setBounds(100, 100, 200, 50);
        userNameField.setBounds(100, 150, 300, 50);
        newPswLabel.setBounds(100, 200, 300, 50);
        newPswField.setBounds(100, 250, 300, 50);
        repeatNewPswLabel.setBounds(100, 300, 300, 50);
        repeatNewPswField.setBounds(100, 350, 300, 50);
        changeButton.setBounds(100, 420, 300, 50);


        //Add to panel
        panel.setLayout(null);
        panel.add(changePswLabel);
        panel.add(userNameLabel);
        panel.add(userNameField);
        panel.add(newPswLabel);
        panel.add(newPswField);
        panel.add(repeatNewPswLabel);
        panel.add(repeatNewPswField);
        panel.add(changeButton);

        panelBoard.setLayout(null);
        panelBoard.add(panel);
        panelBoard.revalidate();
        panelBoard.repaint();

        //Password Comparison
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPassword = newPswField.getText();

                //Pose a message
                if(!newPswField.getText().equals(repeatNewPswField.getText())) {
                    JOptionPane.showMessageDialog(panelBoard, "Mật khẩu mới không giống nhau");
                } else if(newPswField.getText().equals("") || repeatNewPswField.getText().equals("")) {
                    JOptionPane.showMessageDialog(panelBoard, "Bạn chưa điền mật khẩu");
                } else {
                    //Update in sql here
                    updatePswSQL();
                    JOptionPane.showMessageDialog(panelBoard, "Thay đổi mật khẩu thành công");
                    new DangNhap().displaySignIn();
                }
            }
        });

    }
    public void updatePswSQL() {
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE dangnhap SET password = ? WHERE username = ?");
            preparedStatement.setString(1, newPswField.getText());
            preparedStatement.setString(2, userNameField.getText());
            preparedStatement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
