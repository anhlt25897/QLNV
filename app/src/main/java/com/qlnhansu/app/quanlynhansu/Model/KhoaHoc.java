package com.qlnhansu.app.quanlynhansu.Model;

import java.io.Serializable;

public class KhoaHoc implements Serializable {
    private int Id;
    private String hoTenLot;
    private String ten;
    private String hoNghi;
    private String congTrinh;
    private String deTai;
    private String giaoTrinh;
    private String baiBao;
    private String sach;
    private String linkanh;
    private String keyView;

    public KhoaHoc() {
    }

    public KhoaHoc(int id, String hoTenLot, String ten, String hoNghi, String congTrinh, String deTai, String giaoTrinh, String baiBao, String sach, String linkanh, String keyView) {
        Id = id;
        this.hoTenLot = hoTenLot;
        this.ten = ten;
        this.hoNghi = hoNghi;
        this.congTrinh = congTrinh;
        this.deTai = deTai;
        this.giaoTrinh = giaoTrinh;
        this.baiBao = baiBao;
        this.sach = sach;
        this.linkanh = linkanh;
        this.keyView = keyView;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getHoNghi() {
        return hoNghi;
    }

    public void setHoNghi(String hoNghi) {
        this.hoNghi = hoNghi;
    }

    public String getCongTrinh() {
        return congTrinh;
    }

    public void setCongTrinh(String congTrinh) {
        this.congTrinh = congTrinh;
    }

    public String getDeTai() {
        return deTai;
    }

    public void setDeTai(String deTai) {
        this.deTai = deTai;
    }

    public String getGiaoTrinh() {
        return giaoTrinh;
    }

    public void setGiaoTrinh(String giaoTrinh) {
        this.giaoTrinh = giaoTrinh;
    }

    public String getBaiBao() {
        return baiBao;
    }

    public void setBaiBao(String baiBao) {
        this.baiBao = baiBao;
    }

    public String getSach() {
        return sach;
    }

    public void setSach(String sach) {
        this.sach = sach;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }

    public String getKeyView() {
        return keyView;
    }

    public void setKeyView(String keyView) {
        this.keyView = keyView;
    }
}
