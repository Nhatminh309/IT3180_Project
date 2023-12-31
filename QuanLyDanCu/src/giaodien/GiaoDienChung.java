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


public class GiaoDienChung {
    private static JFrame frame;
    private static JPanel verticalBar;
    private static JPanel horizontalBar;

    public GiaoDienChung() {
        frame = new JFrame("Quản lý dân cư");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setMinimumSize(new Dimension(600, 400));
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

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustComponentsSize();
            }
        });


        mainPanel.add(verticalBar, BorderLayout.WEST);
        mainPanel.add(horizontalBar, BorderLayout.NORTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    private void adjustComponentsSize() {
        verticalBar.setPreferredSize(new Dimension(frame.getWidth() / 6, frame.getHeight()));
        horizontalBar.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 6));
        frame.revalidate();
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
        new GiaoDienChung();
    }
}