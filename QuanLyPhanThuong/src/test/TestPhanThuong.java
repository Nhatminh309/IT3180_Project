package test;

import dao.PhanThuongDAO;

import modern.PhanThuong;
import java.sql.Date;
import java.util.ArrayList;

public class TestPhanThuong {
    public static void main(String[] args) {
        Date ngayDangKy = Date.valueOf("2023-12-31");
        Date ngayDangKy1 = Date.valueOf("2023-12-30");


        //PhanThuong t=new PhanThuong("Tết dương lịch","Tiền mặt",ngayDangKy,20,1);
        // PhanThuong t1=new PhanThuong("Tết dương lịch","Tiền mặt",ngayDangKy1,10,2);
        
       // PhanThuongDAO.getInstance().insert(t);
       // PhanThuongDAO.getInstance().insert(t1);
       ArrayList<PhanThuong> result=PhanThuongDAO.getInstance().selectAll();
       for(PhanThuong phanThuong:result){
        System.out.println(phanThuong.toString());
       }
        
    }
}

