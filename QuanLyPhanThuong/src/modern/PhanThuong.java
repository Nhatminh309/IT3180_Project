package modern;
import java.util.*;

public class PhanThuong {
    private int maSoPhanThuong;
    private Date ngayDangKy;
    private boolean daNhan;
    private String loaiPhanThuong;
    private int soLuong;
    public static int nbPhanThuong=0;
    
    
    public PhanThuong(String loaiPhanThuong, Date ngayDangKy,int soLuong) {
        
        this.maSoPhanThuong = nbPhanThuong;
        nbPhanThuong++;
        this.ngayDangKy = ngayDangKy;
        this.loaiPhanThuong=loaiPhanThuong;
        this.soLuong=soLuong;
    }
    public int getMaSoPhanThuong() {
        return maSoPhanThuong;
    }
    public void setMaSoPhanThuong(int maSoPhanThuong) {
        this.maSoPhanThuong = maSoPhanThuong;
    }
    public Date getNgayDangKy() {
        return ngayDangKy;
    }
    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }
    public boolean isDaNhan() {
        return daNhan;
    }
    public void setDaNhan(boolean daNhan) {
        this.daNhan = daNhan;
    }
    public String getLoaiPhanThuong() {
        return loaiPhanThuong;
    }
    public int getSoLuong() {
        return soLuong;
    }
    
}
