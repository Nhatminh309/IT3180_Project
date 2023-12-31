package QuanLyDanCu.src.quanlyphathuong;

import QuanLyDanCu.src.giaodien.GiaoDienQuanLy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ThongKePhatThuong extends GiaoDienQuanLy {
    private static JTextField searchField;
    private static JButton searchButton;
    private static JPanel viewPanel;
    private static  JPanel showPanel;
    public ThongKePhatThuong() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        viewPanel = new JPanel();
        viewPanel.setLayout(null);
        viewPanel.setBounds(0, 0, 1350, 800);

        searchField = new JTextField(20);
        searchField.setBounds(200, 100, 400, 50);

        searchButton = new JButton();
        ImageIcon icon = new ImageIcon("QuanLyDanCu/src/icon/searchIcon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        searchButton.setIcon(scaledIcon);
        searchButton.setBounds(600, 100, 200, 50);
        viewPanel.add(searchField);
        viewPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showPanel != null) {
                    viewPanel.remove(showPanel);
                }
                showPanel = new JPanel();
                showPanel.setLayout(null);
                viewPanel.add(showPanel);
                showPanel.setBounds(0, 200, 1280, 600);

                JLabel soLuongLabel = new JLabel("Tổng số lượng phần quà: " + countTongSoLuong());
                JLabel giaTriLabel = new JLabel("Tổng giá trị phần quà: " + countTongGiaTri());
                Font newFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
                soLuongLabel.setFont(newFont);
                giaTriLabel.setFont(newFont);

                showPanel.add(soLuongLabel);
                showPanel.add(giaTriLabel);

                soLuongLabel.setBounds(200, 0, 400, 50);;
                giaTriLabel.setBounds(200, 50, 400, 50);

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
    public int countTongSoLuong() {
        int total = 0;
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("select sum(Phat_thuong.So_luong) as Tong_so_luong " +
                    "from Phat_thuong " +
                    "join Phan_thuong on Phat_thuong.ma_phan_thuong = Phan_thuong.ma_phan_thuong " +
                    "where extract(YEAR FROM Phat_thuong.Ngay_phat) = ? " +
                    "AND Phat_thuong.Dip_le = 'Cuối Năm Học'");
            preparedStatement.setInt(1, Integer.parseInt(searchField.getText()));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                total = resultSet.getInt("Tong_so_luong");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    public int countTongGiaTri() {
        int total = 0;
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("select sum(Phat_thuong.So_luong * Phan_thuong.Gia_tri) as Tong_gia_tri " +
                    "from Phat_thuong " +
                    "join Phan_thuong on Phat_thuong.ma_phan_thuong = Phan_thuong.ma_phan_thuong " +
                    "where extract(YEAR FROM Phat_thuong.Ngay_phat) = ? " +
                    "AND Phat_thuong.Dip_le = 'Cuối Năm Học'");
            preparedStatement.setInt(1, Integer.parseInt(searchField.getText()));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                total = resultSet.getInt("Tong_gia_tri");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}
