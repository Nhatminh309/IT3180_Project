package QuanLyDanCu.src.danhsachhoadon;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class ViewDanhSachHoaDon extends DanhSachHoaDon {
    private static JTable table;
    private static DefaultTableModel model;
    private static JPanel viewPanel;
    private static JLabel viewLabel;
    private static JButton backButton;

    public void viewChungCu() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBounds(0, 0, 1280, 700);

        JPanel backPanel = new JPanel();
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Back");
        ImageIcon icon = new ImageIcon("QuanLyDanCu/src/icon/backIcon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        backButton.setIcon(scaledIcon);
        backButton.setIconTextGap(10);
        backButton.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        backPanel.add(backButton);
        viewPanel.add(backPanel, BorderLayout.NORTH);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DanhSachHoaDon();
            }
        });

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        viewLabel = new JLabel("Danh sách hoá đơn chung cư Moon");
        viewLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        contentPanel.add(viewLabel, BorderLayout.NORTH);
        //viewPanel.add(viewLabel, BorderLayout.CENTER);
        //viewPanel.add(Box.createRigidArea(new Dimension(0, 30)));


        model = new DefaultTableModel();
        table = new JTable(model);
        table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        table.setPreferredSize(new Dimension(1100, 650));

        //print table
        model.addColumn("MHĐ");
        model.addColumn("Phí quản lý");
        model.addColumn("Phí dịch vụ");
        model.addColumn("Phí góp");
        model.addColumn("Phí gửi xe");
        model.addColumn("Phí điện");
        model.addColumn("Phí nước");
        model.addColumn("Phí internet");
        model.addColumn("Hạn đóng");
        model.addColumn("Đã xác nhận");
        model.addColumn("MHK");
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        int[] columnWidth = {25, 40, 40, 30, 35, 30, 30, 40, 60, 45, 10};
        for(int i = 0; i < columnWidth.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
        }

        try {
            //String URL = "jdbc:postgresql://localhost:5432/QuanLyDanCu";
            //Connection connection = DriverManager.getConnection(URL, "postgres", "271203");
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ma_hoa_don, phi_qly_chung_cu, phi_dvu_chung_cu, phi_dong_gop, phi_gui_xe, phi_dien, phi_nuoc, phi_internet, thoi_diem_dong,da_xac_nhan, ma_ho_khau FROM bang_phi WHERE dia_diem = 'Chung cư BlueMoon'");
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
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        viewPanel.add(contentPanel, BorderLayout.CENTER);

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

        JPanel backPanel = new JPanel();
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Back");
        ImageIcon icon = new ImageIcon("/Users/macbookair/2023.1/nhapmoncnpm/IT3180_Project/QuanLyDanCu/src/icon/backIcon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        backButton.setIcon(scaledIcon);
        backButton.setIconTextGap(10);
        backButton.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        backPanel.add(backButton);
        viewPanel.add(backPanel, BorderLayout.NORTH);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DanhSachHoaDon();
            }
        });

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        viewLabel = new JLabel("Danh sách hoá đơn tổ dân phố");
        viewLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        contentPanel.add(viewLabel, BorderLayout.NORTH);


        model = new DefaultTableModel();
        table = new JTable(model);
        table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        table.setPreferredSize(new Dimension(1100, 650));

        model.addColumn("Mã hoá đơn");
        model.addColumn("Phí vệ sinh");
        model.addColumn("Hạn đóng");
        model.addColumn("Đã xác nhận");
        model.addColumn("Mã hộ khẩu");
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        try {
            String URL = "jdbc:postgresql://localhost:5432/QuanLyDanCu";
            Connection connection = DriverManager.getConnection(URL, "postgres", "271203");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ma_hoa_don, phi_ve_sinh, thoi_diem_dong, da_xac_nhan, ma_ho_khau FROM bang_phi WHERE dia_diem = 'Tổ dân phố 7'");
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
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        viewPanel.add(contentPanel);
        panelBoard.setLayout(null);
        panelBoard.add(viewPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel
    }

}
