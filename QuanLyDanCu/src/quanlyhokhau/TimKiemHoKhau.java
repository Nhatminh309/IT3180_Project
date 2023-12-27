package quanlyhokhau;

import giaodien.GiaoDienQuanLy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class TimKiemHoKhau extends GiaoDienQuanLy {
    private static JLabel searchLabel;
    private static JTextField searchField;
    private static JButton searchButton;
    private static JPanel searchPanel;
    private static JTable table;
    private static DefaultTableModel model;
    public TimKiemHoKhau() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        searchLabel = new JLabel("Tìm kiếm hộ khẩu theo mã hộ khẩu...");
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
                searchByMaHK(Integer.parseInt(searchField.getText()));
            }
        });

        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel
    }
    public void searchByMaHK(int maHK) {
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
        model.addColumn("Mã hộ khẩu");
        model.addColumn("Số nhà");
        model.addColumn("Tên đường");
        model.addColumn("Tên phường");
        model.addColumn("Tên quận");
        model.addColumn("Tên thành phố");
        model.addColumn("Đã xác nhận");
        model.addColumn("Diện tích");
        model.addColumn("Số xe máy");
        model.addColumn("Số oto");
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int[] columnWidth = {10, 10, 15, 15, 15, 30, 10, 15, 15, 15};
        for(int i = 0; i < columnWidth.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
        }
        try {

            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ho_khau WHERE ma_ho_khau = ?");
            preparedStatement.setInt(1, maHK);
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
                updateHoKhauFromSQL();
                JOptionPane.showMessageDialog(panelBoard, "Cập nhật hộ khẩu thành công");
            }
        });
        updatePanel.add(updateButton);
        searchPanel.add(updatePanel, BorderLayout.SOUTH);

        panelBoard.setLayout(null);
        panelBoard.add(searchPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel
    }
    public void updateHoKhauFromSQL() {
        Map<String, String> columnToAttributeMap = new HashMap<>();
        columnToAttributeMap.put("Mã hộ khẩu", "ma_ho_khau");
        columnToAttributeMap.put("Số nhà", "so_nha");
        columnToAttributeMap.put("Tên đường", "ten_duong");
        columnToAttributeMap.put("Tên phường", "ten_phuong");
        columnToAttributeMap.put("Tên quận", "ten_quan");
        columnToAttributeMap.put("Tên thành phố", "ten_thanh_pho");
        columnToAttributeMap.put("Đã xác nhận", "da_xac_nhan");
        columnToAttributeMap.put("Diện tích", "dien_tich");
        columnToAttributeMap.put("Số xe máy", "so_luong_xe_may");
        columnToAttributeMap.put("Số oto", "so_luong_o_to");

        int[] selectedRows = table.getSelectedRows();

        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();

            for (int i = 0; i < selectedRows.length; i++) {
                int modelRowIndex = table.convertRowIndexToModel(selectedRows[i]); // Convert to model index
                Object idValue = model.getValueAt(modelRowIndex, 0);

                StringBuilder query = new StringBuilder("UPDATE ho_khau SET ");
                for (int j = 0; j < model.getColumnCount(); j++) {
                    String columnName = model.getColumnName(j);
                    String attribute = columnToAttributeMap.get(columnName);
                    Object value = model.getValueAt(modelRowIndex, j);
                    if (attribute != null && !attribute.equals("ma_ho_khau")) {
                        query.append(attribute).append("='").append(value).append("'");
                        if (j < model.getColumnCount() - 1) {
                            query.append(", ");
                        }
                    }
                }
                query.append(" WHERE ma_ho_khau = ").append(idValue);
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
