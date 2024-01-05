package QuanLyDanCu.src.quanlythuphi;
import QuanLyDanCu.src.giaodien.GiaoDienQuanLyThuPhiChungCu;
import java.awt.*;
public class GiaoDienThongKeThuPhi extends GiaoDienQuanLyThuPhiChungCu{
    public GiaoDienThongKeThuPhi(String taiKhoan) {
        super(taiKhoan);
        navigateBtnThongKe.setBackground(Color.decode("#004AAD"));

        centerPanel.remove(mainButtonPanel);
        centerPanel.setBackground(Color.WHITE);

        northPanel.removeAll();
    }
    public static void main(String[] args) {
        new GiaoDienThongKeThuPhi("admin");
    }
}
