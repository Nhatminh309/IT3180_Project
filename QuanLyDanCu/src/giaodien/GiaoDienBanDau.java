package QuanLyDanCu.src.giaodien;

import javax.swing.*;
import java.awt.* ;


public class GiaoDienBanDau extends GiaoDienChung {

    public GiaoDienBanDau() {
        super();
        JButton dangNhap = new JButton("Đăng nhập") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(horizontalBar.getWidth() / 6, horizontalBar.getHeight() / 2);
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, horizontalBar.getHeight() / 5);
            }
        };
        dangNhap.setBackground(Color.decode("#38B6FF"));
        dangNhap.setForeground(Color.WHITE);
        dangNhap.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Thiết lập border
        dangNhap.setFocusPainted(false); // Loại bỏ viền focus

        JButton dangKy = new JButton("Đăng ký") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(horizontalBar.getWidth() / 6, horizontalBar.getHeight() / 2);
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, horizontalBar.getHeight() / 5);
            }
        };

        dangKy.setBackground(Color.decode("#38B6FF"));
        dangKy.setForeground(Color.WHITE);
        dangKy.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Thiết lập border
        dangKy.setFocusPainted(false); // Loại bỏ viền focus

        horizontalBar.add(Box.createHorizontalGlue()); // Thêm glue đưa các thành phần về bên phải
        horizontalBar.add(dangKy);
        horizontalBar.add(Box.createRigidArea(new Dimension(20, 0))); // Để tạo khoảng cách giữa nút
        horizontalBar.add(dangNhap);
        horizontalBar.add(Box.createRigidArea(new Dimension(40, 0))); // Để tạo khoảng cách giữa nút

        mainPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(rightPanel.getWidth(), rightPanel.getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel northPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(mainPanel.getWidth(), mainPanel.getHeight() / 4);
            }
        };
        northPanel.setLayout(new BorderLayout());
        northPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("HỆ THỐNG QUẢN LÝ DÂN CƯ") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(mainPanel.getWidth(), mainPanel.getHeight() / 5);
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, mainPanel.getHeight() / 15);
            }
        };
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setForeground(Color.decode("#0097B2"));

        northPanel.add(title, BorderLayout.CENTER);
        mainPanel.add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(mainPanel.getWidth(), mainPanel.getHeight() / 2);
            }
        };
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(centerPanel.getWidth(), centerPanel.getHeight());
            }
        };
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        JButton btnQuanLyHoKhau = new JButton("Quản lý hộ khẩu") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(centerPanel.getWidth() / 3, centerPanel.getHeight());
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, centerPanel.getHeight() / 20);
            }
        };
        btnQuanLyHoKhau.setBackground(Color.WHITE);
        btnQuanLyHoKhau.setForeground(Color.BLACK);
        btnQuanLyHoKhau.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Thiết lập border
        btnQuanLyHoKhau.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon quanLyHoKhauIcon = new ImageIcon("QuanLyDanCu/src/icon/hoKhauIcon.png");
        Image img = quanLyHoKhauIcon.getImage();
        Image scaledImg = img.getScaledInstance(btnQuanLyHoKhau.getWidth() - 1, btnQuanLyHoKhau.getHeight() - 1, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        btnQuanLyHoKhau.setIcon(scaledIcon);
        btnQuanLyHoKhau.setHorizontalTextPosition(SwingConstants.CENTER);
        btnQuanLyHoKhau.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnQuanLyHoKhau.setIconTextGap(15); // Đặt khoảng cách giữa icon và văn bản

        buttonPanel.add(btnQuanLyHoKhau);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Để tạo khoảng cách giữa nút

        JButton btnQuanLyNhanKhau = new JButton("Quản lý nhân khẩu") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(centerPanel.getWidth() / 3, centerPanel.getHeight());
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, centerPanel.getHeight() / 20);
            }
        };

        btnQuanLyNhanKhau.setBackground(Color.WHITE);
        btnQuanLyNhanKhau.setForeground(Color.BLACK);
        btnQuanLyNhanKhau.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Thiết lập border
        btnQuanLyNhanKhau.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon quanLyNhanKhauIcon = new ImageIcon("QuanLyDanCu/src/icon/nhanKhauIcon.png");
        img = quanLyNhanKhauIcon.getImage();
        scaledImg = img.getScaledInstance(btnQuanLyNhanKhau.getWidth() - 1, btnQuanLyNhanKhau.getHeight() - 1, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImg);

        btnQuanLyNhanKhau.setIcon(scaledIcon);
        btnQuanLyNhanKhau.setHorizontalTextPosition(SwingConstants.CENTER);
        btnQuanLyNhanKhau.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnQuanLyNhanKhau.setIconTextGap(15); // Đặt khoảng cách giữa icon và văn bản

        buttonPanel.add(btnQuanLyNhanKhau);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Để tạo khoảng cách giữa nút

        JButton btnQuanLyThuPhi = new JButton("Quản lý thu phí") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(centerPanel.getWidth() / 3, centerPanel.getHeight());
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, centerPanel.getHeight() / 20);
            }
        };

        btnQuanLyThuPhi.setBackground(Color.WHITE);
        btnQuanLyThuPhi.setForeground(Color.BLACK);
        btnQuanLyThuPhi.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Thiết lập border
        btnQuanLyThuPhi.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon quanLyThuPhiIcon = new ImageIcon("QuanLyDanCu/src/icon/thuPhiIcon.png");
        img = quanLyThuPhiIcon.getImage();
        scaledImg = img.getScaledInstance(btnQuanLyThuPhi.getWidth() - 1, btnQuanLyThuPhi.getHeight() - 1, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImg);

        btnQuanLyThuPhi.setIcon(scaledIcon);
        btnQuanLyThuPhi.setHorizontalTextPosition(SwingConstants.CENTER);
        btnQuanLyThuPhi.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnQuanLyThuPhi.setIconTextGap(15); // Đặt khoảng cách giữa icon và văn bản

        buttonPanel.add(btnQuanLyThuPhi);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Để tạo khoảng cách giữa nút

        JButton btnQuanLyPhatThuong = new JButton("Quản lý phát thưởng") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(centerPanel.getWidth() / 3, centerPanel.getHeight());
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, centerPanel.getHeight() / 20);
            }
        };

        btnQuanLyPhatThuong.setBackground(Color.WHITE);
        btnQuanLyPhatThuong.setForeground(Color.BLACK);
        btnQuanLyPhatThuong.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Thiết lập border
        btnQuanLyPhatThuong.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon quanLyPhatThuongIcon = new ImageIcon("QuanLyDanCu/src/icon/phatThuongIcon.png");
        img = quanLyPhatThuongIcon.getImage();
        scaledImg = img.getScaledInstance(btnQuanLyPhatThuong.getWidth() - 1, btnQuanLyPhatThuong.getHeight() - 1, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImg);

        btnQuanLyPhatThuong.setIcon(scaledIcon);
        btnQuanLyPhatThuong.setHorizontalTextPosition(SwingConstants.CENTER);
        btnQuanLyPhatThuong.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnQuanLyPhatThuong.setIconTextGap(15); // Đặt khoảng cách giữa icon và văn bản

        buttonPanel.add(btnQuanLyPhatThuong);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Để tạo khoảng cách giữa nút

        rightPanel.add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new GiaoDienBanDau();
    }
}