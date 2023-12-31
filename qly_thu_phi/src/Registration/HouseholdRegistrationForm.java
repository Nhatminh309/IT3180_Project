package Registration;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HouseholdRegistrationForm extends JFrame implements ActionListener {
    // Các thành phần của form
    private JLabel nameLabel, nicknameLabel, dobLabel, genderLabel, birthPlaceLabel, originLabel,
            ethnicityLabel, religionLabel, occupationLabel, workplaceLabel, registrationDateLabel, relationshipLabel;

    private JTextField nameField, nicknameField, dobField, birthPlaceField, originField,
            occupationField, workplaceField, registrationDateField;

    private JComboBox<String> genderComboBox, ethnicityComboBox, religionComboBox, relationshipComboBox;

    private JButton submitButton;

    public HouseholdRegistrationForm() {
        // Tạo JFrame và cài đặt các thuộc tính cơ bản
        setTitle("Đăng ký hộ khẩu mới");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(12, 2));

        // Khởi tạo các thành phần
        nameLabel = new JLabel("Họ và tên:");
        nameField = new JTextField();

        // Tương tự, khởi tạo các thành phần còn lại với JLabel, JTextField, JComboBox và JButton tương ứng

        // Thêm các thành phần vào JFrame
        add(nameLabel);
        add(nameField);

        // Tương tự, thêm các thành phần còn lại vào JFrame

        // Tạo và thêm nút "Submit"
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this); // Đăng ký sự kiện ActionListener
        add(submitButton);

        // Hiển thị JFrame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Xử lý thông tin khi người dùng nhấn nút "Submit"
            String name = nameField.getText();
            // Lấy thông tin từ các trường khác và xử lý dữ liệu ở đây
            // Ví dụ: In thông tin đăng ký sau khi nhấn Submit
            System.out.println("Thông tin đăng ký hộ khẩu:");
            System.out.println("Họ và tên: " + name);
            // In thông tin của các trường khác tương tự
        }
    }

    public static void main(String[] args) {
        // Khởi chạy form đăng ký hộ khẩu
        new HouseholdRegistrationForm();
    }
}
