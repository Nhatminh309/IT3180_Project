package QuanLyDanCu.src.giaodien;

import QuanLyDanCu.src.connect.ConnectDatabase;
import QuanLyDanCu.src.graphics.RoundBorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;



public class GiaoDienChung extends ConnectDatabase {
    protected static JFrame frame;
    protected static JSplitPane splitPane;
    protected static JPanel leftPanel;
    protected static JPanel rightPanel;
    protected static JPanel horizontalBar;
    protected static JPanel mainPanel;
    protected static JPanel buttonPanel;
    protected static JPanel navigatePanel;
    protected static JPanel centerPanel;
    protected static JPanel mainButtonPanel;
    protected static JPanel northPanel;
    protected static JPanel southPanel;
    protected static JButton btnHome;
    protected static JButton btnAbout;
    protected static JButton quitButton;
    public GiaoDienChung() {
        frame = new JFrame("Quản lý dân cư");
        frame.setSize(1270, 714);
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
        leftPanel.setBackground(Color.decode("#5271FF"));

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
        northLeftPanel.setBackground(Color.decode("#5271FF"));



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

        btnHome = new JButton("Trang chủ") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(leftPanel.getWidth(), leftPanel.getHeight() / 7);
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, leftPanel.getHeight() / 30   );
            }
        };

        btnHome.setBackground(Color.decode("#5271FF"));
        btnHome.setForeground(Color.WHITE);
        btnHome.setFont(new Font("Arial", Font.PLAIN, 20));
        btnHome.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Thiết lập border
        btnHome.setFocusPainted(false); // Loại bỏ viền focus
        ImageIcon homeIcon = new ImageIcon("QuanLyDanCu/src/icon/homeIcon.png");
        int width = btnHome.getWidth();
        int height = btnHome.getHeight();
        Image scaledImage = homeIcon.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH);

        btnHome.setIcon(new ImageIcon(scaledImage));
        btnHome.setHorizontalTextPosition(SwingConstants.RIGHT); // Đặt vị trí của văn bản bên phải của icon
        btnHome.setVerticalTextPosition(SwingConstants.CENTER);
        btnHome.setIconTextGap(15); // Đặt khoảng cách giữa icon và văn bản

        buttonPanel.add(btnHome, BorderLayout.NORTH);
        btnAbout = new JButton("Thông tin") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(leftPanel.getWidth(), leftPanel.getHeight() / 7);
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, leftPanel.getHeight() / 30);
            }
        };
        btnAbout.setBackground(Color.decode("#5271FF"));
        btnAbout.setForeground(Color.WHITE);
        btnAbout.setFont(new Font("Arial", Font.PLAIN, 20));
        btnAbout.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Thiết lập border
        btnAbout.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon aboutIcon = new ImageIcon("QuanLyDanCu/src/icon/aboutIcon.png");
        scaledImage = aboutIcon.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH);

        btnAbout.setIcon(new ImageIcon(scaledImage));
        btnAbout.setHorizontalTextPosition(SwingConstants.RIGHT); // Đặt vị trí của văn bản bên phải của icon
        btnAbout.setVerticalTextPosition(SwingConstants.CENTER);
        btnAbout.setIconTextGap(15); // Đặt khoảng cách giữa icon và văn bản

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
                    }
                });

                // Display the dialog
                dialog.setVisible(true);
            }
        });
        navigatePanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(leftPanel.getWidth(), leftPanel.getHeight() / 2);
            }

        };
        navigatePanel.setLayout(new BoxLayout(navigatePanel, BoxLayout.Y_AXIS));
        navigatePanel.setBackground(Color.decode("#5271FF"));


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
        horizontalBar.setBackground(Color.WHITE);
        horizontalBar.setForeground(Color.decode("#004AAD"));
        rightPanel.add(horizontalBar, BorderLayout.NORTH);

        mainPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(rightPanel.getWidth(), rightPanel.getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.decode("#EDEDED"));
        rightPanel.add(mainPanel, BorderLayout.CENTER);

        southPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(horizontalBar.getWidth() / 2, horizontalBar.getHeight() / 2);
            }
        };
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        southPanel.setBackground(Color.WHITE);
        southPanel.add(Box.createHorizontalGlue()); // Thêm glue đưa các thành phần về bên phải
        JButton fullScreenButton = new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(southPanel.getWidth() / 10, southPanel.getHeight() / 15);
            }
        };
        fullScreenButton.setBackground(Color.WHITE);
        fullScreenButton.setForeground(Color.decode("#004AAD"));
        fullScreenButton.setBorder(new RoundBorder(fullScreenButton.getHeight() / 2));
        fullScreenButton.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon fullScreenIcon = new ImageIcon("QuanLyDanCu/src/icon/fullScreenIcon.png");
        Image fullScreenImg = fullScreenIcon.getImage();
        int fullScreenButtonWidth = fullScreenButton.getWidth() / 2;
        int fullScreenButtonHeight = fullScreenButton.getHeight() / 2;
        Image fullScreenScaledImg = fullScreenImg.getScaledInstance(fullScreenButtonWidth - 1, fullScreenButtonHeight - 1, Image.SCALE_SMOOTH);
        fullScreenButton.setIcon(new ImageIcon(fullScreenScaledImg));
        fullScreenButton.setHorizontalTextPosition(SwingConstants.RIGHT);// Đặt vị trí của văn bản bên phải của icon
        fullScreenButton.setVerticalTextPosition(SwingConstants.CENTER);
        fullScreenButton.setIconTextGap(1); // Đặt khoảng cách giữa icon và văn bản

        fullScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                    frame.setExtendedState(JFrame.NORMAL);
                } else {
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            }
        });

        southPanel.add(fullScreenButton);
        quitButton = new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(southPanel.getWidth() / 10, southPanel.getHeight() / 15);
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, southPanel.getHeight() / 3);
            }
        };
        quitButton.setBackground(Color.WHITE);
        quitButton.setForeground(Color.decode("#004AAD"));
        quitButton.setBorder(new RoundBorder(quitButton.getHeight() / 2));
        quitButton.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon icon = new ImageIcon("QuanLyDanCu/src/icon/exitIcon.png");
        Image img = icon.getImage();
        int quitButtonWidth = quitButton.getWidth() / 2;
        int quitButtonHeight = quitButton.getHeight() / 2;
        Image scaledImg = img.getScaledInstance(quitButtonWidth - 1, quitButtonHeight - 1, Image.SCALE_SMOOTH);
        quitButton.setIcon(new ImageIcon(scaledImg));
        quitButton.setHorizontalTextPosition(SwingConstants.RIGHT);// Đặt vị trí của văn bản bên phải của icon
        quitButton.setVerticalTextPosition(SwingConstants.CENTER);
        quitButton.setIconTextGap(1); // Đặt khoảng cách giữa icon và văn bản

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Bạn có chắc chắn muốn thoát?", "Thoát", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    frame.dispose();
                }
            }
        });
        southPanel.add(quitButton);

        rightPanel.add(southPanel, BorderLayout.SOUTH);

        frame.add(splitPane);

