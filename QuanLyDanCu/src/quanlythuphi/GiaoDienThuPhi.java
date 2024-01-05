package QuanLyDanCu.src.quanlythuphi;

import QuanLyDanCu.src.giaodien.GiaoDienQuanLyThuPhiChungCu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GiaoDienThuPhi extends GiaoDienQuanLyThuPhiChungCu{
    public GiaoDienThuPhi(String taiKhoan) {
        super(taiKhoan);

        navigateBtnThuPhi.setBackground(Color.decode("#004AAD"));

        centerPanel.remove(mainButtonPanel);
        centerPanel.setBackground(Color.WHITE);

        northPanel.removeAll();

        JLabel danhSachHoaDonLabel = new JLabel("Danh sách hóa đơn") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(northPanel.getWidth() / 3, northPanel.getHeight() / 6);
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, northPanel.getHeight() / 6);
            }
        };
        danhSachHoaDonLabel.setHorizontalAlignment(JLabel.CENTER);
        danhSachHoaDonLabel.setVerticalAlignment(JLabel.CENTER);

        northPanel.add(danhSachHoaDonLabel, BorderLayout.CENTER);

        JPanel optionPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(northPanel.getWidth() / 2, northPanel.getHeight() / 5);
            }
        };
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.X_AXIS));
        optionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnThemHoaDon = new JButton("THÊM HÓA ĐƠN") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(optionPanel.getWidth() / 7, optionPanel.getHeight());
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, optionPanel.getHeight() / 2);
            }
        };
        btnThemHoaDon.addActionListener(e -> {
            new GiaoDienThemPhi();
        });
        optionPanel.add(btnThemHoaDon);

        JButton btnSuaHoaDon = new JButton("SỬA HÓA ĐƠN") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(optionPanel.getWidth() / 7, optionPanel.getHeight());
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, optionPanel.getHeight() / 2);
            }
        };
        optionPanel.add(btnSuaHoaDon);

        JButton btnXoaHoaDon = new JButton("XÓA HÓA ĐƠN") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(optionPanel.getWidth() / 7, optionPanel.getHeight());
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, optionPanel.getHeight() / 2);
            }
        };
        optionPanel.add(btnXoaHoaDon);

        JButton btnLocHoaDon = new JButton("LỌC") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(optionPanel.getWidth() / 7, optionPanel.getHeight());
            }
            @Override
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, optionPanel.getHeight() / 2);
            }
        };
        optionPanel.add(btnLocHoaDon);
        northPanel.add(optionPanel, BorderLayout.SOUTH);


        frame.revalidate();
        frame.repaint();

    }
    private static JTable table;
    private static DefaultTableModel model;

    public static void main(String[] args) {
        new GiaoDienThuPhi("admin");
    }
}
