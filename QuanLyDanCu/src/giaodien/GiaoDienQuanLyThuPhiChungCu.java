package QuanLyDanCu.src.giaodien;
import QuanLyDanCu.src.graphics.RoundBorder;
import QuanLyDanCu.src.quanlythuphi.GiaoDienDongGop;
import QuanLyDanCu.src.quanlythuphi.GiaoDienThongKeThuPhi;
import QuanLyDanCu.src.quanlythuphi.GiaoDienThuPhi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GiaoDienQuanLyThuPhiChungCu extends GiaoDienDangNhapChungCu{
    protected static JButton navigateBtnThuPhi;
    protected static JButton navigateBtnDongGop;
    protected static JButton navigateBtnThongKe;
    public GiaoDienQuanLyThuPhiChungCu(String taiKhoan) {
         super(taiKhoan);

         northPanel.removeAll();
         JLabel title = new JLabel("QUẢN LÝ THU PHÍ, ĐÓNG GÓP") {
             @Override
             public Dimension getPreferredSize() {
                 return new Dimension(mainPanel.getWidth(), mainPanel.getHeight() / 6);
             }
             public Font getFont() {
                 return new Font("Arial", Font.PLAIN, mainPanel.getHeight() / 20);
             }
         };
         title.setHorizontalAlignment(JLabel.CENTER);
         title.setForeground(Color.BLACK);
         northPanel.add(title, BorderLayout.CENTER);

         JLabel subTitle = new JLabel("Chung cư BlueMoon") {
             @Override
             public Dimension getPreferredSize() {
                 return new Dimension(mainPanel.getWidth(), mainPanel.getHeight() / 8);
             }
             public Font getFont() {
                 return new Font("Arial", Font.PLAIN, mainPanel.getHeight() / 25);
             }
         };
         subTitle.setHorizontalAlignment(JLabel.CENTER);
         subTitle.setForeground(Color.decode("#0097B2"));
         northPanel.add(subTitle, BorderLayout.SOUTH);


        mainButtonPanel.removeAll();
        mainButtonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        mainButtonPanel.add(Box.createHorizontalStrut(10));
        JButton btnQuanLyThuPhi = new JButton("THU PHÍ") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(mainButtonPanel.getWidth() / 3, mainButtonPanel.getHeight());
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, mainPanel.getHeight() / 30);
            }
        };
        btnQuanLyThuPhi.setBackground(Color.WHITE);
        btnQuanLyThuPhi.setForeground(Color.BLACK);
        btnQuanLyThuPhi.setBorder(compoundBorder);
        btnQuanLyThuPhi.setFocusPainted(false);

        ImageIcon icon = new ImageIcon("QuanLyDanCu/src/icon/billIcon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(btnQuanLyThuPhi.getWidth() - 1, btnQuanLyThuPhi.getHeight() - 1, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        btnQuanLyThuPhi.setIcon(scaledIcon);
        btnQuanLyThuPhi.setHorizontalTextPosition(JButton.CENTER);
        btnQuanLyThuPhi.setVerticalTextPosition(JButton.BOTTOM);
        btnQuanLyThuPhi.setIconTextGap(15);

        mainButtonPanel.add(btnQuanLyThuPhi);

        mainButtonPanel.add(Box.createHorizontalStrut(10));

        JButton btnQuanLyDongGop = new JButton("ĐÓNG GÓP") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(mainButtonPanel.getWidth() / 3, mainButtonPanel.getHeight() / 3);
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, mainPanel.getHeight() / 30);
            }
        };
        btnQuanLyDongGop.setBackground(Color.WHITE);
        btnQuanLyDongGop.setForeground(Color.BLACK);
        btnQuanLyDongGop.setBorder(compoundBorder);
        btnQuanLyDongGop.setFocusPainted(false);

        icon = new ImageIcon("QuanLyDanCu/src/icon/dongGopIcon.png");
        img = icon.getImage();
        scaledImg = img.getScaledInstance(btnQuanLyDongGop.getWidth() - 1, btnQuanLyDongGop.getHeight() - 1, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImg);
        btnQuanLyDongGop.setIcon(scaledIcon);

        btnQuanLyDongGop.setHorizontalTextPosition(JButton.CENTER);
        btnQuanLyDongGop.setVerticalTextPosition(JButton.BOTTOM);
        btnQuanLyDongGop.setIconTextGap(15);

        mainButtonPanel.add(btnQuanLyDongGop);
        mainButtonPanel.add(Box.createHorizontalStrut(10));

        JButton btnThongKe = new JButton("THỐNG KÊ") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(mainButtonPanel.getWidth() / 3, mainButtonPanel.getHeight());
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, mainPanel.getHeight() / 30);
            }
        };
        btnThongKe.setBackground(Color.WHITE);
        btnThongKe.setForeground(Color.BLACK);
        btnThongKe.setBorder(compoundBorder);
        btnThongKe.setFocusPainted(false);

        icon = new ImageIcon("QuanLyDanCu/src/icon/thongKeIcon.png");
        img = icon.getImage();
        scaledImg = img.getScaledInstance(btnThongKe.getWidth() - 1, btnThongKe.getHeight() - 1, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImg);
        btnThongKe.setIcon(scaledIcon);

        btnThongKe.setHorizontalTextPosition(JButton.CENTER);
        btnThongKe.setVerticalTextPosition(JButton.BOTTOM);
        btnThongKe.setIconTextGap(15);

        mainButtonPanel.add(btnThongKe);
        mainButtonPanel.add(Box.createHorizontalStrut(10));

        int margin = (navigatePanel.getHeight() - navigatePanel.getHeight() / 7) / 10;
        navigatePanel.add(Box.createVerticalStrut(margin));
        navigateBtnThuPhi = new JButton("Thu phí") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(navigatePanel.getWidth(), leftPanel.getHeight() / 7);
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, leftPanel.getHeight() / 30);
            }
        };

        int border = (navigatePanel.getWidth() - navigateBtnThuPhi.getWidth()) / 10;

        navigateBtnThuPhi.setBackground(Color.decode("#5271FF"));
        navigateBtnThuPhi.setForeground(Color.WHITE);
        navigateBtnThuPhi.setFont(new Font("Arial", Font.PLAIN, 20));
        navigateBtnThuPhi.setBorder(BorderFactory.createEmptyBorder(5, border, 5, border)); // Thiết lập border
        navigateBtnThuPhi.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon thuPhiIcon = new ImageIcon("QuanLyDanCu/src/icon/billIcon.png");
        int width = 50;
        int height = 50;
        Image scaledImage = thuPhiIcon.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH);

        navigateBtnThuPhi.setIcon(new ImageIcon(scaledImage));
        navigateBtnThuPhi.setHorizontalTextPosition(SwingConstants.RIGHT); // Đặt vị trí của văn bản bên phải của icon
        navigateBtnThuPhi.setVerticalTextPosition(SwingConstants.CENTER);
        navigateBtnThuPhi.setIconTextGap(15); // Đặt khoảng cách giữa icon và văn bản

        navigatePanel.add(navigateBtnThuPhi);
        navigatePanel.add(Box.createVerticalStrut(margin));

        navigateBtnDongGop = new JButton("Đóng góp") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(navigatePanel.getWidth(), leftPanel.getHeight() / 7);
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, leftPanel.getHeight() / 30);
            }
        };
        navigateBtnDongGop.setBackground(Color.decode("#5271FF"));
        navigateBtnDongGop.setForeground(Color.WHITE);
        navigateBtnDongGop.setFont(new Font("Arial", Font.PLAIN, 20));
        navigateBtnDongGop.setBorder(BorderFactory.createEmptyBorder(5, border, 5, border)); // Thiết lập border
        navigateBtnDongGop.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon dongGopIcon = new ImageIcon("QuanLyDanCu/src/icon/dongGopIcon.png");
        scaledImage = dongGopIcon.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH);

        navigateBtnDongGop.setIcon(new ImageIcon(scaledImage));
        navigateBtnDongGop.setHorizontalTextPosition(SwingConstants.RIGHT); // Đặt vị trí của văn bản bên phải của icon
        navigateBtnDongGop.setVerticalTextPosition(SwingConstants.CENTER);
        navigateBtnDongGop.setIconTextGap(15); // Đặt khoảng cách giữa icon và văn bản

        navigatePanel.add(navigateBtnDongGop);
        navigatePanel.add(Box.createVerticalStrut(margin));

        navigateBtnThongKe = new JButton("Thống kê") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(navigatePanel.getWidth(), leftPanel.getHeight() / 7);
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, leftPanel.getHeight() / 30);
            }
        };
        navigateBtnThongKe.setBackground(Color.decode("#5271FF"));
        navigateBtnThongKe.setForeground(Color.WHITE);
        navigateBtnThongKe.setFont(new Font("Arial", Font.PLAIN, 20));
        navigateBtnThongKe.setBorder(BorderFactory.createEmptyBorder(5, border, 5, border)); // Thiết lập border
        navigateBtnThongKe.setFocusPainted(false); // Loại bỏ viền focus

        ImageIcon thongKeIcon = new ImageIcon("QuanLyDanCu/src/icon/thongKeIcon.png");
        scaledImage = thongKeIcon.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH);

        navigateBtnThongKe.setIcon(new ImageIcon(scaledImage));
        navigateBtnThongKe.setHorizontalTextPosition(SwingConstants.RIGHT); // Đặt vị trí của văn bản bên phải của icon
        navigateBtnThongKe.setVerticalTextPosition(SwingConstants.CENTER);
        navigateBtnThongKe.setIconTextGap(15); // Đặt khoảng cách giữa icon và văn bản

        navigatePanel.add(navigateBtnThongKe);
        navigatePanel.add(Box.createVerticalStrut(margin));

        navigatePanel.add(Box.createVerticalGlue());



        btnQuanLyThuPhi.addActionListener(e -> {
            frame.dispose();
            new GiaoDienThuPhi(taiKhoan);
        });
        navigateBtnThuPhi.addActionListener(e -> {
            frame.dispose();
            new GiaoDienThuPhi(taiKhoan);
        });

        btnQuanLyDongGop.addActionListener(e -> {
            frame.dispose();
            new GiaoDienDongGop(taiKhoan);
        });
        navigateBtnDongGop.addActionListener(e -> {
            frame.dispose();
            new GiaoDienDongGop(taiKhoan);
        });

        btnThongKe.addActionListener(e -> {
            frame.dispose();
            new GiaoDienThongKeThuPhi(taiKhoan);
        });
        navigateBtnThongKe.addActionListener(e -> {
            frame.dispose();
            new GiaoDienThongKeThuPhi(taiKhoan);
        });

        frame.revalidate();
        frame.repaint();
    }
    public static void main(String[] args) {
        new GiaoDienQuanLyThuPhiChungCu("admin");
    }
}
