package QuanLyDanCu.src.themhoadon;

import QuanLyDanCu.src.danhsachhoadon.DanhSachHoaDon;
import QuanLyDanCu.src.danhsachhoadon.ViewDanhSachHoaDon;
import QuanLyDanCu.src.giaodien.GiaoDienQuanLy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemHoaDon extends GiaoDienQuanLy{
    private static JPanel selectionPanel;
    private static JLabel selectionLabel;
    private static JButton dropDownButton;
    private static JPopupMenu dropDownMenu;
    public ThemHoaDon() {
        JPanel panelBoard = getPanelBoard();
        panelBoard.removeAll();

        selectionPanel = new JPanel();
        selectionPanel.setBounds(300, 100, 600, 40);
        selectionPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        selectionLabel = new JLabel("Lựa chọn địa điểm....");
        selectionLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        selectionPanel.add(selectionLabel);
        selectionPanel.add(Box.createRigidArea(new Dimension(320, 0)));

        dropDownButton = new JButton();
        ImageIcon icon = new ImageIcon("QuanLyDanCu/src/icon/downArrow.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        dropDownButton.setIcon(scaledIcon);
        selectionPanel.add(dropDownButton);

        panelBoard.setLayout(null);
        panelBoard.add(selectionPanel);

        dropDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMenuBar(dropDownButton);
            }
        });

        panelBoard.revalidate(); // Revalidate the panel to display changes
        panelBoard.repaint(); // Repaint the panel


    }
    public void displayMenuBar(Component co) {
        dropDownMenu = new JPopupMenu();
        Font menuFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        JMenuItem menuItem1 = new JMenuItem("1. Chung cư Moon");
        JMenuItem menuItem2 = new JMenuItem("2. Tổ dân phố");

        menuItem1.setFont(menuFont);
        menuItem2.setFont(menuFont);

        dropDownMenu.add(menuItem1);
        dropDownMenu.add(menuItem2);

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ThemHoaDonChungCu();
            }

        });
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ThemHoaDonTDP();
            }
        });
        dropDownMenu.show(co, 0, co.getHeight());
    }
}
