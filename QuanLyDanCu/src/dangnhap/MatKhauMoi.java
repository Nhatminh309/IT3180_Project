package dangnhap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import giaodien.*;
public class MatKhauMoi extends GiaoDien {
    public void changPassword() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();
        JPanel panel = new JPanel();
        panel.setBounds(300, 100, 500, 450);
        panel.setLayout(null);

        JLabel changePswLabel = new JLabel("Thay đổi mật khẩu");
        JLabel newPswLabel = new JLabel("Mật khẩu mới: ");
        JTextField newPswField = new JPasswordField(20);
        JLabel repeatNewPswLabel = new JLabel("Nhập lại mật khẩu mới: ");
        JTextField repeatNewPswField = new JPasswordField(20);
        JButton changeButton = new JButton("Thay đổi");

        //Set position
        changePswLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        newPswLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        repeatNewPswLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        changeButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        changePswLabel.setBounds(100, 50, 300, 50);
        newPswLabel.setBounds(100, 100, 200, 50);
        newPswField.setBounds(100, 150, 300, 50);
        repeatNewPswLabel.setBounds(100, 200, 300, 50);
        repeatNewPswField.setBounds(100, 250, 300, 50);
        changeButton.setBounds(100, 320, 300, 50);


        //Add to panel
        panel.setLayout(null);
        panel.add(changePswLabel);
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
                    //Update in sqp here

                    JOptionPane.showMessageDialog(panelBoard, "Thay đổi mật khẩu thành công");
                    new DangNhap().displaySignIn();
                }
            }
        });


    }
}
