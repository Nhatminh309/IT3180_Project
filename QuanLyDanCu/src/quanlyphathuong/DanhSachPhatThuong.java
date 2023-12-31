package QuanLyDanCu.src.quanlyphathuong;

import QuanLyDanCu.src.giaodien.GiaoDienQuanLy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DanhSachPhatThuong extends GiaoDienQuanLy {
    private static JTable table;
    private static DefaultTableModel model;
    private static JPanel viewPanel;
    private static JLabel viewLabel;
    private static JTextField searchField;
    private static JButton searchButton;
    private static JPanel contentPanel;
    public DanhSachPhatThuong() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBounds(0, 0, 1350, 800);
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
        viewPanel.add(searchPanel, BorderLayout.NORTH);

        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(400, 40));
        searchPanel.add(searchField);

        searchButton = new JButton();
        ImageIcon icon2 = new ImageIcon("QuanLyDanCu/src/icon/searchIcon.png");
        Image img2 = icon2.getImage();
        Image scaledImg2 = img2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImg2);
        searchButton.setIcon(scaledIcon2);
        searchButton.setPreferredSize(new Dimension(100, 40));
        searchPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(contentPanel != null) {
                    viewPanel.remove(contentPanel);
                }
                contentPanel = new JPanel(new BorderLayout());
                viewLabel = new JLabel("Danh sách phần quà của từng hộ gia đình");
                viewLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
                contentPanel.add(viewLabel, BorderLayout.NORTH);

                model = new DefaultTableModel();
                table = new JTable(model);
                table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
                //table.setPreferredSize(new Dimension(1100, 650));

                //print table
                model.addColumn("MHK");
                model.addColumn("Loại quà");
                model.addColumn("Mã phát thưởng");
                model.addColumn("Mã nhân khẩu");
                model.addColumn("Mã cán bộ");
                model.addColumn("Ngày phát");
                model.addColumn("Dịp lễ");
                model.addColumn("Số lượng");
                model.addColumn("Đã xác nhận");
                JTableHeader header = table.getTableHeader();
                header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
                int[] columnWidth = {20, 20, 50, 35, 20, 20, 20, 20, 20};
                for(int i = 0; i < columnWidth.length; i++) {
                    table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
                }

                try {
                    Connection connection = getConnectDatabase();
                    Statement statement = connection.createStatement();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT Ho_khau.Ma_ho_khau, Phan_thuong.loai_phan_thuong, Phat_thuong.ma_phat_thuong, Phat_thuong.ma_nhan_khau, Phat_thuong.ma_can_bo, Phat_thuong.ngay_phat, Phat_thuong.dip_le, Phat_thuong.so_luong, Phat_thuong.da_xac_nhan " +
                            "FROM Phat_thuong " +
                            "JOIN Nhan_khau ON Phat_thuong.Ma_nhan_khau = Nhan_khau.Ma_nhan_khau " +
                            "JOIN Ho_khau ON Nhan_khau.Ma_ho_khau = Ho_khau.Ma_ho_khau " +
                            "JOIN Phan_thuong ON Phat_thuong.ma_phan_thuong = Phan_thuong.Ma_phan_thuong " +
                            "WHERE Phat_thuong.Dip_le = 'Cuối Năm Học' " +
                            "AND EXTRACT(YEAR FROM Phat_thuong.Ngay_phat) = ?");
                    preparedStatement.setInt(1, Integer.parseInt(searchField.getText()));
                    ResultSet resultSet = preparedStatement.executeQuery();
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
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

                JScrollPane scrollPane = new JScrollPane(table);
                contentPanel.add(scrollPane, BorderLayout.CENTER);
                //scrollPane.setMaximumSize(new Dimension(1280, 300));
                viewPanel.add(contentPanel, BorderLayout.CENTER);
                panelBoard.setLayout(null);
                panelBoard.add(viewPanel);
                panelBoard.revalidate(); // Revalidate the panel to display changes
                panelBoard.repaint(); // Repaint the panel
            }
        });
        panelBoard.setLayout(null);
        panelBoard.add(viewPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel
    }
}
