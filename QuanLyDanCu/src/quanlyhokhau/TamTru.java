package QuanLyDanCu.src.quanlyhokhau;

import QuanLyDanCu.src.giaodien.GiaoDienQuanLy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class TamTru extends GiaoDienQuanLy {
    private static JTable table;
    private static DefaultTableModel model;
    private static JLabel tamTruLabel;
    public TamTru() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBounds(0, 0, 1280, 700);

        tamTruLabel = new JLabel("Danh sách tạm trú");
        tamTruLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        viewPanel.add(tamTruLabel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        //table.setPreferredSize(new Dimension(1100, 650));

        model.addColumn("ID");
        model.addColumn("Địa chỉ thường trú");
        model.addColumn("Ngày đăng ký");
        model.addColumn("Thời hạn");
        model.addColumn("Mã nhân khẩu");
        model.addColumn("Đã xác nhận");
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));

        int[] columnWidth = {5, 350, 30, 30, 10, 10};
        for(int i = 0; i < columnWidth.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
        }
        try {
            //String URL = "jdbc:postgresql://localhost:5432/QuanLyDanCu";
            //Connection connection = DriverManager.getConnection(URL, "postgres", "271203");
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM so_tam_tru");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberCol = metaData.getColumnCount();

            while (resultSet.next()) {
                Object[] row = new Object[numberCol];
                for(int col = 1; col <= numberCol; col++) {
                    row[col - 1] = resultSet.getObject(col);
                    table.setRowHeight(30);
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

        JPanel updatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton updateButton = new JButton("Cập nhật");
        updateButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        updateButton.setPreferredSize(new Dimension(150, 50));
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSQLFromTable();
                JOptionPane.showMessageDialog(panelBoard, "Cập nhật thông tin thành công");
            }
        });
        updatePanel.add(updateButton);
        viewPanel.add(updatePanel, BorderLayout.SOUTH);

        panelBoard.setLayout(null);
        panelBoard.add(viewPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel

    }
    public void updateSQLFromTable() {

        Map<String, String> columnToAttributeMap = new HashMap<>();
        columnToAttributeMap.put("ID", "id");
        columnToAttributeMap.put("Địa chỉ thường trú", "dia_chi_thuong_tru");
        columnToAttributeMap.put("Ngày đăng ký", "ngay_dang_ky");
        columnToAttributeMap.put("Thời hạn", "thoi_han");
        columnToAttributeMap.put("Mã nhân khẩu", "ma_nhan_khau");
        columnToAttributeMap.put("Đã xác nhận", "da_xac_nhan");

        int[] selectedRows = table.getSelectedRows();

        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();

            for (int i = 0; i < selectedRows.length; i++) {
                int modelRowIndex = table.convertRowIndexToModel(selectedRows[i]); // Convert to model index
                Object idValue = model.getValueAt(modelRowIndex, 0);

                StringBuilder query = new StringBuilder("UPDATE so_tam_tru SET ");
                for (int j = 0; j < model.getColumnCount(); j++) {
                    String columnName = model.getColumnName(j);
                    String attribute = columnToAttributeMap.get(columnName);
                    Object value = model.getValueAt(modelRowIndex, j);
                    if (attribute != null && !attribute.equals("id")) {
                        query.append(attribute).append("='").append(value).append("'");
                        if (j < model.getColumnCount() - 1) {
                            query.append(", ");
                        }
                    }
                }
                query.append(" WHERE id = ").append(idValue);
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
