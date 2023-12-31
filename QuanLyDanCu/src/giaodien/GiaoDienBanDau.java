package QuanLyDanCu.src.giaodien;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class GiaoDienBanDau extends GiaoDien {
    private static JFrame frame;
    private static JButton dangNhap;
    private static JButton dangKy;
    private static JButton btnHome;
    private static JButton btnAbout;
    private static JLabel toolBarLabel = new JLabel("CỔNG THÔNG TIN THỦ TỤC HÀNH CHÍNH");
    private static JPanel verticalBar;
    private static JPanel horizontalBar;
    public GiaoDienBanDau() {
        frame = new JFrame("Quản lý dân cư");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true)    ;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        verticalBar = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(frame.getWidth() / 6, frame.getHeight());
            }
        };
        verticalBar.setBackground(Color.decode("#004AAD"));
        verticalBar.setPreferredSize(new Dimension(frame.getWidth() / 5, frame.getHeight())); // Chiều rộng 15%, chiều cao full

        horizontalBar = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(frame.getWidth(), frame.getHeight() / 6);
            }
        };
        horizontalBar.setBackground(Color.decode("#004AAD"));
        horizontalBar.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 6)); // Chiều rộng full, chiều cao 15%


        dangNhap = new JButton("Đăng nhập");
        dangNhap.setBackground(Color.decode("#38B6FF"));
        dangNhap.setForeground(Color.WHITE);
        dangNhap.setFont(new Font("Arial", Font.PLAIN, 20));
        dangNhap.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Thiết lập border
        dangNhap.setFocusPainted(false); // Loại bỏ viền focus
        
        dangKy = new JButton("Đăng ký");
        dangKy.setBackground(Color.decode("#38B6FF"));
        dangKy.setForeground(Color.WHITE);
        dangKy.setFont(new Font("Arial", Font.PLAIN, 20));
        dangKy.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Thiết lập border
        dangKy.setFocusPainted(false); // Loại bỏ viền focus


        horizontalBar.add(Box.createHorizontalGlue()); // Thêm glue đưa các thành phần về bên phải
        horizontalBar.add(dangKy);
        horizontalBar.add(Box.createRigidArea(new Dimension(10, 0))); // Để tạo khoảng cách giữa nút
        horizontalBar.add(dangNhap);

        btnHome = new JButton("Home");
        btnAbout = new JButton("About");
        verticalBar.setLayout(new BoxLayout(verticalBar, BoxLayout.Y_AXIS));
        verticalBar.add(btnHome);
        verticalBar.add(btnAbout);

        mainPanel.add(verticalBar, BorderLayout.WEST);
        mainPanel.add(horizontalBar, BorderLayout.NORTH);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                verticalBar.setPreferredSize(new Dimension(frame.getWidth() / 6, frame.getHeight()));
                horizontalBar.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 6));
                frame.revalidate();
            }
        });
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public String getStringURL() {
        String URL = "jdbc:postgresql://localhost:5432/postgres";
        return URL;
    }
    public Connection getConnectDatabase() throws SQLException {
        String URL = getStringURL();
        Connection connection = DriverManager.getConnection(URL, "postgres", "anhbopcolen");
        return connection;
    }


    public static void main(String[] args) {
        new GiaoDienBanDau();
    }
}