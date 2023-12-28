package timkiemhoadon;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import danhsachhoadon.DanhSachHoaDon;
public class TimKiemHoaDon extends DanhSachHoaDon{
    private static JLabel searchLabel;
    private static JTextField searchField;
    private static JButton searchButton;
    private static JPanel searchPanel;
    private static JTable table;
    private static DefaultTableModel model;
    public JTextField getSearchField() {
        return searchField;
    }
    public TimKiemHoaDon() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        searchLabel = new JLabel("Tìm kiếm hoá đơn theo mã hộ khẩu...");
        searchLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        searchLabel.setBounds(200, 50, 500, 50);

        searchField = new JTextField(20);
        searchField.setBounds(200, 100, 400, 50);


        searchButton = new JButton();
        ImageIcon icon = new ImageIcon("/Users/macbookair/2023.1/nhapmoncnpm/IT3180_Project/QuanLyDanCu/src/icon/searchIcon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        searchButton.setIcon(scaledIcon);
        searchButton.setBounds(600, 100, 200, 50);


        panelBoard.setLayout(null);
        panelBoard.add(searchLabel);
        panelBoard.add(searchField);
        panelBoard.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByCode(Integer.parseInt(searchField.getText()));
            }
        });

        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel


    }
    public void searchByCode(int code) {
        JPanel panelBoard = getPanelBoard();

        if(searchPanel != null) {
            panelBoard.remove(searchPanel);
        }

        searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBounds(0, 200, 1280, 500);

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        table.setPreferredSize(new Dimension(1250, 650));
        model.addColumn("MHĐ");
        model.addColumn("Địa điểm");
        model.addColumn("Phí vệ sinh");
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
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int[] columnWidth = {10, 50, 50, 50, 50, 30, 50, 30, 40, 50, 50, 5, 10};
        for(int i = 0; i < columnWidth.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
        }
        try {
            //String URL = "jdbc:postgresql://localhost:5432/QuanLyDanCu";
            //Connection connection = DriverManager.getConnection(URL, "postgres", "271203");
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bang_phi WHERE ma_ho_khau = ?");
            preparedStatement.setInt(1, code);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JScrollPane scrollPane = new JScrollPane(table);
        searchPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel updatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton updateButton = new JButton("Cập nhật");
        updateButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        updateButton.setPreferredSize(new Dimension(150, 50));
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHoaDonFromSQL();
                JOptionPane.showMessageDialog(panelBoard, "Cập nhật hoá đơn thành công");
            }
        });
        updatePanel.add(updateButton);
        searchPanel.add(updatePanel, BorderLayout.SOUTH);

        panelBoard.setLayout(null);
        panelBoard.add(searchPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel

    }
    public void updateHoaDonFromSQL() {
        Map<String, String> columnToAttributeMap = new HashMap<>();
        columnToAttributeMap.put("Mã hoá đơn", "ma_hoa_don");
        columnToAttributeMap.put("Địa điểm", "dia_diem");
        columnToAttributeMap.put("Phí vệ sinh", "phi_ve_sinh");
        columnToAttributeMap.put("Phí quản lý", "phi_qly_chung_cu");
        columnToAttributeMap.put("Phí dịch vụ", "phi_dvu_chung_cu");
        columnToAttributeMap.put("Phí góp", "phi_dong_gop");
        columnToAttributeMap.put("Phí gửi xe", "phi_gui_xe");
        columnToAttributeMap.put("Phí điện", "phi_dien");
        columnToAttributeMap.put("Phí nước", "phi_nuoc");
        columnToAttributeMap.put("Phí internet", "phi_internet");
        columnToAttributeMap.put("Hạn đóng", "thoi_diem_dong");
        columnToAttributeMap.put("Đã xác nhận", "da_xac_nhan");
        columnToAttributeMap.put("Mã hộ khẩu", "ma_ho_khau");


        int[] selectedRows = table.getSelectedRows();

        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();

            for (int i = 0; i < selectedRows.length; i++) {
                int modelRowIndex = table.convertRowIndexToModel(selectedRows[i]); // Convert to model index
                Object idValue = model.getValueAt(modelRowIndex, 0);

                StringBuilder query = new StringBuilder("UPDATE bang_phi SET ");
                boolean firstAttribute = true;
                for (int j = 0; j < model.getColumnCount(); j++) {
                    String columnName = model.getColumnName(j);
                    String attribute = columnToAttributeMap.get(columnName);
                    Object value = model.getValueAt(modelRowIndex, j);
                    if(value == null) {
                        value = 0;
                    }
                    if (attribute != null && !attribute.equals("ma_hoa_don")) {
                        if (attribute != null && !attribute.equals("ma_hoa_don")) {
                            if (!firstAttribute) {
                                query.append(", "); // Add a comma if it's not the first attribute
                            } else {
                                firstAttribute = false; // Set the flag to false after adding the first attribute
                            }

                            query.append(attribute).append("='").append(value).append("'");
                        }
                    }
                }
                query.append(" WHERE ma_hoa_don = '").append(idValue.toString().toUpperCase()).append("'");
                System.out.println(query.toString());
                statement.executeUpdate(query.toString());
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
