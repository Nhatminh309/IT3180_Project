package controller;

import modern.*;
import java.util.*;
public class ControllerPhanThuong {
    List<PhanThuong> phanThuongs=new ArrayList<PhanThuong>();

    public void addPhanThuong(PhanThuong phanthuong){
        if(!phanThuongs.contains(phanthuong)){
            phanThuongs.add(phanthuong);
        }
    }
    public void removePhanThuong(PhanThuong phanthuong){
        if(phanThuongs.contains(phanthuong)){
            phanThuongs.remove(phanthuong);
        }
    }
    public PhanThuong searchPhanThuong(Date ngayDK){
        for(PhanThuong phanthuong:phanThuongs){
            if(phanthuong.getNgayDangKy()==ngayDK){
                return phanthuong;
            }

        }
        return null;
    }
    public void nhan(PhanThuong phanThuong){    // phần thưởng đã nhận
        phanThuong.setDaNhan(true);
    }
    

}
