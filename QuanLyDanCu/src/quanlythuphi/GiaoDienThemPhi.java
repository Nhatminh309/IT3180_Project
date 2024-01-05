package QuanLyDanCu.src.quanlythuphi;

import QuanLyDanCu.src.connect.ConnectDatabase;
import QuanLyDanCu.src.dangnhap.DangNhap;
import QuanLyDanCu.src.giaodien.GiaoDienBanDau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GiaoDienThemPhi extends ConnectDatabase {
    private static JPanel topPanel;
    private static JPanel mainPanel;
    private static JFrame frame;
    private static JLabel phiQuanLyChungCuLabel;
    private static JTextField phiQuanLyChungCuField;
    private static JLabel phiDichVuChungCuLabel;
    private static JTextField phiDichVuChungCuField;
    private static JLabel phiGuiXeMayLabel;
    private static JTextField phiGuiXeMayField;
    private static JLabel phiGuiOtoLabel;
    private static JTextField phiGuiOtoField;
    private static JLabel dinhDangLabel;
    private static JLabel taiKhoanLabel;
    private static JTextField taiKhoanField;
    private static JLabel matKhauLabel;
    private static JTextField matKhauField;
    private static JLabel nhapMatKhauLabel;
    private static JTextField nhapMatKhauField;
    private static JButton dangKyButton;
    private static JLabel phiDienLabel;
    private static JTextField phiDienField;
    private static JLabel phiNuocLabel;
    private static JTextField phiNuocField;
    private static JLabel phiInternetLabel;
    private static JTextField phiInternetField;
    public GiaoDienThemPhi() {
        frame = new JFrame("Đăng ký");
        frame.setSize(1000, 578);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.getContentPane().setLayout(new BorderLayout());

        topPanel = new JPanel();
        //topPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Remove any border/margin
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton();
        backButton.setBackground(Color.WHITE);
        ImageIcon icon = new ImageIcon("QuanLyDanCu/src/icon/goBackIcon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(frame.getWidth()/20, frame.getHeight()/19, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        backButton.setIcon(scaledIcon);
        backButton.setBorder(null);
        backButton.setBorderPainted(false);
        backButton.setFocusable(false);
        backButton.setContentAreaFilled(false);

        topPanel.add(backButton, BorderLayout.WEST);

        JLabel dangNhapLabel = new JLabel("Thêm hóa đơn");
        dangNhapLabel.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/20));
        dangNhapLabel.setHorizontalAlignment(SwingConstants.CENTER); // Align label to the center
        dangNhapLabel.setForeground(Color.decode("#38B6FF"));
        topPanel.add(dangNhapLabel, BorderLayout.CENTER);

        //Main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        phiQuanLyChungCuLabel = new JLabel("Phí quản lý (*): ");
        phiQuanLyChungCuField = new JTextField();
        phiDichVuChungCuLabel = new JLabel("Phí dịch vụ (*): ");
        phiDichVuChungCuField = new JTextField();
        phiGuiXeMayLabel = new JLabel("Phí gửi xe máy: ");
        phiGuiXeMayField = new JTextField();
        setPlaceholder(phiGuiXeMayField, "70000");
        phiGuiOtoLabel = new JLabel("Phí gửi ô tô: ");
        phiGuiOtoField = new JTextField();
        setPlaceholder(phiGuiOtoField, "1000000");
        taiKhoanLabel = new JLabel("Mã hộ khẩu:");
        taiKhoanField = new JTextField();
        setPlaceholder(taiKhoanField, "Để trống để tự động gửi tới tất cả hộ khẩu");
        matKhauLabel = new JLabel("Hạn đóng: (*)");
        matKhauField = new JTextField();
        setPlaceholder(matKhauField, "YYYY-MM-DD");
        nhapMatKhauLabel = new JLabel("Nhập lại mật khẩu:");
        nhapMatKhauField = new JPasswordField();
        dinhDangLabel = new JLabel("Định dạng: YYYY-MM-DD");
        dangKyButton = new JButton("Thêm hóa đơn");
        phiDienLabel = new JLabel("Phí điện: (*) ");
        phiDienField = new JTextField();
        phiNuocLabel = new JLabel("Phí nước: (*)");
        phiNuocField = new JTextField();
        phiInternetLabel = new JLabel("Phí internet: (*)");
        phiInternetField = new JTextField();

        //Set icon for dinhDangLabel
        ImageIcon icon2 = new ImageIcon("QuanLyDanCu/src/icon/aboutIcon.png");
        Image img2 = icon2.getImage();
        Image scaledImg2 = img2.getScaledInstance(frame.getWidth()/50, frame.getWidth()/50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImg2);
        dinhDangLabel.setIcon(scaledIcon2);
        dinhDangLabel.setIconTextGap(10);

        Font newFont = new Font("Arial", Font.PLAIN, frame.getWidth()/45);
        phiQuanLyChungCuLabel.setFont(newFont);
        phiDichVuChungCuLabel.setFont(newFont);
        phiGuiXeMayLabel.setFont(newFont);
        phiGuiOtoLabel.setFont(newFont);
        taiKhoanLabel.setFont(newFont);
        matKhauLabel.setFont(newFont);
        phiDienLabel.setFont(newFont);
        phiNuocLabel.setFont(newFont);
        phiInternetLabel.setFont(newFont);
        dinhDangLabel.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/55));
        dangKyButton.setFont(newFont);
        dangKyButton.setBackground(Color.decode("#38B6FF"));
        dangKyButton.setOpaque(true);
        dangKyButton.setBorder(BorderFactory.createEmptyBorder());

