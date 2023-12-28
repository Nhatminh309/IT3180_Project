package quanlyhokhau;

import giaodien.GiaoDienQuanLy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DangKyTamVang extends GiaoDienQuanLy {
    private static JTextField idField;
    private static JTextField maNKField;
    private static JTextField ngayTamVangField;
    private static JTextField noiDenField;
    public DangKyTamVang() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBounds(0, 0, 1280, 700);

        JLabel themTamVangLabel = new JLabel("Đăng ký tạm vắng trực tuyến");
        themTamVangLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        viewPanel.add(themTamVangLabel, BorderLayout.NORTH);

        JPanel addPanel = new JPanel();
        addPanel.setLayout(null);
        Font newFont = new Font(Font.SANS_SERIF, Font.PLAIN, 28);
        JLabel idLabel = new JLabel("Nhập id: ");
        JLabel maNKLabel = new JLabel("Nhập mã nhân khẩu: ");
        JLabel ngayTamVangLabel = new JLabel("Nhập ngày tạm vắng: ");
        JLabel noiDenLabel = new JLabel("Nhập nơi đến: ");

        idLabel.setFont(newFont);
        maNKLabel.setFont(newFont);
        ngayTamVangLabel.setFont(newFont);
        noiDenLabel.setFont(newFont);

        idField = new JTextField();
        maNKField = new JTextField();
        ngayTamVangField = new JTextField();
        noiDenField = new JTextField();

        addPanel.add(idLabel);
        addPanel.add(idField);
        addPanel.add(maNKLabel);
        addPanel.add(maNKField);
        addPanel.add(ngayTamVangLabel);
        addPanel.add(ngayTamVangField);
        addPanel.add(noiDenLabel);
        addPanel.add(noiDenField);

        idLabel.setBounds(30, 30, 300, 50);
        idField.setBounds(330, 30, 700, 50);;
        maNKLabel.setBounds(30, 100, 300, 50);
        maNKField.setBounds(330, 100, 700, 50);
        ngayTamVangLabel.setBounds(30, 170, 300, 50);
        ngayTamVangField.setBounds(330, 170, 700, 50);;
        noiDenLabel.setBounds(30, 240, 300, 50);
        noiDenField.setBounds(330, 240, 700, 50);

        viewPanel.add(addPanel, BorderLayout.CENTER);

        JPanel updatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton updateButton = new JButton("Thêm tạm vắng");
        updateButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        updateButton.setPreferredSize(new Dimension(200, 50));
        updatePanel.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTamVang();
                //set blank text to field
                idField.setText("");
                maNKField.setText("");
                ngayTamVangField.setText("");
                noiDenField.setText("");
                JOptionPane.showMessageDialog(panelBoard, "Đã thêm tạm vắng thành công");
            }
        });

        viewPanel.add(updatePanel, BorderLayout.SOUTH);


        panelBoard.setLayout(null);
        panelBoard.add(viewPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel
    }
    public void addTamVang() {
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tam_vang VALUES(?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, Integer.parseInt(idField.getText()));
            preparedStatement.setInt(2, Integer.parseInt(maNKField.getText()));
            preparedStatement.setDate(3, Date.valueOf(ngayTamVangField.getText()));
            preparedStatement.setString(4, noiDenField.getText());
            preparedStatement.setBoolean(5, true);

            preparedStatement.executeQuery();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
