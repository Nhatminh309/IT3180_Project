package Views;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_tam_tru {

    public static void main(String[] args) {
        // Creating instance of JFrame
        JFrame frame = new JFrame("Đăng ký tạm trú");
        // Setting the width and height of frame
        frame.setSize(500, 300); // Đã thay đổi kích thước thành 500x300
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating panel
        JPanel panel = new JPanel();
        // adding panel to frame
        frame.add(panel);

        // calling user defined method for adding components to the panel
        placeComponents(panel);

        // Setting the frame visibility to true
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel dia_chi = new JLabel("Địa chỉ thường trú");
        dia_chi.setBounds(10, 20, 200, 25); // Đã thay đổi kích thước và vị trí
        panel.add(dia_chi);

        JTextField dia_chi_Text = new JTextField(20);
        dia_chi_Text.setBounds(140, 20, 200, 25); // Đã thay đổi kích thước và vị trí
        panel.add(dia_chi_Text);

        JLabel ma_nhan_khau = new JLabel("Mã nhân khẩu");
        ma_nhan_khau.setBounds(10, 50, 200, 25); // Đã thay đổi kích thước và vị trí
        panel.add(ma_nhan_khau);

        JPasswordField ma_nhan_khau_Text = new JPasswordField(20);
        ma_nhan_khau_Text.setBounds(140, 50, 200, 25); // Đã thay đổi kích thước và vị trí
        panel.add(ma_nhan_khau_Text);

        JButton acceptButton = new JButton("Gui dang ky");
        acceptButton.setBounds(10, 80, 120, 25); // Đã thay đổi kích thước và vị trí
        panel.add(acceptButton);

        // Xử lý sự kiện khi người dùng nhấn nút "Gửi đăng ký"
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiển thị thông báo "Gửi đăng ký thành công"
                JOptionPane.showMessageDialog(panel, "Gửi đăng ký thành công");
            }
        });

    }
}
