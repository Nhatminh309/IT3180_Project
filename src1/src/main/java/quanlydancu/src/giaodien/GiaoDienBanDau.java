package quanlydancu.src.giaodien;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import java.awt.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import quanlydancu.src.dangky.DangKy;
import quanlydancu.src.dangnhap.DangNhap;
import quanlydancu.src.graphics.RoundBorder;
import quanlydancu.src.quanlyhokhau.QuanLyHoKhau;
import quanlydancu.src.quanlynhankhau.QuanLyNhanKhau;
import quanlydancu.src.quanlyphatthuong.GiaoDienPhatThuong;


public class GiaoDienBanDau extends GiaoDienChung {
    protected static JButton dangNhap;
    protected static JButton dangKy;
    protected static JButton btnQuanLyHoKhau;
    protected static JButton btnQuanLyNhanKhau;
    protected static JButton btnQuanLyThuPhi;
    protected static JButton btnQuanLyPhatThuong;
    protected static Border lineBorder = new RoundBorder(10);
    protected static Border emptyBorder = BorderFactory.createEmptyBorder(0, 20, 0, 20);
    protected static Border compoundBorder = new CompoundBorder(lineBorder, emptyBorder);

    public GiaoDienBanDau() {
        super();
        dangNhap = new JButton("Dân cư") {
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
        dangNhap.addActionListener(e -> {
            frame.dispose();
            new DangNhap();
        });

        dangKy = new JButton("Quản lý") {
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
        dangKy.addActionListener(e -> {
            frame.dispose();
            new DangKy();
        });

        horizontalBar.add(Box.createHorizontalGlue()); // Thêm glue đưa các thành phần về bên phải
        horizontalBar.add(dangKy);
        horizontalBar.add(Box.createRigidArea(new Dimension(20, 0))); // Để tạo khoảng cách giữa nút
        horizontalBar.add(dangNhap);
        horizontalBar.add(Box.createRigidArea(new Dimension(40, 0))); // Để tạo khoảng cách giữa nút

        northPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(mainPanel.getWidth(), mainPanel.getHeight() / 4);
            }
        };
        northPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("HỆ THỐNG QUẢN LÝ DÂN CƯ") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(mainPanel.getWidth(), mainPanel.getHeight() / 5);
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, mainPanel.getHeight() / 20);
            }
        };
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setForeground(Color.BLACK);

        northPanel.add(title, BorderLayout.CENTER);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        centerPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(mainPanel.getWidth(), mainPanel.getHeight() / 2);
            }
        };

        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.decode("#F5F5F5"));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        mainButtonPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(centerPanel.getWidth(), centerPanel.getHeight());
            }
        };
        mainButtonPanel.setLayout(new BoxLayout(mainButtonPanel, BoxLayout.X_AXIS));
        mainButtonPanel.setBackground(Color.decode("#F5F5F5"));
        centerPanel.add(mainButtonPanel, BorderLayout.CENTER);

        mainButtonPanel.add(Box.createHorizontalGlue());
        mainButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainButtonPanel.add(Box.createHorizontalStrut(10));
        btnQuanLyHoKhau = new JButton("HỘ KHẨU") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(centerPanel.getWidth() / 4, centerPanel.getWidth() / 4);
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, centerPanel.getHeight() / 30);
            }
        };
        btnQuanLyHoKhau.setBackground(Color.WHITE);
        btnQuanLyHoKhau.setForeground(Color.BLACK);
        btnQuanLyHoKhau.setBorder(compoundBorder);
        btnQuanLyHoKhau.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon quanLyHoKhauIcon = new ImageIcon("QuanLyDanCu/src/icon/hoKhauIcon.png");
        Image img = quanLyHoKhauIcon.getImage();
        Image scaledImg = img.getScaledInstance(btnQuanLyHoKhau.getWidth() - 1, btnQuanLyHoKhau.getHeight() - 1, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        btnQuanLyHoKhau.setIcon(scaledIcon);
        btnQuanLyHoKhau.setHorizontalTextPosition(SwingConstants.CENTER);
        btnQuanLyHoKhau.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnQuanLyHoKhau.setIconTextGap(15); // Đặt khoảng cách giữa icon và văn bản

        mainButtonPanel.add(btnQuanLyHoKhau);
        mainButtonPanel.add(Box.createHorizontalStrut(10));

        btnQuanLyNhanKhau = new JButton("NHÂN KHẨU") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(centerPanel.getWidth() / 4, centerPanel.getWidth() / 4);
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, centerPanel.getHeight() / 30);
            }
        };

        btnQuanLyNhanKhau.setBackground(Color.WHITE);
        btnQuanLyNhanKhau.setForeground(Color.BLACK);
