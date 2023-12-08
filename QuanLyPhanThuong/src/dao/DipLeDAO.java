package dao;

import java.util.ArrayList;

import modern.DipLe;

public class DipLeDAO implements DAOInterface<DipLe>{

    public static DipLeDAO getInstance(DipLe diple){
        return new DipLeDAO();
    }

    @Override
    public int delete(DipLe t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insert(DipLe t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ArrayList<DipLe> selectAll() {
       
        return null;
    }

    @Override
    public ArrayList<DipLe> selectByCondition(String condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DipLe selectByID(DipLe t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int update(DipLe t) {
        // TODO Auto-generated method stub
        return 0;
    }

}
