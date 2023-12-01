import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import danhsachhoadon.DanhSachHoaDon;
public class ThongKeKhoanThu extends DanhSachHoaDon {
    private static JPanel selectionPanel;
    private static JLabel selectionLabel;
    private static JButton dropDownButton;
    private static JPopupMenu dropDownMenu;
    private static JTable table;
    private static DefaultTableModel model;
    private static JPanel viewPanel;
    private static JLabel viewLabel;
    public JPanel getSelectionPanel() {
        return selectionPanel;
    }
    public ThongKeKhoanThu() {
        super();

    }
    @Override
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

            }

        });
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        dropDownMenu.show(co, 0, co.getHeight());

    }
}
