package com.qlnhansu.app.quanlynhansu.Model;

import java.io.Serializable;

public class GiangVien implements Serializable {
    private int Id;
    private String maGV;
    private String hoTenLot;
    private String ten;
    private String ngaySinh;
    private String gioiTinh;
    private String queQuan;
    private String danToc;
    private String tonGiao;
    private int soCMND;
    private String hoKhauThuongTru;
    private String choOHienTai;
    private String dienThoai;
    private String Email;
    private String donViCongTac;
    private String hocVi;
    private int namNhanHocVi;
    private String chucDanhKhoaHoc;
    private int namBoNhiem;
    private String linkanh;
    public GiangVien() {
    }

    public GiangVien(int id, String maGV, String hoTenLot, String ten, String ngaySinh, String gioiTinh, String queQuan, String danToc, String tonGiao, int soCMND, String hoKhauThuongTru, String choOHienTai, String dienThoai, String email, String donViCongTac, String hocVi, int namNhanHocVi, String chucDanhKhoaHoc, int namBoNhiem, String linkanh) {
        Id = id;
        this.maGV = maGV;
        this.hoTenLot = hoTenLot;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.soCMND = soCMND;
        this.hoKhauThuongTru = hoKhauThuongTru;
        this.choOHienTai = choOHienTai;
        this.dienThoai = dienThoai;
        Email = email;
        this.donViCongTac = donViCongTac;
        this.hocVi = hocVi;
        this.namNhanHocVi = namNhanHocVi;
        this.chucDanhKhoaHoc = chucDanhKhoaHoc;
        this.namBoNhiem = namBoNhiem;
        this.linkanh = linkanh;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getHoTenLot() {
        return hoTenLot;
    }

    public void setHoTenLot(String hoTenLot) {
        this.hoTenLot = hoTenLot;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public int getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(int soCMND) {
        this.soCMND = soCMND;
    }

    public String getHoKhauThuongTru() {
        return hoKhauThuongTru;
    }

    public void setHoKhauThuongTru(String hoKhauThuongTru) {
        this.hoKhauThuongTru = hoKhauThuongTru;
    }

    public String getChoOHienTai() {
        return choOHienTai;
    }

    public void setChoOHienTai(String choOHienTai) {
        this.choOHienTai = choOHienTai;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDonViCongTac() {
        return donViCongTac;
    }

    public void setDonViCongTac(String donViCongTac) {
        this.donViCongTac = donViCongTac;
    }

    public String getHocVi() {
        return hocVi;
    }

    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }

    public int getNamNhanHocVi() {
        return namNhanHocVi;
    }

    public void setNamNhanHocVi(int namNhanHocVi) {
        this.namNhanHocVi = namNhanHocVi;
    }

    public String getChucDanhKhoaHoc() {
        return chucDanhKhoaHoc;
    }

    public void setChucDanhKhoaHoc(String chucDanhKhoaHoc) {
        this.chucDanhKhoaHoc = chucDanhKhoaHoc;
    }

    public int getNamBoNhiem() {
        return namBoNhiem;
    }

    public void setNamBoNhiem(int namBoNhiem) {
        this.namBoNhiem = namBoNhiem;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }

}
