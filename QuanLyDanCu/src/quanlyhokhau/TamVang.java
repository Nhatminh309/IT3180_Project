package quanlyhokhau;
import giaodien.GiaoDienQuanLy;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TamVang extends GiaoDienQuanLy {
    private static JTable table;
    private static DefaultTableModel model;
    private static JLabel tamVangLabel;
    public TamVang() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.setLayout(new BorderLayout());
        panelBoard.removeAll();

        tamVangLabel = new JLabel("Danh sách tạm vắng");
        tamVangLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));
        panelBoard.add(tamVangLabel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        table.setPreferredSize(new Dimension(1100, 650));

        model.addColumn("ID");
        model.addColumn("Mã nhân khẩu");
        model.addColumn("Ngày tạm vắng");
        model.addColumn("Nơi đến");
        model.addColumn("Đã xác nhận");
        int[] columnWidth = {30, 30, 50, 30, 30};
        for(int i = 0; i < columnWidth.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
        }
        try {
            String URL = "jdbc:postgresql://localhost:5432/QuanLyDanCu";
            Connection connection = DriverManager.getConnection(URL, "postgres", "271203");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Tam_vang");
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
        panelBoard.add(scrollPane, BorderLayout.CENTER);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel

    }
}
