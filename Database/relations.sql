create table nhankhau (
	ma_nhan_khau int primary key,
	ho_va_ten varchar(50),
	bi_danh varchar(50),
	ngay_sinh date,
	gioi_tinh varchar(5),
	noi_sinh varchar(255),
	nguyen_quan varchar(255),
	dan_toc varchar(20),
	ton_giao varchar(25),
	nghe_nghiep varchar(50),
	noi_lam_viec varchar(255),
	ngay_dang_ky date,
	so_cccd int,
	quan_he_voi_chu_ho varchar(25),
	ma_ho_khau int
);
create table hokhau (
	ma_ho_khau int primary key,
	so_nha varchar(10),
	ten_duong varchar(50),
	ten_phuong_xa varchar(50),
	ten_quan_huyen varchar(50),
	ten_thanh_pho_tinh varchar(50),
	ma_chu_ho int
);

create table phi (
	ma_dong_phi int primary key,
	dia_diem varchar(10),
	phi_ve_sinh int,
	phi_qly_chung_cu int,
	phi_qly_dvu_chung_cu int,
	phi_dong_gop int,
	thoi_diem_dong date,
	ma_ho_khau int
);

create table cancuoccongdan (
	so_cccd int primary key,
	ngay_cap date,
	noi_cap varchar(255),
	gia_tri_den_ngay date
);

ALTER TABLE nhankhau
ADD CONSTRAINT so_cccd_fkey FOREIGN KEY (so_cccd) REFERENCES cancuoccongdan(so_cccd),
ADD CONSTRAINT ma_ho_khau_fkey FOREIGN KEY (ma_ho_khau) REFERENCES hokhau(ma_ho_khau);

alter table hokhau
add constraint ma_chu_ho_fkey foreign key (ma_chu_ho) references nhankhau(ma_nhan_khau);

alter table phi
add constraint ma_ho_khau_fkey foreign key (ma_ho_khau) references hokhau(ma_ho_khau);
