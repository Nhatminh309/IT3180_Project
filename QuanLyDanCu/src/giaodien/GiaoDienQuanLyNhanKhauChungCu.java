package QuanLyDanCu.src.giaodien;

import QuanLyDanCu.src.connect.ConnectDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class GiaoDienQuanLyNhanKhauChungCu extends GiaoDienDangNhapChungCu{
    private static JTable table;
    private static DefaultTableModel model;
    public GiaoDienQuanLyNhanKhauChungCu (String taiKhoan) {
        super(taiKhoan);
        northPanel.removeAll();
        JLabel title = new JLabel("QUẢN LÝ NHÂN KHẨU") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(mainPanel.getWidth(), mainPanel.getHeight() / 6);
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, mainPanel.getHeight() / 20);
            }
        };
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.BLACK);
        northPanel.add(title, BorderLayout.CENTER);

        centerPanel.remove(mainButtonPanel);
        centerPanel.setBackground(Color.WHITE);
        JLabel subTitle = new JLabel("Chung cư BlueMoon") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(mainPanel.getWidth(), mainPanel.getHeight() / 8);
            }
            public Font getFont() {
                return new Font("Arial", Font.PLAIN, mainPanel.getHeight() / 25);
            }
        };
        subTitle.setHorizontalAlignment(JLabel.CENTER);
        subTitle.setForeground(Color.decode("#0097B2"));
        northPanel.add(subTitle, BorderLayout.SOUTH);

        model = new DefaultTableModel();
        table = new JTable(model);
        frame.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {

                int width = frame.getWidth()*2/7;
                int height = frame.getHeight()/20;
                int gap = frame.getHeight()/20;
                int X_button = (frame.getWidth() - width*2)/2;
                int Y_button = frame.getHeight()/40;
//                searchLabel.setBounds(X_button, Y_button, width*2, height);
                Y_button += gap;
//                searchField.setBounds(X_button, Y_button, width*3/2, height);
//                searchButton.setBounds(X_button + searchField.getWidth(), Y_button, width*2/7, height);
                Y_button += gap;

                model = new DefaultTableModel();
                table = new JTable(model);
                table.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/80));
                table.setBounds(0, Y_button, frame.getWidth(), frame.getHeight()- Y_button);
                model.addColumn("Mã nhân khẩu");
                model.addColumn("Họ và tên");
                model.addColumn("Ngày sinh");
                model.addColumn("Giới tính");
                model.addColumn("Nơi sinh");
                model.addColumn("Quê quán");
                model.addColumn("Dân tộc");
                model.addColumn("Tôn giáo");
                model.addColumn("Nghề nghiệp");
                model.addColumn("Nơi làm việc");
                model.addColumn("Ngày đăng ký");
                model.addColumn("Căn cước");
                model.addColumn("Số điện thoại");
                model.addColumn("Quan hệ");
                model.addColumn("Mã hộ khẩu");
                model.addColumn("Xác nhận");
                try {
                    Connection connection = getConnectDatabase();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM nhan_khau");
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

                    preparedStatement.close();
                    connection.close();
                } catch(SQLException event) {
                    event.printStackTrace();
                }
                JScrollPane scrollPane = new JScrollPane(table);
                centerPanel.add(scrollPane);
            }
        });
        frame.revalidate();
        frame.repaint();
    }
    public static void main(String[] args) {
        new GiaoDienQuanLyNhanKhauChungCu("admin");
    }
}