//        btnQuanLyNhanKhau.setBorder(BorderFactory.createLineBorder(Color.decode("#38B6FF"), 3, true)); // Thiết lập border
        btnQuanLyNhanKhau.setBorder(compoundBorder);
        btnQuanLyNhanKhau.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon quanLyNhanKhauIcon = new ImageIcon("QuanLyDanCu/src/icon/nhanKhauIcon.png");
        img = quanLyNhanKhauIcon.getImage();
        scaledImg = img.getScaledInstance(btnQuanLyNhanKhau.getWidth() - 1, btnQuanLyNhanKhau.getHeight() - 1, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImg);

        btnQuanLyNhanKhau.setIcon(scaledIcon);
        btnQuanLyNhanKhau.setHorizontalTextPosition(SwingConstants.CENTER);
        btnQuanLyNhanKhau.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnQuanLyNhanKhau.setIconTextGap(15); // Đặt khoảng cách giữa icon và văn bản

        mainButtonPanel.add(btnQuanLyNhanKhau);
        mainButtonPanel.add(Box.createHorizontalStrut(10));

        btnQuanLyThuPhi = new JButton("THU PHÍ, ĐÓNG GÓP") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(centerPanel.getWidth() / 4, centerPanel.getWidth() / 4);
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, centerPanel.getHeight() / 30);
            }
        };

        btnQuanLyThuPhi.setBackground(Color.WHITE);
        btnQuanLyThuPhi.setForeground(Color.BLACK);
//        btnQuanLyThuPhi.setBorder(BorderFactory.createLineBorder(Color.decode("#38B6FF"), 3, true)); // Thiết lập border
        btnQuanLyThuPhi.setBorder(compoundBorder);
        btnQuanLyThuPhi.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon quanLyThuPhiIcon = new ImageIcon("QuanLyDanCu/src/icon/thuPhiIcon.png");
        img = quanLyThuPhiIcon.getImage();
        scaledImg = img.getScaledInstance(btnQuanLyThuPhi.getWidth() - 1, btnQuanLyThuPhi.getHeight() - 1, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImg);

        btnQuanLyThuPhi.setIcon(scaledIcon);
        btnQuanLyThuPhi.setHorizontalTextPosition(SwingConstants.CENTER);
        btnQuanLyThuPhi.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnQuanLyThuPhi.setIconTextGap(15); // Đặt khoảng cách giữa icon và văn bản

        mainButtonPanel.add(btnQuanLyThuPhi);
        mainButtonPanel.add(Box.createHorizontalStrut(10));

        btnQuanLyPhatThuong = new JButton("PHÁT THƯỞNG") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(centerPanel.getWidth() / 4, centerPanel.getWidth() / 4);
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, centerPanel.getHeight() / 30);
            }
        };

        btnQuanLyPhatThuong.setBackground(Color.WHITE);
        btnQuanLyPhatThuong.setForeground(Color.BLACK);
//        btnQuanLyPhatThuong.setBorder(BorderFactory.createLineBorder(Color.decode("#38B6FF"), 3, true)); // Thiết lập border
        btnQuanLyPhatThuong.setBorder(compoundBorder);
        btnQuanLyPhatThuong.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon quanLyPhatThuongIcon = new ImageIcon("QuanLyDanCu/src/icon/phatThuongIcon.png");
        img = quanLyPhatThuongIcon.getImage();
        scaledImg = img.getScaledInstance(btnQuanLyPhatThuong.getWidth() - 1, btnQuanLyPhatThuong.getHeight() - 1, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImg);

        btnQuanLyPhatThuong.setIcon(scaledIcon);
        btnQuanLyPhatThuong.setHorizontalTextPosition(SwingConstants.CENTER);
        btnQuanLyPhatThuong.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnQuanLyPhatThuong.setIconTextGap(15); // Đặt khoảng cách giữa icon và văn bản

        mainButtonPanel.add(btnQuanLyPhatThuong);
        mainButtonPanel.add(Box.createHorizontalStrut(10));

        frame.revalidate();
        frame.repaint();

        btnQuanLyHoKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new QuanLyHoKhau();
                frame.dispose();
            }
        });

        btnQuanLyNhanKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new QuanLyNhanKhau();
                frame.dispose();
            }
        });

        btnQuanLyPhatThuong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new GiaoDienPhatThuong();
            }
        });

    }

    public static void main(String[] args) {
        new GiaoDienBanDau();
    }
}
