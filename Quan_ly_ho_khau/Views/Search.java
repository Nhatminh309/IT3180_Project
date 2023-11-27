package Views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Search {
    JFrame f;

    Search() {
        f = new JFrame("Tìm kiếm thông tin");

        String search[] = { "Tìm kiếm theo mã hộ khẩu", "Tìm kiếm theo mã nhân khẩu", "Tìm kiếm theo số CCCD",
                "Tìm kiếm lịch sử di chuyển đến", "Tìm kiếm lịch sử chuyển đi" };

        JComboBox<String> cb = new JComboBox<>(search);
        cb.setBounds(200, 50, 250, 20);
        f.add(cb);

        JLabel searchTypeLabel = new JLabel("Loại hình tìm kiếm: ");
        searchTypeLabel.setBounds(50, 50, 150, 20);
        f.add(searchTypeLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(200, 80, 150, 20);
        f.add(searchField);

        JLabel searchInputLabel = new JLabel("Mã số tương ứng: ");
        searchInputLabel.setBounds(50, 80, 150, 20);
        f.add(searchInputLabel);

        JButton submitButton = new JButton("Tìm kiếm");
        submitButton.setBounds(200, 110, 150, 25);
        f.add(submitButton);

        // Đặt sự kiện cho nút "Tìm kiếm"
        submitButton.addActionListener(e -> {
            // Xử lý thông tin tìm kiếm ở đây
            String selectedSearchType = (String) cb.getSelectedItem();
            String searchText = searchField.getText();
        });

        f.setLayout(null);
        f.setSize(500, 300);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new Search();
    }
}
