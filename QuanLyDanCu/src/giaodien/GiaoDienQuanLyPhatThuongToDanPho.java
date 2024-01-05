package QuanLyDanCu.src.giaodien;

import javax.swing.*;
import java.awt.*;

public class GiaoDienQuanLyPhatThuongToDanPho extends GiaoDienDangNhapToDanPho {
    public GiaoDienQuanLyPhatThuongToDanPho(String taiKhoan) {
        super(taiKhoan);

        centerPanel.remove(mainButtonPanel);
        centerPanel.setBackground(Color.WHITE);

        northPanel.removeAll();
        JLabel title = new JLabel("QUẢN LÝ PHÁT THƯỞNG") {
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
