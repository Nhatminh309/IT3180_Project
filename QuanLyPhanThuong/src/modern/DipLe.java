package modern;
import java.util.*;
public class DipLe {
    private String tenDipLe;
    private Date ngay;
    private float kinhPhi;
    public DipLe(String tenDipLe, Date ngay) {
        this.tenDipLe = tenDipLe;
        this.ngay = ngay;
    }
    public String getTenDipLe() {
        return tenDipLe;
    }
    public void setTenDipLe(String tenDipLe) {
        this.tenDipLe = tenDipLe;
    }
    public Date getNgay() {
        return ngay;
    }
    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
    public float getKinhPhi() {
        return kinhPhi;
    }
    public void setKinhPhi(float kinhPhi) {
        this.kinhPhi = kinhPhi;
    }

    
}
