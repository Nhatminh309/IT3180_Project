CREATE TABLE Ho_khau(
    Ma_ho_khau INT PRIMARY KEY,
    So_nha VARCHAR(10) NOT NULL,
    Ten_duong VARCHAR(50) NOT NULL,
    Ten_phuong VARCHAR(50) NOT NULL,
    Ten_quan VARCHAR(50) NOT NULL,
    Ten_thanh_pho VARCHAR(50) NOT NULL,
    Da_xac_nhan BOOLEAN DEFAULT FALSE
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
    Ma_nhan_khau INT PRIMARY KEY,
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
    Ma_ho_khau INT NOT NULL,
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (So_CCCD) REFERENCES Can_cuoc_cong_dan(So_CCCD),
    FOREIGN KEY (Ma_ho_khau) REFERENCES Ho_khau(Ma_ho_khau)
);

CREATE TABLE Chu_ho(
    Ma_ho_khau INT,
    Ma_nhan_khau INT,
    PRIMARY KEY (Ma_ho_khau, Ma_nhan_khau),
    Ngay_tao DATE NOT NULL,
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (Ma_ho_khau) REFERENCES Ho_khau(Ma_ho_khau),
    FOREIGN KEY (Ma_nhan_khau) REFERENCES Nhan_khau(Ma_nhan_khau)
);

CREATE TABLE So_tam_tru(
    ID INT PRIMARY KEY,
    Dia_chi_thuong_tru VARCHAR(255),
    Ngay_dang_ky DATE NOT NULL,
    Thoi_han DATE NOT NULL,
    Ma_nhan_khau INT NOT NULL,
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (Ma_nhan_khau) REFERENCES Nhan_khau(Ma_nhan_khau)
);

CREATE TABLE Tam_vang(
    ID INT PRIMARY KEY,
    Ma_nhan_khau INT, 
    Ngay_tam_vang DATE NOT NULL,
    Noi_den VARCHAR(255),
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (Ma_nhan_khau) REFERENCES Nhan_khau(Ma_nhan_khau)
);

CREATE TABLE Khai_tu(
    ID INT PRIMARY KEY,
    Ma_nhan_khau INT,
    Ngay_mat DATE,
    Noi_mat VARCHAR(255),
    Ly_do VARCHAR(255),
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (Ma_nhan_khau) REFERENCES Nhan_khau(Ma_nhan_khau)
);

CREATE TABLE Thay_doi_chu_ho(
    ID INT PRIMARY KEY,
    Ngay_thay_doi DATE,
    Ly_do  VARCHAR(255),
    Ma_ho_khau INT NOT NULL,
    Ma_chu_moi INT NOT NULL, 
    Ma_chu_cu INT NOT NULL,
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (Ma_ho_khau) REFERENCES Ho_khau(Ma_ho_khau),
    FOREIGN KEY (Ma_chu_moi) REFERENCES Nhan_khau(Ma_nhan_khau),
    FOREIGN KEY (Ma_chu_cu) REFERENCES Nhan_khau(Ma_nhan_khau)
);

CREATE TABLE Phieu_chuyen_di(
    ID INT PRIMARY KEY,
    Ma_nhan_khau INT NOT NULL,
    Noi_chuyen_den VARCHAR(255),
    Ngay_chuyen_di DATE NOT NULL,
    Ly_do_chuyen VARCHAR(255),
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (Ma_nhan_khau) REFERENCES Nhan_khau(Ma_nhan_khau)
);

CREATE TABLE Phieu_chuyen_den(
    ID INT PRIMARY KEY,
    Ma_nhan_khau INT,
    Noi_chuyen_di VARCHAR(255),
    Ngay_chuyen_den DATE NOT NULL,
    Ly_do_chuyen VARCHAR(255),
    Da_xac_nhan BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (Ma_nhan_khau) REFERENCES Nhan_khau(Ma_nhan_khau)
);

CREATE TABLE Can_bo(
    Ma_can_bo INTEGER PRIMARY KEY,
    Ho_ten VARCHAR(30),
    Ngay_sinh DATE,
    Gioi_tinh VARCHAR(10),
    Chuc_vu VARCHAR(30)
);
CREATE TABLE Phan_thuong(
    Ma_phan_phuong INTEGER PRIMARY KEY,
    Dip_le VARCHAR(100),
    Loai_phan_thuong VARCHAR(100),
    Gia_tri NUMERIC(10, 2),
    Ngay_dang_ky DATE,
    Ma_nhan_khau INTEGER,
    Ma_can_bo INTEGER,
    FOREIGN KEY (Ma_nhan_khau) REFERENCES Nhan_khau(Ma_nhan_khau),
    FOREIGN KEY (Ma_can_bo) REFERENCES Can_bo(Ma_can_bo)
);

CREATE TABLE Bang_phi(
    Ma_hoa_don VARCHAR(10) PRIMARY KEY,
    Dia_diem VARCHAR(20),
    Phi_ve_sinh INTEGER,
    Phi_qly_chung_cu INTEGER,
    Phi_dvu_chung_cu INTEGER,
    Phi_dong_gop INTEGER,
    Thoi_diem_dong DATE,
    Ma_ho_khau INT,
    FOREIGN KEY (Ma_ho_khau) REFERENCES Ho_khau(Ma_ho_khau)
);

CREATE TABLE Dang_nhap(
    Usernam VARCHAR(20),
    Pass VARCHAR(20),
    Vai_tro VARCHAR(20),
    Ma_nhan_khau INTEGER,
    FOREIGN KEY (Ma_nhan_khau) REFERENCES Nhan_khau(Ma_nhan_khau)
);