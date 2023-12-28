package quanlyhokhau;

import giaodien.GiaoDienQuanLy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RectangularShape;
import java.sql.*;
import java.time.LocalDate;

public class ThemHoKhau extends GiaoDienQuanLy {
    private static JTextField maHkField;
    private static JTextField soNhaField;
    private static JTextField tenDuongField;
    private static JTextField tenPhuongField;
    private static JTextField tenQuanField;
    private static JTextField tenThanhPhoField;
    private static JTextField dienTichField;
    private static JTextField chuHoField;
    private static JTextField soLuongXeMayField;
    private static JTextField soLuongXeOToField;
    public ThemHoKhau() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBounds(0, 0, 1280, 700);

        JLabel themHoKhauabel = new JLabel("Đăng ký hộ khẩu thường trú");
        themHoKhauabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        viewPanel.add(themHoKhauabel, BorderLayout.NORTH);

        JPanel addPanel = new JPanel();
        addPanel.setLayout(null);
        Font newFont = new Font(Font.SANS_SERIF, Font.PLAIN, 28);
        JLabel maHKLabel = new JLabel("Mã hộ khẩu:");
        maHKLabel.setFont(newFont);
        JLabel soNhaLabel = new JLabel("Số nhà:");
        soNhaLabel.setFont(newFont);
        JLabel tenDuongLabel = new JLabel("Tên đường:");
        tenDuongLabel.setFont(newFont);
        JLabel tenPhuongLabel = new JLabel("Tên phường: ");
        tenPhuongLabel.setFont(newFont);
        JLabel tenQuanLabel = new JLabel("Tên quận: ");
        tenQuanLabel.setFont(newFont);
        JLabel tenThanhPhoLabel = new JLabel("Tên thành phố: ");
        tenThanhPhoLabel.setFont(newFont);
        JLabel dienTichLabel = new JLabel("Diện tích: ");
        dienTichLabel.setFont(newFont);
        JLabel chuHoLabel = new JLabel("Mã chủ hộ: ");
        chuHoLabel.setFont(newFont);
        JLabel soLuongXeMayLabel = new JLabel("Số xe máy:");
        soLuongXeMayLabel.setFont(newFont);
        JLabel soLuongXeOToLabel = new JLabel("Số xe oto:");
        soLuongXeOToLabel.setFont(newFont);

        maHkField = new JTextField();
        soNhaField = new JTextField();
        tenDuongField = new JTextField();
        tenPhuongField = new JTextField();
        tenQuanField = new JTextField();
        tenThanhPhoField = new JTextField();
        dienTichField = new JTextField();
        chuHoField = new JTextField();
        soLuongXeMayField = new JTextField();
        soLuongXeOToField = new JTextField();

        addPanel.add(maHKLabel);
        addPanel.add(maHkField);
        addPanel.add(soNhaLabel);
        addPanel.add(soNhaField);
        addPanel.add(tenDuongLabel);
        addPanel.add(tenDuongField);
        addPanel.add(tenPhuongLabel);
        addPanel.add(tenPhuongField);
        addPanel.add(tenQuanLabel);
        addPanel.add(tenQuanField);
        addPanel.add(tenThanhPhoLabel);
        addPanel.add(tenThanhPhoField);
        addPanel.add(dienTichLabel);
        addPanel.add(dienTichField);
        addPanel.add(chuHoLabel);
        addPanel.add(chuHoField);
        addPanel.add(soLuongXeMayLabel);
        addPanel.add(soLuongXeMayField);
        addPanel.add(soLuongXeOToLabel);
        addPanel.add(soLuongXeOToField);

        maHKLabel.setBounds(30, 30, 220, 50);
        maHkField.setBounds(250, 30, 850, 50);;
        soNhaLabel.setBounds(30, 100, 100, 50);
        soNhaField.setBounds(250, 100, 300, 50);
        tenDuongLabel.setBounds(600, 100, 200, 50);
        tenDuongField.setBounds(800, 100, 300, 50);;
        tenPhuongLabel.setBounds(30, 170, 200, 50);
        tenPhuongField.setBounds(250, 170, 300, 50);
        tenQuanLabel.setBounds(600, 170, 200, 50);
        tenQuanField.setBounds(800, 170, 300, 50);
        tenThanhPhoLabel.setBounds(30, 240, 220, 50);
        tenThanhPhoField.setBounds(250, 240, 850, 50);
        dienTichLabel.setBounds(30, 310, 220, 50);
        dienTichField.setBounds(250, 310, 850, 50);
        chuHoLabel.setBounds(30, 380, 220, 50);
        chuHoField.setBounds(250, 380, 850, 50);
        soLuongXeMayLabel.setBounds(30, 450, 220, 50);
        soLuongXeMayField.setBounds(250, 450, 850, 50);
        soLuongXeOToLabel.setBounds(30, 520, 220, 50);
        soLuongXeOToField.setBounds(250, 520, 850, 50);


        viewPanel.add(addPanel, BorderLayout.CENTER);

        JPanel updatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton updateButton = new JButton("Thêm hộ khẩu");
        updateButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        updateButton.setPreferredSize(new Dimension(150, 50));
        updatePanel.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHKToSQL();
                //set blank text to field
                maHkField.setText("");
                soNhaField.setText("");
                tenDuongField.setText("");
                tenQuanField.setText("");
                tenPhuongField.setText("");
                tenThanhPhoField.setText("");
                dienTichField.setText("");
                chuHoField.setText("");
                soLuongXeMayField.setText("");
                soLuongXeOToField.setText("");
                JOptionPane.showMessageDialog(panelBoard, "Đã thêm hộ khẩu thành công");
            }
        });

        viewPanel.add(updatePanel, BorderLayout.SOUTH);


        panelBoard.setLayout(null);
        panelBoard.add(viewPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel


    }
    public void addHKToSQL() {
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO ho_khau VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement1.setInt(1, Integer.parseInt(maHkField.getText()));
            preparedStatement1.setString(2, soNhaField.getText());
            preparedStatement1.setString(3, tenDuongField.getText());
            preparedStatement1.setString(4, tenPhuongField.getText());
            preparedStatement1.setString(5, tenQuanField.getText());
            preparedStatement1.setString(6, tenThanhPhoField.getText());
            preparedStatement1.setBoolean(7, true);
            preparedStatement1.setInt(8, Integer.parseInt(dienTichField.getText()));
            preparedStatement1.setInt(9, Integer.parseInt(soLuongXeMayField.getText()));
            preparedStatement1.setInt(10, Integer.parseInt(soLuongXeOToField.getText()));

            PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO chu_ho VALUES(?, ?, ?, ?)");
            preparedStatement2.setInt(1, Integer.parseInt(maHkField.getText()));
            preparedStatement2.setInt(2, Integer.parseInt(chuHoField.getText()));
            preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
            preparedStatement2.setBoolean(4, true);

            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
