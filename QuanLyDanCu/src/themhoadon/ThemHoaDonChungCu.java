package themhoadon;

import giaodien.GiaoDienQuanLy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;

public class ThemHoaDonChungCu extends GiaoDienQuanLy  {
    private static JTextField maHDField;
    private static JTextField phiQlyField;
    private static JTextField phiDvuField;
    private static JTextField hanDongField;
    private static JTextField maHKField;
    private static JTextField phiInternetField;
    private static JTextField phiDienField;
    private static JTextField phiNuocField;
    private static JTextField phiGuiXeField;
    public ThemHoaDonChungCu() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBounds(0, 0, 1280, 700);

        JPanel backPanel = new JPanel();
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        ImageIcon icon = new ImageIcon("/Users/macbookair/2023.1/nhapmoncnpm/IT3180_Project/QuanLyDanCu/src/icon/backIcon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        backButton.setIcon(scaledIcon);
        backButton.setIconTextGap(10);
        backButton.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        backPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ThemHoaDon();
            }
        });
        viewPanel.add(backPanel, BorderLayout.NORTH);

        JPanel addPanel = new JPanel();
        addPanel.setLayout(null);
        Font newFont = new Font(Font.SANS_SERIF, Font.PLAIN, 24);
        JLabel maHDLabel = new JLabel("Mã hoá đơn:");
        maHDLabel.setFont(newFont);
        JLabel phiQlyLabel = new JLabel("Phí quản lý:");
        phiQlyLabel.setFont(newFont);
        JLabel phiDvuLabel = new JLabel("Phí dịch vụ:");
        phiDvuLabel.setFont(newFont);
        JLabel hanDongLabel = new JLabel("Hạn đóng: ");
        hanDongLabel.setFont(newFont);
        JLabel maHKLabel = new JLabel("Mã hộ khẩu: ");
        maHKLabel.setFont(newFont);
        JLabel phiDienLabel = new JLabel("Phí điện: ");
        phiDienLabel.setFont(newFont);
        JLabel phiNuocLabel = new JLabel("Phí nước: ");
        phiNuocLabel.setFont(newFont);
        JLabel phiInternetLabel = new JLabel("Phí internet: ");
        phiInternetLabel.setFont(newFont);
        JLabel phiGuiXeLabel = new JLabel("Phí gửi xe: ");
        phiGuiXeLabel.setFont(newFont);


        maHDField = new JTextField();
        phiQlyField = new JTextField();
        phiDvuField = new JTextField();
        hanDongField = new JTextField();
        maHKField = new JTextField();
        phiDienField = new JTextField();
        phiNuocField = new JTextField();
        phiInternetField = new JTextField();
        phiGuiXeField = new JTextField();

        addPanel.add(maHDLabel);
        addPanel.add(maHDField);
        addPanel.add(phiQlyLabel);
        addPanel.add(phiQlyField);
        addPanel.add(phiDvuLabel);
        addPanel.add(phiDvuField);
        addPanel.add(hanDongLabel);
        addPanel.add(hanDongField);
        addPanel.add(maHKLabel);
        addPanel.add(maHKField);
        addPanel.add(phiInternetLabel);
        addPanel.add(phiInternetField);
        addPanel.add(phiDienLabel);
        addPanel.add(phiDienField);
        addPanel.add(phiNuocLabel);
        addPanel.add(phiNuocField);
        addPanel.add(phiGuiXeLabel);
        addPanel.add(phiGuiXeField);


        maHDLabel.setBounds(30, 30, 220, 40);
        maHDField.setBounds(250, 30, 700, 40);;
        maHKLabel.setBounds(30, 90, 220, 40);
        maHKField.setBounds(250, 90, 700, 40);
        phiDvuLabel.setBounds(30, 150, 220, 40);
        phiDvuField.setBounds(250, 150, 700, 40);;
        phiQlyLabel.setBounds(30, 210, 220, 40);
        phiQlyField.setBounds(250, 210, 700, 40);
        hanDongLabel.setBounds(30, 270, 220, 40);
        hanDongField.setBounds(250, 270, 700, 40);
        phiDienLabel.setBounds(30, 330, 220, 40);
        phiDienField.setBounds(250, 330, 700, 40);
        phiNuocLabel.setBounds(30, 390, 220, 40);
        phiNuocField.setBounds(250, 390, 700, 40);
        phiInternetLabel.setBounds(30, 450, 220, 40);
        phiInternetField.setBounds(250, 450, 700, 40);
        phiGuiXeLabel.setBounds(30, 510, 220, 40);
        phiGuiXeField.setBounds(250, 510, 700, 40);


        viewPanel.add(addPanel, BorderLayout.CENTER);
        phiQlyField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if(!maHKField.getText().equals("")) {
                    phiQlyField.setText(String.valueOf(calculatePhiQuanLy()));
                }
            }
        });
        phiDvuField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if(!maHKField.getText().equals("")) {
                    phiDvuField.setText(String.valueOf(calculatePhiDichVu()));
                }
            }
        });
        maHKField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if(!maHKField.getText().equals("")) {
                    phiGuiXeField.setText(String.valueOf(calculatePhiGuiXe()));
                }
            }
        });

        JPanel updatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton updateButton = new JButton("Thêm hoá đơn");
        updateButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        updateButton.setPreferredSize(new Dimension(150, 50));
        updatePanel.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHDToSQL();
                //set blank text to field
                maHDField.setText("");
                maHKField.setText("");
                phiDvuField.setText("");
                phiQlyField.setText("");
                hanDongField.setText("");
                phiInternetField.setText("");
                phiDienField.setText("");
                phiNuocField.setText("");
                phiGuiXeField.setText("");
                JOptionPane.showMessageDialog(panelBoard, "Đã thêm hoá đơn thành công");
            }
        });

        viewPanel.add(updatePanel, BorderLayout.SOUTH);


        panelBoard.setLayout(null);
        panelBoard.add(viewPanel);
        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel


    }
    public void addHDToSQL() {
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO bang_phi(ma_ho_khau, dia_diem, phi_qly_chung_cu, phi_dvu_chung_cu, phi_gui_xe, phi_dien, phi_nuoc, phi_internet, thoi_diem_dong,da_xac_nhan, ma_ho_khau) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, maHDField.getText());
            preparedStatement.setString(2, "Chung cư Bluemoon");
            preparedStatement.setInt(3, calculatePhiQuanLy());
            preparedStatement.setInt(4, calculatePhiDichVu());
            preparedStatement.setInt(5, calculatePhiGuiXe());
            preparedStatement.setInt(6, Integer.parseInt(phiDienField.getText()));
            preparedStatement.setInt(7, Integer.parseInt(phiNuocField.getText()));
            preparedStatement.setInt(8, Integer.parseInt(phiInternetField.getText()));
            preparedStatement.setDate(8, Date.valueOf(hanDongField.getText()));
            preparedStatement.setBoolean(9, false);
            preparedStatement.setInt(10, Integer.parseInt(maHKField.getText()));

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int calculatePhiQuanLy() {
        int dienTich = 0;
        int phiQuanLy = 0;
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT dien_tich FROM ho_khau WHERE ma_ho_khau = ?");
            preparedStatement.setInt(1, Integer.parseInt(maHKField.getText()));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                dienTich = resultSet.getInt("dien_tich");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        phiQuanLy = dienTich * Integer.parseInt(phiQlyField.getText());
        return phiQuanLy;
    }
    public int calculatePhiDichVu() {
        int dienTich = 0;
        int phiDichVu = 0;
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT dien_tich FROM ho_khau WHERE ma_ho_khau = ?");
            preparedStatement.setInt(1, Integer.parseInt(maHKField.getText()));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                dienTich = resultSet.getInt("dien_tich");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        phiDichVu = dienTich * Integer.parseInt(phiDvuField.getText());
        return phiDichVu;
    }

    public int calculatePhiGuiXe() {
        int soLuongXeMay = 0;
        int soLuongXeOto = 0;
        int tongTienGuiXe = 0;
        try {
            Connection connection = getConnectDatabase();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT so_luong_xe_may FROM ho_khau WHERE ma_ho_khau = ?");
            preparedStatement1.setInt(1, Integer.parseInt(maHKField.getText()));

            PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT so_luong_o_to FROM ho_khau WHERE ma_ho_khau = ?");
            preparedStatement2.setInt(1, Integer.parseInt(maHKField.getText()));

            ResultSet resultSet1 = preparedStatement1.executeQuery();
            if(resultSet1.next()) {
                soLuongXeMay = resultSet1.getInt("so_luong_xe_may");
            }

            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if(resultSet2.next()) {
                soLuongXeOto = resultSet2.getInt("so_luong_o_to");
            }

            resultSet1.close();
            resultSet2.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tongTienGuiXe = soLuongXeMay*70000 + soLuongXeOto*120000;
        return tongTienGuiXe;
    }
}
