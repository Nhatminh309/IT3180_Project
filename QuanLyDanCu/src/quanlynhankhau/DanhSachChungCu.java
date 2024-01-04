package quanlynhankhau;

import connect.ConnectDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DanhSachChungCu extends ConnectDatabase {
    private static JPanel topPanel;
    private static JPanel mainPanel;
    private static JFrame frame;
    private static JTable table;
    private static DefaultTableModel model;
    private static JLabel searchLabel;
    private static JTextField searchField;
    private static JButton searchButton;
    public DanhSachChungCu() {
        frame = new JFrame("Danh sách nhân khẩu");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.getContentPane().setLayout(new BorderLayout());

        topPanel = new JPanel();
        //topPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Remove any border/margin
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton();
        ImageIcon icon = new ImageIcon("src/icon/goBackIcon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(frame.getWidth()/20, frame.getHeight()/19, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        backButton.setIcon(scaledIcon);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //back to giao dien ban dau
            }
        });
        topPanel.add(backButton, BorderLayout.WEST);

        JLabel chinhSuaNKLabel = new JLabel("Danh sách nhân khẩu chung cư");
        chinhSuaNKLabel.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/20));
        chinhSuaNKLabel.setHorizontalAlignment(SwingConstants.CENTER); // Align label to the center
        chinhSuaNKLabel.setForeground(Color.decode("#38B6FF"));
        topPanel.add(chinhSuaNKLabel, BorderLayout.CENTER);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        //Search bar
        searchLabel = new JLabel("Tìm kiếm nhân khẩu theo mã nhân khẩu...");
        searchField = new JTextField();
        searchButton = new JButton();
        ImageIcon icon2 = new ImageIcon("src/icon/searchIcon.png");
        Image img2 = icon2.getImage();
        Image scaledImg2 = img2.getScaledInstance(frame.getWidth()/35, frame.getHeight()/35, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImg2);
        searchButton.setIcon(scaledIcon2);

        Font newFont = new Font("Arial", Font.PLAIN, frame.getWidth()/50);
        searchLabel.setFont(newFont);

        mainPanel.add(searchLabel);
        mainPanel.add(searchField);
        mainPanel.add(searchButton);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth()*2/7;
                int height = frame.getHeight()/20;
                int gap = frame.getHeight()/20;
                int X_button = (frame.getWidth() - width*2)/2;
                int Y_button = frame.getHeight()/40;
                searchLabel.setBounds(X_button, Y_button, width*2, height);
                Y_button += gap;
                searchField.setBounds(X_button, Y_button, width*3/2, height);
                searchButton.setBounds(X_button + searchField.getWidth(), Y_button, width*2/7, height);
                Y_button += gap;

                model = new DefaultTableModel();
                table = new JTable(model);
                table.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/80));
                table.setBounds(0, Y_button, frame.getWidth(), frame.getHeight()- Y_button);
                model.addColumn("Mã nhân khẩu");
                model.addColumn("Họ và tên");
                model.addColumn("Ngày sinh");
                model.addColumn("Giới tính");
                model.addColumn("Nơi sinh");
                model.addColumn("Quê quán");
                model.addColumn("Dân tộc");
                model.addColumn("Tôn giáo");
                model.addColumn("Nghề nghiệp");
                model.addColumn("Nơi làm việc");
                model.addColumn("Ngày đăng ký");
                model.addColumn("Căn cước");
                model.addColumn("Số điện thoại");
                model.addColumn("Quan hệ");
                model.addColumn("Mã hộ khẩu");
                model.addColumn("Xác nhận");


            }
        });

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new DanhSachChungCu();
    }
}
