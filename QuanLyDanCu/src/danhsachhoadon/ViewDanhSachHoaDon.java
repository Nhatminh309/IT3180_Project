package danhsachhoadon;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class ViewDanhSachHoaDon extends DanhSachHoaDon {
    private static JTable table;
    private static DefaultTableModel model;
    private static JPanel viewPanel;
    private static JLabel viewLabel;
    private static JLabel dvuLabel;
    private static JLabel qlyLabel;
    public void viewChungCu() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBounds(0, 0, 1280, 700);
        viewLabel = new JLabel("Danh sách hoá đơn chung cư Moon");
        viewLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        viewPanel.add(viewLabel, BorderLayout.NORTH);
        viewPanel.add(Box.createRigidArea(new Dimension(0, 30)));


        model = new DefaultTableModel();
        table = new JTable(model);
        table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        table.setPreferredSize(new Dimension(1100, 650));

        //print table
        model.addColumn("Mã hoá đơn");
        model.addColumn("Địa điểm");
        model.addColumn("Phí quản lý");
        model.addColumn("Phí dịch vụ");
        model.addColumn("Thời điểm bắt đầu đóng");
        model.addColumn("Mã hộ khẩu");
        try {
            String URL = "jdbc:postgresql://localhost:5432/QuanLyDanCu";
            Connection connection = DriverManager.getConnection(URL, "postgres", "271203");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ma_hoa_don, dia_diem, phi_qly_chung_cu, phi_dvu_chung_cu, thoi_diem_dong, ma_ho_khau FROM bang_phi WHERE dia_diem = 'Chung cư BlueMoon'");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberCol = metaData.getColumnCount();

            while (resultSet.next()) {
                Object[] row = new Object[numberCol];
                for(int col = 1; col <= numberCol; col++) {
                    row[col - 1] = resultSet.getObject(col);
                    table.setRowHeight(50);
                }
                model.addRow(row);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        viewPanel.add(scrollPane, BorderLayout.CENTER);
        panelBoard.setLayout(null);
        panelBoard.add(viewPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel

    }
    public void viewToDanPho() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBounds(0, 0, 1280, 700);
        viewLabel = new JLabel("Danh sách hoá đơn tổ dân phố");
        viewLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        viewPanel.add(viewLabel, BorderLayout.NORTH);
        viewPanel.add(Box.createRigidArea(new Dimension(0, 30)));


        model = new DefaultTableModel();
        table = new JTable(model);
        table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        table.setPreferredSize(new Dimension(1100, 650));

        model.addColumn("Mã hoá đơn");
        model.addColumn("Địa điểm");
        model.addColumn("Phí vệ sinh");
        model.addColumn("Thời điểm bắt đầu đóng");
        model.addColumn("Mã hộ khẩu");
        try {
            String URL = "jdbc:postgresql://localhost:5432/QuanLyDanCu";
            Connection connection = DriverManager.getConnection(URL, "postgres", "271203");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ma_hoa_don, dia_diem, phi_ve_sinh, thoi_diem_dong, ma_ho_khau FROM bang_phi WHERE dia_diem = 'Tổ dân phố 7'");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberCol = metaData.getColumnCount();

            while (resultSet.next()) {
                Object[] row = new Object[numberCol];
                for(int col = 1; col <= numberCol; col++) {
                    row[col - 1] = resultSet.getObject(col);
                    table.setRowHeight(50);
                }
                model.addRow(row);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JScrollPane scrollPane = new JScrollPane(table);
        viewPanel.add(scrollPane, BorderLayout.CENTER);
        panelBoard.setLayout(null);
        panelBoard.add(viewPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel
    }

}
