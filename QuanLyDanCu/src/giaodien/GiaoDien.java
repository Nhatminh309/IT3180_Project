package QuanLyDanCu.src.giaodien;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import QuanLyDanCu.src.dangnhap.*;
public class GiaoDien {
    private static JFrame frame;
    private static JButton dangNhap;
    private static JButton dangKy;
    private static JLabel toolBarLabel = new JLabel("CỔNG THÔNG TIN THỦ TỤC HÀNH CHÍNH");
    private static JPanel panelBoard;
    private static JPanel panelToolbar;

    public GiaoDien() {
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
        //Board
        panelBoard = new JPanel();
        panelBoard.setBounds(200, 100, 1480, 800);
        //panelBoard.setLayout(new BoxLayout(panelBoard, BoxLayout.Y_AXIS));
        panelBoard.setBackground(Color.WHITE);
        //panelBoard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));


        //Panel toolbar
        panelToolbar.add(Box.createRigidArea(new Dimension(30, 0)));
        ImageIcon icon = new ImageIcon("QuanLyDanCu/src/icon/iconHoKhau.png");
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
        //Dang ky button
        dangKy = new JButton("Đăng ký");
        dangKy.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        dangKy.setMaximumSize(new Dimension(150, 50));
        dangKy.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        panelToolbar.add(dangKy);
        panelToolbar.add(Box.createRigidArea(new Dimension(10, 0)));
        //Dang nhap button
        dangNhap = new JButton("Đăng nhập");
        dangNhap.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        dangNhap.setMaximumSize(new Dimension(150, 50));
        dangNhap.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        panelToolbar.add(dangNhap);


        //Dang nhap
        dangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new DangNhap().displaySignIn();

            }
        });
        //Dang ky
        dangKy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new DangKy().displaySignUp();
            }
        });

        //Add to mainPanel
        mainPanel.add(panelToolbar);
        mainPanel.add(panelBoard);
        frame.getContentPane().add(mainPanel);
        frame.setSize(1680, 900);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel getPanelBoard() {
        return panelBoard;
    }
    public JFrame getFrame() {
        return frame;
    }
    public String getStringURL() {
        String URL = "jdbc:postgresql://localhost:5432/postgres";
        return URL;
    }
    public Connection getConnectDatabase() throws SQLException {
        String URL = getStringURL();
        Connection connection = DriverManager.getConnection(URL, "postgres", "anhbopcolen");
        return connection;
    }


    public static void main(String[] args) {
        new GiaoDien();
    }
}
