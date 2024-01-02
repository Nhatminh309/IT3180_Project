package QuanLyDanCu.src;

import javax.swing.*;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Spaced Image Button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        // Tạo button
        JButton button = new JButton("Home");

        // Đường dẫn đến ảnh ngôi nhà
        String imagePath = "QuanLyDanCu/src/icon/homeIcon"; // Thay đổi đường dẫn đến ảnh ngôi nhà

        // Tạo icon từ ảnh
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image img = originalIcon.getImage();

        // Lấy kích thước của icon
        int iconWidth = originalIcon.getIconWidth();
        int iconHeight = originalIcon.getIconHeight();

        // Scale ảnh theo một nửa kích thước ban đầu
        Image scaledImage = img.getScaledInstance(iconWidth - 1, iconHeight - 1, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Đặt icon và văn bản cho button
        button.setIcon(scaledIcon);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setMargin(new Insets(0, 10, 0, 10)); // Thiết lập khoảng cách xung quanh văn bản

        frame.add(button);
        frame.setVisible(true);
    }
    }

