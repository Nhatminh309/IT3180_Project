package quanlyhokhau;

import giaodien.GiaoDienQuanLy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

public class DangKyTamTru extends GiaoDienQuanLy {
    private static JTextField idField;
    private static JTextField diaChiField;
    private static JTextField thoiHanField;
    private static JTextField ngayDKField;
    private static JTextField maNKField;
    public DangKyTamTru() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBounds(0, 0, 1280, 700);

        JLabel themTamTruLabel = new JLabel("Đăng ký tạm trú trực tuyến");
        themTamTruLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        viewPanel.add(themTamTruLabel, BorderLayout.NORTH);

        JPanel addPanel = new JPanel();
        addPanel.setLayout(null);
        Font newFont = new Font(Font.SANS_SERIF, Font.PLAIN, 28);
        JLabel idLabel = new JLabel("Nhập id: ");
        JLabel diaChiLabel = new JLabel("Nhập địa chỉ: ");
        JLabel ngayDKLabel = new JLabel("Nhập ngày đăng ký: ");
        JLabel thoiHanLabel = new JLabel("Nhập ngày hết hạn: ");
        JLabel maNKLabel = new JLabel("Nhập mã nhân khẩu: ");

        idLabel.setFont(newFont);
        diaChiLabel.setFont(newFont);
        ngayDKLabel.setFont(newFont);
        thoiHanLabel.setFont(newFont);
        maNKLabel.setFont(newFont);

        idField = new JTextField();
        diaChiField = new JTextField();
        ngayDKField = new JTextField();
        thoiHanField = new JTextField();
        maNKField = new JTextField();

        addPanel.add(idLabel);
        addPanel.add(idField);
        addPanel.add(diaChiLabel);
        addPanel.add(diaChiField);
        addPanel.add(ngayDKLabel);
        addPanel.add(ngayDKField);
        addPanel.add(thoiHanLabel);
        addPanel.add(thoiHanField);
        addPanel.add(maNKLabel);
        addPanel.add(maNKField);

        idLabel.setBounds(30, 30, 300, 50);
        idField.setBounds(330, 30, 700, 50);;
        diaChiLabel.setBounds(30, 100, 300, 50);
        diaChiField.setBounds(330, 100, 700, 50);
        ngayDKLabel.setBounds(30, 170, 300, 50);
        ngayDKField.setBounds(330, 170, 700, 50);;
        thoiHanLabel.setBounds(30, 240, 300, 50);
        thoiHanField.setBounds(330, 240, 700, 50);
        maNKLabel.setBounds(30, 310, 300, 50);
        maNKField.setBounds(330, 310, 700, 50);

        viewPanel.add(addPanel, BorderLayout.CENTER);

        JPanel updatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton updateButton = new JButton("Thêm tạm trú");
        updateButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        updateButton.setPreferredSize(new Dimension(150, 50));
        updatePanel.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTamTru();
                //set blank text to field
                idField.setText("");
                diaChiField.setText("");
                ngayDKField.setText("");
                thoiHanField.setText("");
                maNKField.setText("");
                JOptionPane.showMessageDialog(panelBoard, "Đã thêm tạm trú thành công");
            }
        });

        viewPanel.add(updatePanel, BorderLayout.SOUTH);


        panelBoard.setLayout(null);
        panelBoard.add(viewPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel

    }
    public void addTamTru() {
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO so_tam_tru VALUES(?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
            preparedStatement.setString(2, diaChiField.getText());
            preparedStatement.setDate(3, Date.valueOf(ngayDKField.getText()));
            preparedStatement.setDate(4, Date.valueOf(thoiHanField.getText()));
            preparedStatement.setInt(5, Integer.parseInt(maNKField.getText()));
            preparedStatement.setBoolean(6, true);

            preparedStatement.executeQuery();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
