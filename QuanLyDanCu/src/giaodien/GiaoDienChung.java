package giaodien;
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
    protected static JFrame frame;
    protected static JSplitPane splitPane;
    protected static JPanel leftPanel;
    protected static JPanel rightPanel;
    protected static JPanel horizontalBar;
    protected static JPanel homePanel;
    protected static JPanel aboutPanel;
    public GiaoDienChung() {
        frame = new JFrame("Quản lý dân cư");
        frame.setSize(1000, 578);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setMinimumSize(new Dimension(600, 400));
        leftPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(frame.getWidth() / 6, frame.getHeight());
            }
        };
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.decode("#004AAD"));

        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setResizeWeight((double) 1 /6);
        splitPane.setDividerLocation(frame.getWidth() / 6);
        splitPane.setDividerSize(0);

        JPanel northLeftPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(leftPanel.getWidth(), leftPanel.getHeight() / 6);
            }

        };
        northLeftPanel.setBackground(Color.decode("#004AAD"));


        JLabel groupLabel = new JLabel("SE_03") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(northLeftPanel.getWidth(), northLeftPanel.getHeight());
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.BOLD, northLeftPanel.getHeight() / 4);
            }
        };
        groupLabel.setForeground(Color.WHITE);
        groupLabel.setHorizontalAlignment(JLabel.CENTER);


        northLeftPanel.add(groupLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());

        homePanel = new JPanel();
        JButton btnHome = new JButton("HOME") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(leftPanel.getWidth(), leftPanel.getHeight() / 7);
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, leftPanel.getHeight() / 32  );
            }
        };
        btnHome.setBackground(Color.decode("#004AAD"));
        btnHome.setForeground(Color.WHITE);
        btnHome.setFont(new Font("Arial", Font.PLAIN, 20));
        btnHome.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Thiết lập border
        btnHome.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon homeIcon = new ImageIcon("QuanLyDanCu/src/icon/homeIcon.png");
        JLabel homeLabel = new JLabel(homeIcon) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(leftPanel.getWidth() / 6, leftPanel.getHeight() / 7);
            }
        };

        homeLabel.setHorizontalAlignment(JLabel.CENTER);
        homePanel.add(homeLabel);
        homePanel.add(btnHome);
        JButton btnAbout = new JButton("ABOUT") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(leftPanel.getWidth(), leftPanel.getHeight() / 7);
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, leftPanel.getHeight() / 32);
            }
        };
        btnAbout.setBackground(Color.decode("#004AAD"));
        btnAbout.setForeground(Color.WHITE);
        btnAbout.setFont(new Font("Arial", Font.PLAIN, 20));
        btnAbout.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Thiết lập border
        btnAbout.setFocusPainted(false); // Loại bỏ viền focus

//        buttonPanel.add(Box.createVerticalGlue());


        JPanel navigatePanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(leftPanel.getWidth(), leftPanel.getHeight() / 2);
            }

        };
        navigatePanel.setLayout(new BoxLayout(navigatePanel, BoxLayout.Y_AXIS));
        navigatePanel.setBackground(Color.decode("#004AAD"));



        buttonPanel.add(btnHome, BorderLayout.NORTH);
        buttonPanel.add(navigatePanel, BorderLayout.CENTER);

        leftPanel.add(northLeftPanel, BorderLayout.NORTH);
        leftPanel.add(buttonPanel, BorderLayout.CENTER);
        leftPanel.add(btnAbout, BorderLayout.SOUTH);

        horizontalBar = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(rightPanel.getWidth(), rightPanel.getHeight() / 6);
            }
        };
        horizontalBar.setLayout(new BoxLayout(horizontalBar, BoxLayout.X_AXIS));
        horizontalBar.setBackground(Color.decode("#004AAD"));
        rightPanel.add(horizontalBar, BorderLayout.NORTH);

        frame.add(splitPane);
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
        new GiaoDienChung();
    }
}