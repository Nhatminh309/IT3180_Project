package quanlydancu.src.quanlyhokhau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static com.sun.glass.ui.Cursor.setVisible;

public class QuanLyHoKhau  extends MainBoard {
    private JButton btnThemHoKhau;
    private JButton btnTachHoKhau;
    private JButton btnDSHoKhau;
    private JButton btnTimHoKhau;
    private JButton btnTaoChuHo;
    private JButton btnDoiChuHo;
    private JButton btnDangKyTamVang;
    private JButton btnDangKyTamTru;
    private JButton btnDSTamTru;
    private JButton btnDSTamVang;
    private JButton btnKhaiTu;
    private JButton btnDSThanhVien;

    public QuanLyHoKhau() {
        // Gọi constructor của lớp cha (GiaoDienChung)
        super();

        // Tạo GridLayout với 2 hàng và nhiều cột
        GridLayout gridLayout = new GridLayout(2, 0);
        horizontalBar.setLayout(gridLayout);

        // Tạo các nút bấm
        btnThemHoKhau = new JButton("Thêm hộ khẩu");
        btnTachHoKhau = new JButton("Tách hộ khẩu");
        btnDSHoKhau = new JButton("DS Hộ khẩu");
        btnTimHoKhau = new JButton("Tìm hộ khẩu");
        btnTaoChuHo = new JButton("Tạo chủ hộ");
        btnDoiChuHo = new JButton("Đổi chủ hộ");
        btnDangKyTamVang = new JButton("Đăng ký tạm vắng");
        btnDangKyTamTru = new JButton("Đăng ký tạm trú");
        btnDSTamTru = new JButton("DS Tạm trú");
        btnDSTamVang = new JButton("DS Tạm vắng");
        btnKhaiTu = new JButton("Khai tử");
        btnDSThanhVien = new JButton("DS thành viên");

        // Thêm sự kiện cho các nút bấm
        btnThemHoKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemHoKhau themHoKhauFrame = new ThemHoKhau();
                showFrame();
                try {
                    ThemThanhVien themThanhVienFrame = new ThemThanhVien();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                showFrame();
                frame.dispose();
            }
        });

        btnTachHoKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    autoHoKhau autoHoKhauFrame = new autoHoKhau();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                showFrame();
            }
        });

        btnDSHoKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DSHoKhau dsHoKhauFrame = new DSHoKhau();
                showFrame();
            }
        });

        btnTimHoKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimHoKhau timHoKhauFrame = new TimHoKhau();
                showFrame();
            }
        });

        btnTaoChuHo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TaoChuHo taoChuHoFrame = new TaoChuHo();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                showFrame();
            }
        });

        btnDoiChuHo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DoiChuHo doiChuHoFrame = new DoiChuHo();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                showFrame();
            }
        });

        btnDangKyTamVang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DangKyTamVang dangKyTamVangFrame = new DangKyTamVang();
                showFrame();
            }
        });

        btnDangKyTamTru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DangKyTamTru dangKyTamTruFrame = new DangKyTamTru();
                showFrame();
            }
        });

        btnDSTamTru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DSTamTru DSTamTruFrame = new DSTamTru();
                showFrame();
            }
        });

        btnDSTamVang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DSTamVang DSTamVangFrame = new DSTamVang();
                showFrame();
            }
        });

        btnKhaiTu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KhaiTu khaiTuFrame = new KhaiTu();
                showFrame();
            }
        });

        btnDSThanhVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DSThanhVien dsThanhVienFrame = new DSThanhVien();
                showFrame();
            }
        });


        // Thêm các nút vào thanh horizontalBar
        horizontalBar.add(btnThemHoKhau);
        horizontalBar.add(btnTachHoKhau);
        horizontalBar.add(btnDSHoKhau);
        horizontalBar.add(btnTimHoKhau);
        horizontalBar.add(btnTaoChuHo);
        horizontalBar.add(btnDoiChuHo);
        horizontalBar.add(btnDangKyTamVang);
        horizontalBar.add(btnDangKyTamTru);
        horizontalBar.add(btnDSTamTru);
        horizontalBar.add(btnDSTamVang);
        horizontalBar.add(btnKhaiTu);
        horizontalBar.add(btnDSThanhVien);
    }
    public void showFrame() {
        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuanLyHoKhau();
            }
        });
    }
}