package QuanLyDanCu.src.quanlythuphi;

import QuanLyDanCu.src.giaodien.GiaoDienQuanLyThuPhiChungCu;

import java.awt.*;

public class GiaoDienDongGop extends GiaoDienQuanLyThuPhiChungCu {
    public GiaoDienDongGop(String taiKhoan) {
        super(taiKhoan);
        navigateBtnDongGop.setBackground(Color.decode("#004AAD"));

        centerPanel.remove(mainButtonPanel);
        centerPanel.setBackground(Color.WHITE);

        northPanel.removeAll();

    }
    public static void main(String[] args) {
        new GiaoDienDongGop("admin");
    }
}
