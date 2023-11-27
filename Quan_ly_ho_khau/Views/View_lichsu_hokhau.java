package Views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_lichsu_hokhau {
    JFrame f;

    View_lichsu_hokhau() {
        f = new JFrame("Xem lịch sử hộ khẩu");

        JLabel searchInputLabel = new JLabel("Mã số hộ khẩu: ");
        searchInputLabel.setBounds(50, 80, 150, 20);
        f.add(searchInputLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(200, 80, 250, 20);
        f.add(searchField);

        JButton submitButton = new JButton("Tìm kiếm");
        submitButton.setBounds(200, 110, 100, 25);
        f.add(submitButton);

        // Đặt sự kiện cho nút "Tìm kiếm"
        submitButton.addActionListener(e -> {
            // Xử lý thông tin tìm kiếm ở đây
           
        });

        f.setLayout(null);
        f.setSize(500, 300);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new View_lichsu_hokhau();
    }
}

