package thongkethuphi;

import danhsachhoadon.DanhSachHoaDon;
import giaodien.GiaoDienQuanLy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ThongKeTDP extends GiaoDienQuanLy {
    private static JTable table;
    private static DefaultTableModel model;
    private static JPanel viewPanel;
    private static JButton backButton;
    private static JTextField searchField;
    private static JButton searchButton;
    private static JPanel sumPanel;
    private static JPanel thongKePanel;
    public ThongKeTDP() {
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
                new ThongKeThuPhi();
            }
        });

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(1280, 650));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
        contentPanel.add(searchPanel, BorderLayout.NORTH);

        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(400, 40));
        searchPanel.add(searchField);

        searchButton = new JButton();
        ImageIcon icon2 = new ImageIcon("/Users/macbookair/2023.1/nhapmoncnpm/IT3180_Project/QuanLyDanCu/src/icon/searchIcon.png");
        Image img2 = icon2.getImage();
        Image scaledImg2 = img2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImg2);
        searchButton.setIcon(scaledIcon2);
        searchButton.setPreferredSize(new Dimension(100, 40));
        searchPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(thongKePanel != null) {
                    contentPanel.remove(thongKePanel);
                }
                thongKePanel = new JPanel(new BorderLayout());
                contentPanel.add(thongKePanel, BorderLayout.CENTER);
                //thongKePanel.setPreferredSize(new Dimension(1280, 500));

                sumPanel = new JPanel();
                sumPanel.setPreferredSize(new Dimension(1280, 100));
                sumPanel.setLayout(new BoxLayout(sumPanel, BoxLayout.Y_AXIS));
                int month = 0, year = 0;
                if(!searchField.getText().equals("")) {
                    String[] splitDate = searchField.getText().split("/");
                    month = Integer.parseInt(splitDate[0]);
                    year = Integer.parseInt(splitDate[1]);
                }
                JLabel sumMoneyLabel = new JLabel("Tổng số tiền phí vệ sinh trong tháng " + month  + " năm "+ year + " là: " + countSumFee());
                JLabel sumDongGopLabel = new JLabel("Tổng số tiền đóng góp trong tháng " + month + " năm " + year + " là: " + countSumDongGop());
                JLabel sumHKLabel = new JLabel("Tổng số hộ gia đình đã đóng là: "+ countSumHK());

                sumMoneyLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
                sumDongGopLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
                sumHKLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
                sumMoneyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                sumHKLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                sumPanel.add(sumMoneyLabel);
                sumPanel.add(sumDongGopLabel);
                sumPanel.add(sumHKLabel);

                sumMoneyLabel.setMaximumSize(new Dimension(600, 40));
                sumDongGopLabel.setMaximumSize(new Dimension(600, 40));
                sumHKLabel.setMaximumSize(new Dimension(600, 40));

                thongKePanel.add(sumPanel, BorderLayout.NORTH);

                model = new DefaultTableModel();
                table = new JTable(model);
                table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
                //table.setPreferredSize(new Dimension(1100, 500));

                model.addColumn("Mã hoá đơn");
                model.addColumn("Phí vệ sinh");
                model.addColumn("Phí đóng góp");
                model.addColumn("Hạn đóng");
                model.addColumn("Đã xác nhận");
                model.addColumn("Mã hộ khẩu");
                JTableHeader header = table.getTableHeader();
                header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
                try {
                    Connection connection = getConnectDatabase();
                    Statement statement = connection.createStatement();
                    String startDate = year + "-" + month + "-" + "1";
                    String endDate = year + "-" + month + "-" + "31";
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT ma_hoa_don, phi_ve_sinh,phi_dong_gop, thoi_diem_dong, da_xac_nhan, ma_ho_khau FROM bang_phi WHERE thoi_diem_dong >= ? AND thoi_diem_dong < ? AND dia_diem = ? AND da_xac_nhan = true");
                    preparedStatement.setDate(1, Date.valueOf(startDate));
                    preparedStatement.setDate(2, Date.valueOf(endDate));
                    preparedStatement.setString(3, "Tổ dân phố 7");
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
                thongKePanel.add(scrollPane, BorderLayout.CENTER);
                scrollPane.setMaximumSize(new Dimension(1280, 300));

                viewPanel.add(contentPanel, BorderLayout.CENTER);
                panelBoard.setLayout(null);
                panelBoard.add(viewPanel);
                panelBoard.revalidate(); // Revalidate the panel to display changes
                panelBoard.repaint(); // Repaint the panel
            }
        });
        viewPanel.add(contentPanel, BorderLayout.CENTER);
        panelBoard.setLayout(null);
        panelBoard.add(viewPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel
    }
    public int countSumFee() {
        int total = 0;
        int month = 0, year = 0;
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();
            if(!searchField.getText().equals("")) {
                String[] splitDate = searchField.getText().split("/");
                month = Integer.parseInt(splitDate[0]);
                year = Integer.parseInt(splitDate[1]);
            }
            String startDate = year + "-" + month + "-" + "1";
            String endDate = year + "-" + month + "-" + "31";
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(phi_ve_sinh) AS total FROM bang_phi WHERE thoi_diem_dong >= ? AND thoi_diem_dong < ? AND dia_diem = ? AND da_xac_nhan = true");
            preparedStatement.setDate(1, Date.valueOf(startDate));
            preparedStatement.setDate(2, Date.valueOf(endDate));
            preparedStatement.setString(3, "Tổ dân phố 7");

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                total = resultSet.getInt("total");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    public int countSumDongGop() {
        int total = 0;
        int month = 0, year = 0;
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();
            if(!searchField.getText().equals("")) {
                String[] splitDate = searchField.getText().split("/");
                month = Integer.parseInt(splitDate[0]);
                year = Integer.parseInt(splitDate[1]);
            }
            String startDate = year + "-" + month + "-" + "1";
            String endDate = year + "-" + month + "-" + "31";
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(phi_dong_gop) AS total FROM bang_phi WHERE thoi_diem_dong >= ? AND thoi_diem_dong < ? AND dia_diem = ? AND da_xac_nhan = true");
            preparedStatement.setDate(1, Date.valueOf(startDate));
            preparedStatement.setDate(2, Date.valueOf(endDate));
            preparedStatement.setString(3, "Tổ dân phố 7");

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                total = resultSet.getInt("total");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    public int countSumHK() {
        int totalHK = 0;
        int month = 0, year = 0;
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();
            if(!searchField.getText().equals("")) {
                String[] splitDate = searchField.getText().split("/");
                month = Integer.parseInt(splitDate[0]);
                year = Integer.parseInt(splitDate[1]);
            }
            String startDate = year + "-" + month + "-" + "1";
            String endDate = year + "-" + month + "-" + "31";
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(ma_hoa_don) AS total FROM bang_phi WHERE thoi_diem_dong >= ? AND thoi_diem_dong < ? AND dia_diem = ? AND da_xac_nhan = true");
            preparedStatement.setDate(1, Date.valueOf(startDate));
            preparedStatement.setDate(2, Date.valueOf(endDate));
            preparedStatement.setString(3, "Tổ dân phố 7");

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                totalHK = resultSet.getInt("total");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalHK;
    }
}
