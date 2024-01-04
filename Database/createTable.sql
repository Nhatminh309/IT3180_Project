CREATE TABLE Ho_khau(
    Ma_ho_khau SERIAL PRIMARY KEY,
    Dia_diem VARCHAR(50) NOT NULL,
    So_nha VARCHAR(10) NOT NULL,
    Ten_duong VARCHAR(50) NOT NULL,
    Ten_phuong VARCHAR(50) NOT NULL,
    Ten_quan VARCHAR(50) NOT NULL,
    Ten_thanh_pho VARCHAR(50) NOT NULL,
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    So_luong_xe_may INT NOT NULL DEFAULT 0,
    So_luong_o_to INT NOT NULL DEFAULT 0,
    Dien_tich INT NOT NULL
);

CREATE TABLE Can_cuoc_cong_dan(
    So_CCCD VARCHAR(20) PRIMARY KEY,
    Ngay_cap DATE NOT NULL,
    Noi_cap VARCHAR(255) NOT NULL,
    Gia_tri_den_ngay DATE NOT NULL,
    Dac_diem_nhan_dang VARCHAR(255),
    Da_xac_nhan BOOLEAN DEFAULT FALSE
);

CREATE TABLE Nhan_khau(
    Ma_nhan_khau SERIAL PRIMARY KEY,
    Ho_va_ten VARCHAR(50) NOT NULL,
    Bi_danh VARCHAR(50),
    Ngay_sinh DATE NOT NULL,
    Gioi_tinh VARCHAR(5) NOT NULL,
    Noi_sinh VARCHAR(255) NOT NULL,
    Que_quan VARCHAR(255) NOT NULL,
    Dan_toc VARCHAR(20) NOT NULL,
    Ton_giao VARCHAR(25),
    Nghe_nghiep VARCHAR(50),
    Noi_lam_viec VARCHAR(255),
    Ngay_dang_ky DATE NOT NULL,
    So_CCCD VARCHAR(20) NOT NULL UNIQUE,
    So_dien_thoai VARCHAR(10),
    Quan_he_voi_chu_ho VARCHAR(25),
    Ma_ho_khau SERIAL NOT NULL,
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (So_CCCD) REFERENCES Can_cuoc_cong_dan(So_CCCD),
    FOREIGN KEY (Ma_ho_khau) REFERENCES Ho_khau(Ma_ho_khau)
);

CREATE TABLE Chu_ho(
    Ma_ho_khau SERIAL,
    Ma_nhan_khau SERIAL,
    PRIMARY KEY (Ma_ho_khau, Ma_nhan_khau),
    Ngay_tao DATE NOT NULL,
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (Ma_ho_khau) REFERENCES Ho_khau(Ma_ho_khau),
    FOREIGN KEY (Ma_nhan_khau) REFERENCES Nhan_khau(Ma_nhan_khau)
);

CREATE TABLE So_tam_tru(
    ID SERIAL PRIMARY KEY,
    Dia_chi_thuong_tru VARCHAR(255),
    Ngay_dang_ky DATE NOT NULL,
    Thoi_han DATE NOT NULL,
    Ma_nhan_khau INT NOT NULL,
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (Ma_nhan_khau) REFERENCES Nhan_khau(Ma_nhan_khau)
);

CREATE TABLE Tam_vang(
    ID SERIAL PRIMARY KEY,
    Ma_nhan_khau SERIAL, 
    Ngay_tam_vang DATE NOT NULL,
    Noi_den VARCHAR(255),
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (Ma_nhan_khau) REFERENCES Nhan_khau(Ma_nhan_khau)
);

CREATE TABLE Khai_tu(
    ID SERIAL PRIMARY KEY,
    Ma_nhan_khau SERIAL,
    Ngay_mat DATE,
    Noi_mat VARCHAR(255),
    Ly_do VARCHAR(255),
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (Ma_nhan_khau) REFERENCES Nhan_khau(Ma_nhan_khau)
);


CREATE TABLE DangNhap (
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    vaitro VARCHAR(10) NOT NULL
);


