package giaodien;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.*;
import dangnhap.*;
import danhsachhoadon.*;
import timkiemhoadon.*;
public class GiaoDienQuanLy extends DangNhap{
    private static JFrame frame;
    private static JButton quanLyHoKhau;
    private static JButton quanLyThuPhi;
    private static JButton quanLyPhatThuong;
    private static JLabel toolBarLabel = new JLabel("CỔNG THÔNG TIN THỦ TỤC HÀNH CHÍNH");
    private static JPanel panelBoard;
    private static JPanel panelButtons;
    private static JPanel panelToolbar;
    private static JButton logOutButton = new JButton("Đăng xuất");

    public GiaoDienQuanLy() {
        frame = new JFrame("Quan ly dan cu");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //Font
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);

        //Toolbar
        panelToolbar = new JPanel();
        panelToolbar.setBounds(0, 0, 1680, 100);
        panelToolbar.setLayout(new BoxLayout(panelToolbar, BoxLayout.X_AXIS));
        panelToolbar.setBackground(Color.LIGHT_GRAY);

        //Buttons
        panelButtons = new JPanel();
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
        panelButtons.setBounds(0, 100, 200, 800);
        panelButtons.setBackground(Color.LIGHT_GRAY);


        //Board
        panelBoard = new JPanel();
        panelBoard.setBounds(200, 100, 1480, 800);
        //panelBoard.setLayout(new BoxLayout(panelBoard, BoxLayout.Y_AXIS));
        panelBoard.setBackground(Color.WHITE);
        panelBoard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        //Panel Buttons
        quanLyHoKhau = new JButton("Quản lý hộ khẩu");
        quanLyHoKhau.setMaximumSize(new Dimension(200, 100));
        quanLyHoKhau.setBackground(Color.LIGHT_GRAY);
        quanLyHoKhau.setOpaque(true);
        quanLyHoKhau.setFont(font);
        panelButtons.add(quanLyHoKhau);
        quanLyHoKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSubFunctionHoKhau(quanLyHoKhau);
            }
        });

        quanLyThuPhi = new JButton("Quản lý thu phí");
        quanLyThuPhi.setBackground(Color.LIGHT_GRAY);
        quanLyThuPhi.setMaximumSize(new Dimension(200, 100));
        quanLyThuPhi.setOpaque(true);
        quanLyThuPhi.setFont(font);
        panelButtons.add(quanLyThuPhi);
        quanLyThuPhi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSubFunctionThuPhi(quanLyThuPhi);
            }
        });

        quanLyPhatThuong = new JButton("Quản lý phát thưởng");
        quanLyPhatThuong.setBackground(Color.LIGHT_GRAY);
        quanLyPhatThuong.setMaximumSize(new Dimension(200, 100));
        quanLyPhatThuong.setOpaque(true);
        quanLyPhatThuong.setFont(font);
        panelButtons.add(quanLyPhatThuong);
        quanLyPhatThuong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSubFunctionPhatThuong(quanLyPhatThuong);
            }
        });

        ImageIcon iconLogOut = new ImageIcon("/Users/macbookair/2023.1/nhapmoncnpm/IT3180_Project/QuanLyDanCu/src/icon/logout_Icon.png");
        Image imgLogOut = iconLogOut.getImage();
        Image scaledImgLogOut = imgLogOut.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaleIconLogOut = new ImageIcon(scaledImgLogOut);
        logOutButton.setIcon(scaleIconLogOut);
        logOutButton.setIconTextGap(10);
        logOutButton.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        //panelButtons.add(Box.createVerticalGlue());
        logOutButton.setMaximumSize(new Dimension(200, 50));
        logOutButton.setFocusable(false);
        panelButtons.add(Box.createRigidArea(new Dimension(0, 400)));
        panelButtons.add(logOutButton);

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GiaoDien();
            }

        });

        //Panel toolbar
        panelToolbar.add(Box.createRigidArea(new Dimension(30, 0)));
        ImageIcon icon = new ImageIcon("/Users/macbookair/2023.1/nhapmoncnpm/IT3180_Project/QuanLyDanCu/src/icon/iconHoKhau.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        toolBarLabel.setIcon(scaledIcon);
        toolBarLabel.setIconTextGap(20);

        Font newFont = new Font(Font.SANS_SERIF, Font.BOLD, 36);
        toolBarLabel.setFont(newFont);
        toolBarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelToolbar.add(toolBarLabel);
        panelToolbar.add(Box.createRigidArea(new Dimension(200, 0)));

        JLabel subLabel = new JLabel("Xin chào");
        JLabel userNameLabel = new JLabel(getUserNameField().getText());
        subLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        userNameLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 26));
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subLabel.setMaximumSize(new Dimension(100, 30));
        userNameLabel.setMaximumSize(new Dimension(200, 30));
        panelToolbar.add(subLabel);
        //panelToolbar.add(Box.createRigidArea(new Dimension(10, 0)));
        panelToolbar.add(userNameLabel);

        //Add to mainPanel
        mainPanel.add(panelToolbar);
        mainPanel.add(panelButtons);
        mainPanel.add(panelBoard);


        frame.getContentPane().add(mainPanel);
        frame.setSize(1680, 900);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void showSubFunctionHoKhau(Component co) {
        JPopupMenu popMenu = new JPopupMenu();
        Font menuFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        JMenuItem menuItem1 = new JMenuItem("1. Tạo hộ khẩu mới");
        JMenuItem menuItem2 = new JMenuItem("2. Thay đổi chủ hộ");
        JMenuItem menuItem3 = new JMenuItem("3. Cấp giấy tạm vắng");
        JMenuItem menuItem4 = new JMenuItem("4. Cấp giấy tạm trú");

        menuItem1.setFont(menuFont);
        menuItem2.setFont(menuFont);
        menuItem3.setFont(menuFont);
        menuItem4.setFont(menuFont);

        popMenu.add(menuItem1);
        popMenu.add(menuItem2);
        popMenu.add(menuItem3);
        popMenu.add(menuItem4);

        popMenu.show(co, 0, co.getHeight());
    }
    public void showSubFunctionThuPhi(Component co) {
        JPopupMenu popMenu = new JPopupMenu();
        Font menuFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        JMenuItem menuItem1 = new JMenuItem("1. Danh sách hoá đơn");
        JMenuItem menuItem2 = new JMenuItem("2. Thêm hoá đơn");
        JMenuItem menuItem3 = new JMenuItem("3. Tìm kiếm hoá đơn");
        JMenuItem menuItem4 = new JMenuItem("4. Thống kê khoản thu");

        menuItem1.setFont(menuFont);
        menuItem2.setFont(menuFont);
        menuItem3.setFont(menuFont);
        menuItem4.setFont(menuFont);

        popMenu.add(menuItem1);
        popMenu.add(menuItem2);
        popMenu.add(menuItem3);
        popMenu.add(menuItem4);

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DanhSachHoaDon();
            }
        });
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TimKiemHoaDon();
            }
        });
        popMenu.show(co, 0, co.getHeight());
    }
    public void showSubFunctionPhatThuong(Component co) {
        JPopupMenu popMenu = new JPopupMenu();
        Font menuFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        JMenuItem menuItem1 = new JMenuItem("1. Danh sách phát thưởng");
        JMenuItem menuItem2 = new JMenuItem("2. Danh sách quà tặng");

        menuItem1.setFont(menuFont);
        menuItem2.setFont(menuFont);
        popMenu.add(menuItem1);
        popMenu.add(menuItem2);

        popMenu.show(co, 0, co.getHeight());
    }
    public JPanel getPanelBoard() {
        return panelBoard;
    }


    public static void main(String[] args) {
        new GiaoDienQuanLy();
    }
}
