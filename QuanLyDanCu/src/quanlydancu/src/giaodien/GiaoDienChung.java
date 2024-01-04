package quanlydancu.src.giaodien;

import quanlydancu.src.connect.ConnectDatabase;

import javax.swing.*;
import java.awt.*;

public class GiaoDienChung extends ConnectDatabase {
    protected static JFrame frame;
    protected static JSplitPane splitPane;
    protected static JPanel leftPanel;
    protected static JPanel rightPanel;
    protected static JPanel horizontalBar;
    protected static JPanel mainPanel;
    protected static JPanel buttonPanel;
    protected static JPanel navigatePanel;

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
        splitPane.setResizeWeight((double) 1 / 6);
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

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());

        JButton btnHome = new JButton("HOME") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(leftPanel.getWidth(), leftPanel.getHeight() / 7);
            }

            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, leftPanel.getHeight() / 30);
            }
        };

        btnHome.setBackground(Color.decode("#004AAD"));
        btnHome.setForeground(Color.WHITE);
        btnHome.setFont(new Font("Arial", Font.PLAIN, 20));
        btnHome.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnHome.setFocusPainted(false);

        ImageIcon homeIcon = new ImageIcon("QuanLyDanCu/src/icon/homeIcon.png");
        int width = btnHome.getWidth();
        int height = btnHome.getHeight();
        Image scaledImage = homeIcon.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH);

        btnHome.setIcon(new ImageIcon(scaledImage));
        btnHome.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnHome.setVerticalTextPosition(SwingConstants.CENTER);
        btnHome.setIconTextGap(15);

        buttonPanel.add(btnHome, BorderLayout.NORTH);

        JButton btnAbout = new JButton("ABOUT") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(leftPanel.getWidth(), leftPanel.getHeight() / 7);
            }

            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, leftPanel.getHeight() / 30);
            }
        };

        btnAbout.setBackground(Color.decode("#004AAD"));
        btnAbout.setForeground(Color.WHITE);
        btnAbout.setFont(new Font("Arial", Font.PLAIN, 20));
        btnAbout.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnAbout.setFocusPainted(false);

        ImageIcon aboutIcon = new ImageIcon("QuanLyDanCu/src/icon/aboutIcon.png");
        scaledImage = aboutIcon.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH);

        btnAbout.setIcon(new ImageIcon(scaledImage));
        btnAbout.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnAbout.setVerticalTextPosition(SwingConstants.CENTER);
        btnAbout.setIconTextGap(15);

        navigatePanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(leftPanel.getWidth(), leftPanel.getHeight() / 2);
            }
        };
        navigatePanel.setLayout(new BoxLayout(navigatePanel, BoxLayout.Y_AXIS));
        navigatePanel.setBackground(Color.decode("#004AAD"));

        buttonPanel.add(navigatePanel, BorderLayout.CENTER);

        leftPanel.add(northLeftPanel, BorderLayout.NORTH);
        leftPanel.add(buttonPanel, BorderLayout.CENTER);
        leftPanel.add(btnAbout, BorderLayout.SOUTH);

        horizontalBar = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(rightPanel.getWidth(), rightPanel.getHeight() / 5);
            }
        };
        horizontalBar.setLayout(new BoxLayout(horizontalBar, BoxLayout.X_AXIS));
        horizontalBar.setBackground(Color.decode("#004AAD"));
        rightPanel.add(horizontalBar, BorderLayout.NORTH);

        frame.add(splitPane);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GiaoDienChung();
    }
}
