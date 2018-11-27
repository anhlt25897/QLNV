package com.qlnhansu.app.quanlynhansu.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.qlnhansu.app.quanlynhansu.Model.GiangVien;
import com.qlnhansu.app.quanlynhansu.R;
import com.squareup.picasso.Picasso;

public class SoYeuLiLichActivity extends AppCompatActivity {
    TextView hoTenLot,Ten,ngaySinh,gioiTinh,queQuan,danToc,tonGiao,soCMND,hoKhauThuongTru,choOHienTai,
    dienThoai,Email,donViCongTac,hocVi,namNhanHocVi,chucDanh,namBoNhiem;
    ImageView imgVew;
    int id =0;
    String hoTenLotsy = "";
    String TenLotsy = "";
    String ngaySinhsy = "";
    String gioiTinhsy = "";
    String queQuansy = "";
    String danTocsy = "";
    String tonGiaosy = "";
    int soCMNDsy = 0;
    String hoKhauThuongTrusy = "";
    String choOHienTaisy = "";
    String dienThoaisy = "";
    String Emailsy = "";
    String donViCongTacsy = "";
    String hocVisy = "";
    int namNhanHocVisy = 0;
    String chucDanhsy = "";
    int namBoNhiemsy = 0;
    String linkanh = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_yeu_li_lich);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarsoyeull);
        setSupportActionBar(toolbar);
        TextView titleListUser = toolbar.findViewById(R.id.title_soyeull);
        titleListUser.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_32dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        hoTenLot = (TextView)findViewById(R.id.hotenlot);
        Ten = (TextView)findViewById(R.id.tensyll);
        ngaySinh = (TextView)findViewById(R.id.txtsyllngaysinh);
        gioiTinh = (TextView)findViewById(R.id.txtsyllgioitinh);
        queQuan = (TextView)findViewById(R.id.txtsyllquequan);
        danToc = (TextView)findViewById(R.id.txtsylldantoc);
        tonGiao = (TextView)findViewById(R.id.txtsylltongiao);
        soCMND = (TextView)findViewById(R.id.txtsyllsoCMND);
        hoKhauThuongTru = (TextView)findViewById(R.id.txtsyllhktt);
        choOHienTai = (TextView)findViewById(R.id.txtsyllchooht);
        dienThoai = (TextView)findViewById(R.id.txtsylldienthoai);
        Email = (TextView)findViewById(R.id.txtsyllemail);
        donViCongTac = (TextView)findViewById(R.id.txtsylldonvict);
        hocVi = (TextView)findViewById(R.id.txtsyllhocvi);
        namNhanHocVi = (TextView)findViewById(R.id.txtsyllnamnhanhocvi);
        chucDanh = (TextView)findViewById(R.id.txtsyllchucdanh);
        namBoNhiem = (TextView)findViewById(R.id.txtsyllnambonhiem);
        imgVew = (ImageView) findViewById(R.id.imgView);
        GetInfomation();
    }

    private void GetInfomation() {
        GiangVien giangVien = (GiangVien) getIntent().getSerializableExtra("soyeulilich");
        id = giangVien.getId();
        hoTenLotsy = giangVien.getHoTenLot();
        TenLotsy = giangVien.getTen();
        ngaySinhsy = giangVien.getNgaySinh();
        gioiTinhsy = giangVien.getGioiTinh();
        queQuansy = giangVien.getQueQuan();
        danTocsy = giangVien.getDanToc();
        tonGiaosy = giangVien.getTonGiao();
        soCMNDsy = giangVien.getSoCMND();
        hoKhauThuongTrusy = giangVien.getHoKhauThuongTru();
        choOHienTaisy = giangVien.getChoOHienTai();
        dienThoaisy = giangVien.getDienThoai();
        Emailsy = giangVien.getEmail();
        donViCongTacsy = giangVien.getDonViCongTac();
        hocVisy = giangVien.getHocVi();
        namNhanHocVisy = giangVien.getNamNhanHocVi();
        chucDanhsy = giangVien.getChucDanhKhoaHoc();
        namBoNhiemsy = giangVien.getNamBoNhiem();
        linkanh = giangVien.getLinkanh();
        hoTenLot.setText(hoTenLotsy);
        Ten.setText(TenLotsy);
        ngaySinh.setText(ngaySinhsy);
        gioiTinh.setText(gioiTinhsy);
        queQuan.setText(queQuansy);
        danToc.setText(danTocsy);
        tonGiao.setText(tonGiaosy);
        soCMND.setText(Integer.toString(soCMNDsy));
        hoKhauThuongTru.setText(hoKhauThuongTrusy);
        choOHienTai.setText(choOHienTaisy);
        dienThoai.setText(dienThoaisy);
        Email.setText(Emailsy);
        donViCongTac.setText(donViCongTacsy);
        hocVi.setText(hocVisy);
        namNhanHocVi.setText(Integer.toString(namNhanHocVisy));
        chucDanh.setText(chucDanhsy);
        namBoNhiem.setText(Integer.toString(namBoNhiemsy));
        Picasso.with(getApplicationContext()).load(linkanh)
                .into(imgVew);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //handle the home button onClick event here.
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
