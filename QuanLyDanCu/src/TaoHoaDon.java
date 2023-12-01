import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import danhsachhoadon.DanhSachHoaDon;
public class TaoHoaDon extends DanhSachHoaDon {
    private static JTextField dvuField;
//    private static JTextField qlyField;
//
//    public JTextField getDvuField() {
//        return dvuField;
//    }
//    public JTextField getQlyField() {
//        return qlyField;
//    }
//    public void insertFee() {
//        JPanel panelBoard = getPanelBoard();
//        panelBoard.setLayout(null);
//        dvuLabel = new JLabel("Nhập phí dịch vụ: ");
//        qlyLabel = new JLabel("Nhập phí quản lý: ");
//        dvuField = new JTextField(10);
//        qlyField = new JTextField(10);
//
//        dvuLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//        qlyField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//
//        dvuLabel.setBounds(200, 200, 100, 50);
//        dvuField.setBounds(200, 250, 100, 50);
//        qlyLabel.setBounds(200, 300, 100, 50);
//        qlyField.setBounds(200, 350, 100, 50);
//
//        panelBoard.add(dvuLabel);
//        panelBoard.add(dvuField);
//        panelBoard.add(qlyLabel);
//        panelBoard.add(qlyField);
//
//        viewChungCu();
    //}
    //update BangPhi
//        try {
//            String URL = "jdbc:postgresql://localhost:5432/QuanLyDanCu";
//            Connection connection = DriverManager.getConnection(URL, "postgres", "271203");
//            int phiDvu = Integer.parseInt(getDvuField().getText());
//            int phiQly = Integer.parseInt(getQlyField().getText());
//            String updateDvu = "UPDATE Bang_phi AS bp SET phi_dvu_chung_cu = ? * hk.dien_tich FROM ho_khau AS hk WHERE bp.ma_ho_khau = hk.ma_nhan_khau";
//            String updateQly =  "UPDATE Bang_phi AS bp SET phi_qly_chung_cu = ? * hk.dien_tich FROM ho_khau AS hk WHERE bp.ma_ho_khau = hk.ma_nhan_khau";
//            PreparedStatement preparedStatement1 = connection.prepareStatement(updateDvu);
//            PreparedStatement preparedStatement2 = connection.prepareStatement(updateQly);
//            preparedStatement1.setInt(1, phiDvu);
//            preparedStatement2.setInt(1, phiQly);
//            preparedStatement1.executeUpdate();
//            preparedStatement2.executeUpdate();
//
//            preparedStatement1.close();
//            preparedStatement2.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
}
