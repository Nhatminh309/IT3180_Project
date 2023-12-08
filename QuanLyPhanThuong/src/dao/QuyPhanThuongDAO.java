package dao;

import java.util.ArrayList;
import modern.QuyPhanThuong;

public class QuyPhanThuongDAO implements DAOInterface<QuyPhanThuong>{

    public static QuyPhanThuongDAO getInstance(){
        return new QuyPhanThuongDAO();
    }
    @Override
    public int delete(QuyPhanThuong t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insert(QuyPhanThuong t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ArrayList<QuyPhanThuong> selectAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<QuyPhanThuong> selectByCondition(String condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public QuyPhanThuong selectByID(QuyPhanThuong t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int update(QuyPhanThuong t) {
        // TODO Auto-generated method stub
        return 0;
    }
    

}
