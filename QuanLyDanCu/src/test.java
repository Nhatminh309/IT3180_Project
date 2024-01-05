package QuanLyDanCu.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("SplitPane on LayeredPane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Khai báo JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(600, 400));

        // Tạo JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(300);

        // Thêm các thành phần vào JSplitPane
        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");

        JPanel leftPanel = new JPanel();
        leftPanel.add(leftButton);

        JPanel rightPanel = new JPanel();
        rightPanel.add(rightButton);

        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);

        // Thêm JSplitPane vào JLayeredPane với lớp khác nhau
        layeredPane.add(splitPane, JLayeredPane.DEFAULT_LAYER);

        // Hiển thị frame chứa JLayeredPane
        frame.add(layeredPane);
        frame.setVisible(true);
    }
}
