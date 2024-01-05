package QuanLyDanCu.src.giaodien;

public class GiaoDienDangNhap extends GiaoDienBanDau{
    public GiaoDienDangNhap(String taiKhoan) {
        super();

        horizontalBar.remove(dangNhap);
        horizontalBar.remove(dangKy);

        addLogin(taiKhoan);
        frame.revalidate();
        frame.repaint();

    }
    public static void main(String[] args) {
        new GiaoDienDangNhap("admin");
    }
}
