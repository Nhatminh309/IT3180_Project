package quanlydancu.src.quanlynhankhau;

import quanlydancu.src.quanlyhokhau.QuanLyHoKhau;
import quanlydancu.src.quanlyhokhau.MainBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuanLyNhanKhau extends MainBoard {

    private JButton btnChinhSua;
    private JButton btnThem;
    public QuanLyNhanKhau(){
        super();
        // Tạo GridLayout với 2 hàng và nhiều cột
        GridLayout gridLayout = new GridLayout(2, 0);
        horizontalBar.setLayout(gridLayout);

        // Tạo các nút bấm
        btnChinhSua = new JButton("Chỉnh sửa nhân khẩu");
        btnThem = new JButton("Thêm nhân khẩu");

        // Thêm sự kiện cho các nút bấm
        btnChinhSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChinhSuaNhanKhau();
            }
        });

        // Thêm sự kiện cho các nút bấm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ThemNhanKhau();
            }
        });

        frame.setVisible(true);
        // Add "Quay về" button
        JButton btnQuayVe = new JButton("Quay về");
        btnQuayVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new QuanLyHoKhau();
                frame.dispose();
            }
        });

        horizontalBar.add(btnChinhSua);
        horizontalBar.add(btnThem);
        // Add the "Quay về" button to the rightPanel
        rightPanel.add(btnQuayVe, BorderLayout.SOUTH);

        frame.setVisible(true);

    }
}
