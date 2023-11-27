package Views;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_tam_vang {

    public static void main(String[] args) {
        // Creating instance of JFrame
        JFrame frame = new JFrame("Đăng ký tạm vắng");
        // Setting the width and height of frame
        frame.setSize(500, 300);
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

        JLabel ma_nhan_khau = new JLabel("Mã nhân khẩu");
        ma_nhan_khau.setBounds(10, 20, 200, 25);
        panel.add(ma_nhan_khau);

        JTextField ma_nhan_khau_Text = new JTextField(20);
        ma_nhan_khau_Text.setBounds(140, 20, 200, 25);
        panel.add(ma_nhan_khau_Text);

        JLabel date = new JLabel("Ngày tạm vắng");
        date.setBounds(10, 50, 200, 25);
        panel.add(date);

        JTextField date_Text = new JTextField(20);
        date_Text.setBounds(140, 50, 200, 25);
        panel.add(date_Text);

        JLabel noi_den = new JLabel("Nơi đến");
        noi_den.setBounds(10, 80, 200, 25);
        panel.add(noi_den);

        JTextField noi_den_Text = new JTextField(20);
        noi_den_Text.setBounds(140, 80, 200, 25);
        panel.add(noi_den_Text);

        JButton acceptButton = new JButton("Gửi đăng ký");
        acceptButton.setBounds(10, 110, 120, 25);
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