//        layeredPane.add(splitPane, JLayeredPane.DEFAULT_LAYER);
//        frame.add(layeredPane);

        frame.setVisible(true);
    }

    protected static void addLogin(String taiKhoan) {
        JPanel helloUsernamePanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(horizontalBar.getWidth() / 3, horizontalBar.getHeight());
            }
        };
        helloUsernamePanel.setLayout(new BoxLayout(helloUsernamePanel, BoxLayout.X_AXIS));
        helloUsernamePanel.setBackground(Color.WHITE);
        helloUsernamePanel.setForeground(Color.decode("#004AAD"));
        JLabel helloUsernameLabel = new JLabel(taiKhoan) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(helloUsernamePanel.getWidth(), helloUsernamePanel.getHeight());
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, helloUsernamePanel.getHeight() / 4);
            }
        };
        helloUsernameLabel.setForeground(Color.decode("#004AAD"));
        helloUsernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        helloUsernamePanel.add(helloUsernameLabel, BorderLayout.CENTER);

        helloUsernamePanel.add(Box.createHorizontalGlue()); // Thêm glue đưa các thành phần về bên phải
        helloUsernamePanel.add(helloUsernameLabel);
        helloUsernamePanel.add(Box.createRigidArea(new Dimension(20, 0))); // Để tạo khoảng cách giữa nút

        JButton avatarButton = new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(horizontalBar.getWidth() / 10, horizontalBar.getHeight());
            }
        };
        avatarButton.setBackground(Color.WHITE);
        avatarButton.setForeground(Color.decode("#004AAD"));
        ImageIcon icon = new ImageIcon("QuanLyDanCu/src/icon/avatarIcon.png");
        Image img = icon.getImage();
        int avatarButtonWidth = avatarButton.getWidth();
        int avatarButtonHeight = avatarButton.getHeight();
        Image scaledImg = img.getScaledInstance(avatarButtonWidth - 1, avatarButtonHeight - 1, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        avatarButton.setIcon(scaledIcon);
        avatarButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        avatarButton.setFocusPainted(false); // Loại bỏ viền focus

        helloUsernamePanel.add(avatarButton);
        horizontalBar.add(helloUsernamePanel);
        horizontalBar.add(Box.createRigidArea(new Dimension(20, 0))); // Để tạo khoảng cách giữa nút
    }
    public static void main(String[] args) {
        new GiaoDienChung();
    }
}
