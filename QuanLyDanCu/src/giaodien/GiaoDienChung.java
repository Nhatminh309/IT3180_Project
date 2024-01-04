package giaodien;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
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
    public GiaoDienChung() {
        frame = new JFrame("Quản lý dân cư");
        frame.setSize(1000, 578);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setUndecorated(true);

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

        btnAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.remove(splitPane);
                frame.setOpacity(0.7f);
                JDialog dialog = new JDialog(frame, "About", Dialog.ModalityType.APPLICATION_MODAL);
                dialog.setLayout(new BorderLayout());
                dialog.setSize(new Dimension(frame.getWidth() *2 / 5, frame.getHeight()));
                dialog.setLocationRelativeTo(frame);

                JPanel topPanel = new JPanel();
                topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
                topPanel.setBackground(Color.decode("#004AAD"));
                topPanel.setOpaque(true);
                topPanel.setPreferredSize(new Dimension(dialog.getWidth(), dialog.getHeight()/8));
                JLabel topLabel = new JLabel("Thông tin");
                topLabel.setForeground(Color.WHITE);
                topLabel.setFont(new Font("Arial", Font.PLAIN, 30));
                topLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                topLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

                topPanel.add(Box.createVerticalGlue());
                topPanel.add(topLabel);
                topPanel.add(Box.createVerticalGlue());
                dialog.add(topPanel, BorderLayout.NORTH);

                JPanel mainPanel = new JPanel();
                JLabel aboutLabel = new JLabel("<html><div style='text-align:justify;'>Phần mềm quản lý dân cư của Tổ dân phố 7 và Chung cư Bluemoon với các " +
                        "chức năng: Quản lý hộ khẩu, nhân khẩu; Quản lý thu phí; Quản lý phát thưởng</div></html>");


                // Ensure the text wraps within the width of the dialog
                aboutLabel.setPreferredSize(new Dimension(dialog.getWidth(), 100));
                aboutLabel.setVerticalAlignment(JLabel.TOP); // Aligns text to the top

                mainPanel.add(aboutLabel);

                dialog.add(mainPanel);

                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frame.setOpacity(1.0f);
                        //frame.setUndecorated(false);
                    }
                });

                // Display the dialog
                dialog.setVisible(true);
            }
        });

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