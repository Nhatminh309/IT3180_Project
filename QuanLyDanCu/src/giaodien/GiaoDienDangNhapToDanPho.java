package QuanLyDanCu.src.giaodien;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GiaoDienDangNhapToDanPho extends GiaoDienDangNhap{
    public GiaoDienDangNhapToDanPho(String taiKhoan) {
        super(taiKhoan);


        mainButtonPanel.remove(btnQuanLyThuPhi);
        btnQuanLyHoKhau.setPreferredSize(new Dimension(mainButtonPanel.getWidth() / 3, mainButtonPanel.getHeight()));
        btnQuanLyNhanKhau.setPreferredSize(new Dimension(mainButtonPanel.getWidth() / 3, mainButtonPanel.getHeight()));
        btnQuanLyPhatThuong.setPreferredSize(new Dimension(mainButtonPanel.getWidth() / 3, mainButtonPanel.getHeight()));

        btnQuanLyHoKhau.addActionListener(e -> {
            frame.dispose();
            new GiaoDienQuanLyHoKhauToDanPho(taiKhoan);
        });

        btnQuanLyNhanKhau.addActionListener(e -> {
            frame.dispose();
            new GiaoDienQuanLyNhanKhauToDanPho(taiKhoan);
        });

        btnQuanLyPhatThuong.addActionListener(e -> {
            frame.dispose();
            new GiaoDienQuanLyPhatThuongToDanPho(taiKhoan);
        });

        mainButtonPanel.add(Box.createHorizontalGlue());
        mainButtonPanel.revalidate();
        mainButtonPanel.repaint();

        JLabel toDanPhoLabel = new JLabel("Tổ dân phố 7") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(centerPanel.getWidth() / 3, centerPanel.getHeight() / 6);
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, centerPanel.getHeight() / 15);
            }
        };
        toDanPhoLabel.setHorizontalAlignment(JLabel.CENTER);
        toDanPhoLabel.setVerticalAlignment(JLabel.CENTER);
        toDanPhoLabel.setForeground(Color.decode("#0097B2"));
        northPanel.add(toDanPhoLabel, BorderLayout.SOUTH);

        btnHome.addActionListener(e -> {
            frame.dispose();
            new GiaoDienDangNhapToDanPho(taiKhoan);
        });
    }

    public static void main(String[] args) {
        new GiaoDienDangNhapToDanPho("admin");
    }
}
