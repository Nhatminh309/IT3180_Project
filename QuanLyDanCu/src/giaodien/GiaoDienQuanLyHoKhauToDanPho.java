package QuanLyDanCu.src.giaodien;

import javax.swing.*;
import java.awt.*;

public class GiaoDienQuanLyHoKhauToDanPho extends GiaoDienDangNhapToDanPho{
    public GiaoDienQuanLyHoKhauToDanPho(String taiKhoan) {
        super(taiKhoan);

        northPanel.removeAll();

        JLabel title = new JLabel("QUẢN LÝ HỘ KHẨU") {
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

        centerPanel.remove(mainButtonPanel);
        centerPanel.setBackground(Color.WHITE);

        JLabel subTitle = new JLabel("Tổ dân phố 7") {
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

        frame.revalidate();
        frame.repaint();
    }
}
