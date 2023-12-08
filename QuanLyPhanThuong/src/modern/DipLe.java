package modern;
import java.util.*;
import java.time.LocalDate;
public class DipLe {
    private String tenDipLe;
    private LocalDate ngay;
    private float kinhPhi;
    public DipLe(String tenDipLe, LocalDate ngay) {
        this.tenDipLe = tenDipLe;
        this.ngay = ngay;
    }
    public String getTenDipLe() {
        return tenDipLe;
    }
    public void setTenDipLe(String tenDipLe) {
        this.tenDipLe = tenDipLe;
    }
    public LocalDate getNgay() {
        return ngay;
    }
    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }
    public float getKinhPhi() {
        return kinhPhi;
    }
    public void setKinhPhi(float kinhPhi) {
        this.kinhPhi = kinhPhi;
    }

    
}
