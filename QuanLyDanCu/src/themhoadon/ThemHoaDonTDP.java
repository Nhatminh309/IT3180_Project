package QuanLyDanCu.src.themhoadon;

import QuanLyDanCu.src.danhsachhoadon.DanhSachHoaDon;
import QuanLyDanCu.src.giaodien.GiaoDienQuanLy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.time.LocalDate;
import java.util.jar.JarException;

public class ThemHoaDonTDP extends GiaoDienQuanLy {
    private static JTextField maHDField;
    private static JTextField phiVSField;
    private static JTextField hanDongField;
    private static JTextField maHkField;

    public ThemHoaDonTDP() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBounds(0, 0, 1280, 700);

        JPanel backPanel = new JPanel();
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        ImageIcon icon = new ImageIcon("QuanLyDanCu/src/icon/backIcon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        backButton.setIcon(scaledIcon);
        backButton.setIconTextGap(10);
        backButton.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        backPanel.add(backButton);
        backButton.setMaximumSize(new Dimension(200, 40));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ThemHoaDon();
            }
        });
        viewPanel.add(backPanel, BorderLayout.NORTH);

        JPanel addPanel = new JPanel();
        addPanel.setLayout(null);
        Font newFont = new Font(Font.SANS_SERIF, Font.PLAIN, 28);
        JLabel maHDLabel = new JLabel("Mã hoá đơn:");
        maHDLabel.setFont(newFont);
        JLabel phiVSLabel = new JLabel("Phí vệ sinh:");
        phiVSLabel.setFont(newFont);
        JLabel hanDongLabel = new JLabel("Hạn đóng:");
        hanDongLabel.setFont(newFont);
        JLabel maHKLabel = new JLabel("Mã hộ khẩu: ");
        maHKLabel.setFont(newFont);

        maHDField = new JTextField();
        phiVSField = new JTextField();
        hanDongField = new JTextField();
        maHkField = new JTextField();

        addPanel.add(maHDLabel);
        addPanel.add(maHDField);
        addPanel.add(maHKLabel);
        addPanel.add(maHkField);
        addPanel.add(hanDongLabel);
        addPanel.add(hanDongField);
        addPanel.add(phiVSLabel);
        addPanel.add(phiVSField);

        maHDLabel.setBounds(30, 30, 220, 50);
        maHDField.setBounds(250, 30, 700, 50);;
        maHKLabel.setBounds(30, 100, 220, 50);
        maHkField.setBounds(250, 100, 700, 50);
        hanDongLabel.setBounds(30, 170, 220, 50);
        hanDongField.setBounds(250, 170, 700, 50);;
        phiVSLabel.setBounds(30, 240, 220, 50);
        phiVSField.setBounds(250, 240, 700, 50);


        viewPanel.add(addPanel, BorderLayout.CENTER);

        maHkField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if(!maHkField.getText().equals("")) {
                    phiVSField.setText(String.valueOf(calculatePhiVeSinh()));
                }
            }
        });

        JPanel updatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton updateButton = new JButton("Thêm hoá đơn");
        updateButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        updateButton.setPreferredSize(new Dimension(200, 50));
        updatePanel.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHoaDonToSQL();
                //set blank text to field
                addHoaDonToSQL();
                JOptionPane.showMessageDialog(panelBoard, "Đã thêm hoá đơn thành công");
                maHDField.setText("");
                phiVSField.setText("");
                hanDongField.setText("");
                maHkField.setText("");
            }
        });

        viewPanel.add(updatePanel, BorderLayout.SOUTH);

        panelBoard.setLayout(null);
        panelBoard.add(viewPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel

    }
    public void addHoaDonToSQL() {
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO bang_phi(ma_hoa_don, dia_diem, phi_ve_sinh, thoi_diem_dong, da_xac_nhan, ma_ho_khau) VALUES(?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, maHDField.getText());
            preparedStatement.setString(2, "Tổ dân phố 7");
            preparedStatement.setInt(3, calculatePhiVeSinh());
            preparedStatement.setDate(4, Date.valueOf(hanDongField.getText()));
            preparedStatement.setBoolean(5, false);
            preparedStatement.setInt(6, Integer.parseInt(maHkField.getText()));
            preparedStatement.executeQuery();


            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int calculatePhiVeSinh() {
        int tongSoNguoi = 0;
        int phiVeSinh = 0;
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(ma_nhan_khau) AS tong_nguoi FROM nhan_khau WHERE ma_ho_khau = ?");
            preparedStatement.setInt(1, Integer.parseInt(maHkField.getText()));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                tongSoNguoi = resultSet.getInt("tong_nguoi");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        phiVeSinh = tongSoNguoi * 6000;
        return phiVeSinh;
    }
}
