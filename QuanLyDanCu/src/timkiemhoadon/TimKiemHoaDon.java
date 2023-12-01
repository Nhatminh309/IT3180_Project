package timkiemhoadon;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
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
        searchPanel.setBounds(0, 200, 1280, 700);

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        table.setPreferredSize(new Dimension(1250, 650));
        model.addColumn("Mã hoá đơn");
        model.addColumn("Địa điểm");
        model.addColumn("Phí vệ sinh");
        model.addColumn("Phí quản lý");
        model.addColumn("Phí dịch vụ");
        model.addColumn("Phí đóng góp");
        model.addColumn("Thời điểm bắt đầu đóng");
        model.addColumn("Đã xác nhận");
        model.addColumn("Mã hộ khẩu");

        try {
            String URL = "jdbc:postgresql://localhost:5432/QuanLyDanCu";
            Connection connection = DriverManager.getConnection(URL, "postgres", "271203");
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
        panelBoard.setLayout(null);
        panelBoard.add(searchPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel

    }
}
