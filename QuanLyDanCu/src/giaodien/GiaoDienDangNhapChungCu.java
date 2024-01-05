package QuanLyDanCu.src.giaodien;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class GiaoDienDangNhapChungCu extends GiaoDienDangNhap{
    public GiaoDienDangNhapChungCu (String taiKhoan) {
        super(taiKhoan);

        mainButtonPanel.remove(btnQuanLyPhatThuong);
        btnQuanLyHoKhau.setPreferredSize(new Dimension(mainButtonPanel.getWidth() / 3, mainButtonPanel.getHeight()));
        btnQuanLyNhanKhau.setPreferredSize(new Dimension(mainButtonPanel.getWidth() / 3, mainButtonPanel.getHeight()));
        btnQuanLyThuPhi.setPreferredSize(new Dimension(mainButtonPanel.getWidth() / 3, mainButtonPanel.getHeight()));

        btnQuanLyHoKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new GiaoDienQuanLyHoKhauChungCu(taiKhoan);
            }
        });
        btnQuanLyNhanKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
//                new GiaoDienQuanLyNhanKhauChungCu(taiKhoan);
            }
        });
        btnQuanLyThuPhi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new GiaoDienQuanLyThuPhiChungCu(taiKhoan);
            }
        });

        mainButtonPanel.add(Box.createHorizontalGlue());
        JLabel toDanPhoLabel = new JLabel("Chung cư BlueMoon") {
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

        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new GiaoDienDangNhapChungCu(taiKhoan);
            }
        });
        frame.revalidate();
        frame.repaint();
    }
    public static void main(String[] args) {
        new GiaoDienDangNhapChungCu("admin");
    }
}
