package modern;
import java.util.*;
import java.sql.Date;

public class PhanThuong {
    private int maSoPhanThuong;
    private Date ngayDangKy;
    private boolean daNhan;
    private String loaiPhanThuong;
    private int soLuong;
    public static int nbPhanThuong=0;
    private String dipLe;
    private int giaTri;
    private int maNhanKhau;
    
    public int getMaNhanKhau() {
        return maNhanKhau;
    }
    public void setMaNhanKhau(int maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
    }
    public int getGiaTri() {
        return giaTri;
    }
    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }
    public void setLoaiPhanThuong(String loaiPhanThuong) {
        this.loaiPhanThuong = loaiPhanThuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public String getDipLe() {
        return dipLe;
    }
    @Override
    public String toString() {
        return "PhanThuong [maSoPhanThuong=" + maSoPhanThuong + ", ngayDangKy=" + ngayDangKy + ", daNhan=" + daNhan
                + ", loaiPhanThuong=" + loaiPhanThuong + ", soLuong=" + soLuong + ", dipLe=" + dipLe + ", giaTri="
                + giaTri + ", maNhanKhau=" + maNhanKhau + "]";
    }
    public void setDipLe(String dipLe) {
        this.dipLe = dipLe;
    }
    public PhanThuong(String diple,String loaiPhanThuong, Date ngayDangKy,int soLuong,int maNhanKhau) {
        this.dipLe=diple;
        this.maSoPhanThuong = nbPhanThuong;
        nbPhanThuong++;
        this.ngayDangKy = ngayDangKy;
        this.loaiPhanThuong=loaiPhanThuong;
        this.soLuong=soLuong;
        this.maNhanKhau=maNhanKhau;
        this.daNhan=false;
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