//        dangNhapPanel = new JPanel();
//        dangNhapPanel.setLayout(new BoxLayout(dangNhapPanel, BoxLayout.X_AXIS));
//        dangNhapPanel.setBorder(BorderFactory.createEmptyBorder());
//        dangNhapPanel.setOpaque(false);
        ImageIcon icon3 = new ImageIcon("QuanLyDanCu/src/icon/aboutIcon.png");
        Image img3 = icon3.getImage();
        Image scaledImg3 = img3.getScaledInstance(frame.getWidth()/50, frame.getHeight()/40, Image.SCALE_SMOOTH);

        JLabel askLabel = new JLabel("Đơn vị là VND/m2/tháng đối với các phí quản lý và dịch vụ, VND/xe/tháng đối với phí gửi xe");
        askLabel.setFont(new Font("Arial", Font.PLAIN, frame.getWidth()/60));
        askLabel.setIcon(new ImageIcon(scaledImg2));
        //Add popMenu to phiGuiXeMayField
        JPopupMenu popupMenu1 = new JPopupMenu();
        phiGuiXeMayField.add(popupMenu1);
        phiGuiXeMayField.setComponentPopupMenu(popupMenu1);



        mainPanel.add(phiQuanLyChungCuLabel);
        mainPanel.add(phiQuanLyChungCuField);
        mainPanel.add(phiDichVuChungCuLabel);
        mainPanel.add(phiDichVuChungCuField);
        mainPanel.add(dinhDangLabel);
        mainPanel.add(phiGuiXeMayLabel);
        mainPanel.add(phiGuiXeMayField);
        mainPanel.add(phiGuiOtoLabel);
        mainPanel.add(phiGuiOtoField);
        mainPanel.add(phiDienLabel);
        mainPanel.add(phiDienField);
        mainPanel.add(phiNuocLabel);
        mainPanel.add(phiNuocField);
        mainPanel.add(phiInternetLabel);
        mainPanel.add(phiInternetField);
        mainPanel.add(taiKhoanLabel);
        mainPanel.add(taiKhoanField);
        mainPanel.add(matKhauLabel);
        mainPanel.add(matKhauField);
        mainPanel.add(dangKyButton);
        mainPanel.add(askLabel);


        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth()*2/5;
                int height = frame.getHeight()/15;
                int X_left = (frame.getWidth() - width*2)/2;
                int Y_left = frame.getHeight()/20;
                int gap = frame.getHeight()/17;
                //Line 1
                phiQuanLyChungCuLabel.setBounds(X_left, Y_left, width, height);
                phiDichVuChungCuLabel.setBounds(X_left + phiQuanLyChungCuLabel.getWidth(), Y_left, width, height);
                Y_left += gap;
                //Line 2
                phiQuanLyChungCuField.setBounds(X_left, Y_left, width, height);
                phiDichVuChungCuField.setBounds(X_left + phiQuanLyChungCuField.getWidth(), Y_left, width, height);
                Y_left += gap;
                //Line 3
                phiGuiXeMayLabel.setBounds(X_left, Y_left, width, height);
                phiGuiOtoLabel.setBounds(X_left + phiGuiXeMayLabel.getWidth(), Y_left, width, height);
                Y_left += gap;
                //Line 4
                phiGuiXeMayField.setBounds(X_left, Y_left, width, height);
                phiGuiOtoField.setBounds(X_left + phiGuiXeMayField.getWidth(), Y_left, width, height);
                Y_left += gap;
                //Line 5
                phiDienLabel.setBounds(X_left, Y_left, width * 2 / 3 , height);
                phiNuocLabel.setBounds(X_left + phiDienLabel.getWidth(), Y_left, width * 2 / 3, height);
                phiInternetLabel.setBounds(X_left + phiDienLabel.getWidth() + phiInternetLabel.getWidth(), Y_left, width * 2 / 3, height);
                Y_left += gap;
                //Line 6
                phiDienField.setBounds(X_left, Y_left, width * 2 / 3, height);
                phiNuocField.setBounds(X_left + phiDienField.getWidth(), Y_left, width * 2 / 3, height);
                phiInternetField.setBounds(X_left + phiInternetLabel.getWidth() + phiInternetLabel.getWidth(), Y_left, width* 2 / 3, height);
                Y_left += gap;
                //Line 7
                taiKhoanLabel.setBounds(X_left, Y_left, width, height);
                Y_left += gap;
                //Line 6
                taiKhoanField.setBounds(X_left, Y_left, width*2, height);
                Y_left += gap;
                //Line 7
                matKhauLabel.setBounds(X_left, Y_left, width, height);
                Y_left += gap;
                //Line 8
                matKhauField.setBounds(X_left, Y_left, width*2, height);
                Y_left += gap;
                askLabel.setBounds(X_left, Y_left, width * 2, height);
                Y_left += gap;

                int Y_dinhDang = Y_left;

                Y_left += gap;
                int X_center = (frame.getWidth() - width) / 2;
                dangKyButton.setBounds(X_center, Y_left, width, height);
                Y_left += gap;

                int X_center_label = X_center + dangKyButton.getWidth() / 2;


            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GiaoDienBanDau();
                frame.dispose();
            }
        });
        dangKyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(phiQuanLyChungCuField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(phiDichVuChungCuField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(phiGuiXeMayField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(phiGuiOtoField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(taiKhoanField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(matKhauField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(phiDienField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(phiNuocField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else if(phiInternetField.getText().equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Bạn chưa điền đủ thông tin");
                } else {
                        addSignInToSQL();
                        JOptionPane.showMessageDialog(mainPanel, "Thêm hóa đơn thành công");
                        new DangNhap();
                        frame.dispose();
                }



            }
        });

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    private static void setPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }
    public void addSignInToSQL() {
        try {
            Connection connection = getConnectDatabase();
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            String text = taiKhoanField.getText();
            if (text.equals("Để trống để tự động gửi tới tất cả hộ khẩu")) {
                preparedStatement = connection.prepareStatement("SELECT Ma_ho_khau, So_luong_xe_may, So_luong_o_to, Dien_tich \n" +
                        "FROM ho_khau \n" +
                        "WHERE Ma_ho_khau IN (\n" +
                        "    SELECT Ma_ho_khau\n" +
                        "    FROM ho_khau\n" +
                        "    WHERE dia_diem LIKE 'Chung cư BlueMoon'\n" +
                        ");");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int maHoKhau = Integer.parseInt(String.valueOf(resultSet.getInt(1)));
                    int soXeMay = Integer.parseInt(String.valueOf(resultSet.getInt(2)));
                    int soOto = Integer.parseInt(String.valueOf(resultSet.getInt(3)));
                    int dienTich = Integer.parseInt(String.valueOf(resultSet.getInt(4)));
                    PreparedStatement sendAllStatement = connection.prepareStatement("INSERT INTO hoa_don(Phi_qly_chung_cu, Phi_dvu_chung_cu, Phi_gui_xe, Phi_dien,Phi_nuoc,Phi_Internet, Thoi_diem_dong, Han_dong, Da_xac_nhan, Ma_ho_khau)  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    sendAllStatement.setInt(1, Integer.parseInt(phiQuanLyChungCuField.getText()) * dienTich);
                    sendAllStatement.setInt(2, Integer.parseInt(phiDichVuChungCuField.getText()) * dienTich);
                    sendAllStatement.setInt(3, Integer.parseInt(phiGuiXeMayField.getText()) * soXeMay + Integer.parseInt(phiGuiOtoField.getText()) * soOto);
                    sendAllStatement.setInt(4, Integer.parseInt(phiDienField.getText()));
                    sendAllStatement.setInt(5, Integer.parseInt(phiNuocField.getText()));
                    sendAllStatement.setInt(6, Integer.parseInt(phiInternetField.getText()));
                    sendAllStatement.setString(7, null);
                    sendAllStatement.setString(8, (matKhauField.getText()));
                    sendAllStatement.setBoolean(9, false);
                    sendAllStatement.setInt(10, maHoKhau);

                    sendAllStatement.executeUpdate();
                    sendAllStatement.close();
                }
            } else {
                int maHoKhau = Integer.parseInt(text);
                preparedStatement = connection.prepareStatement("SELECT So_luong_xe_may, So_luong_o_to, Dien_tich FROM ho_khau WHERE Ma_ho_khau = ? AND dia_diem LIKE 'Chung cư BlueMoon'");
                preparedStatement.setInt(1, maHoKhau);
                int soXeMay;
                int soOto;
                int dienTich;
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(mainPanel, "Mã hộ khẩu không tồn tại");
                } else {
                    soXeMay = Integer.parseInt(String.valueOf(resultSet.getInt(1)));
                    soOto = Integer.parseInt(String.valueOf(resultSet.getInt(2)));
                    dienTich = Integer.parseInt(String.valueOf(resultSet.getInt(3)));
                    PreparedStatement sendStatement = connection.prepareStatement("INSERT INTO hoa_don(Phi_qly_chung_cu, Phi_dvu_chung_cu, Phi_gui_xe, Phi_dien,Phi_nuoc,Phi_Internet, Thoi_diem_dong, Han_dong, Da_xac_nhan, Ma_ho_khau)  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    sendStatement.setInt(1, Integer.parseInt(phiQuanLyChungCuField.getText()) * dienTich);
                    sendStatement.setInt(2, Integer.parseInt(phiDichVuChungCuField.getText())* dienTich);
                    sendStatement.setInt(3, Integer.parseInt(phiGuiXeMayField.getText()) * soXeMay + Integer.parseInt(phiGuiOtoField.getText()) * soOto);
                    sendStatement.setInt(4, Integer.parseInt(phiDienField.getText()));
                    sendStatement.setInt(5, Integer.parseInt(phiNuocField.getText()));
                    sendStatement.setInt(6, Integer.parseInt(phiInternetField.getText()));
                    sendStatement.setString(7, null);
                    sendStatement.setString(8, (matKhauField.getText()));
                    sendStatement.setBoolean(9, false);
                    sendStatement.setInt(10, maHoKhau);

                    sendStatement.executeUpdate();
                    sendStatement.close();
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GiaoDienThemPhi();
    }
}