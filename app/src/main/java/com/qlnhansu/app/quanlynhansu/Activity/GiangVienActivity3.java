package com.qlnhansu.app.quanlynhansu.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qlnhansu.app.quanlynhansu.Adapter.GiangVienAdapter;
import com.qlnhansu.app.quanlynhansu.Adapter.GiangVienAdapter2;
import com.qlnhansu.app.quanlynhansu.Adapter.GiangVienAdapter3;
import com.qlnhansu.app.quanlynhansu.CheckUrl.UrlConnect;
import com.qlnhansu.app.quanlynhansu.Model.GiangVien;
import com.qlnhansu.app.quanlynhansu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GiangVienActivity3 extends AppCompatActivity {
    String urlgetdata = UrlConnect.getdatagv;
    String urlxoadata = UrlConnect.xoaGV;
    String urltimkiemdata = UrlConnect.timkiemGV;
    ProgressDialog pDialog;
    ListView lssgiangvien;
    ArrayList<GiangVien> giangVienArrayList;
    GiangVienAdapter3 giangVienAdapter;
    EditText edt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giang_vien3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbargiangvien3);
        setSupportActionBar(toolbar);
        lssgiangvien = (ListView) findViewById(R.id.listView3);
        giangVienArrayList = new ArrayList<>();
        giangVienAdapter = new GiangVienAdapter3(GiangVienActivity3.this, giangVienArrayList);
        lssgiangvien.setAdapter(giangVienAdapter);
        TextView titleListUser = toolbar.findViewById(R.id.title_giangvien3);
        titleListUser.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_32dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DocDuLieu(urlgetdata);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GiangVienActivity3.this,ThemActivity.class);
                startActivity(intent);
            }
        });
        edt3=(EditText)findViewById(R.id.text3);
        //edittest tìm kiếm
        edt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String hoten = edt3.getText().toString().trim();
                if (hoten.equals("")) {
                    DocDuLieu(urlgetdata);
                } else {
                    timkiem(urltimkiemdata);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void DocDuLieu(String url) {
        pDialog = new ProgressDialog(GiangVienActivity3.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Đang tải dữ liệu...");
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(GiangVienActivity3.this);
        //truyền đường dẫn
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0 ;
                String MaGV = "";
                String hoTenLot = "";
                String Ten = "";
                String gioiTinh = "";
                String NgaySinh = "";
                String queQuan = "";
                String danToc = "";
                String tonGiao = "";
                int soCMND = 0;
                String hoKhauThuongTru = "";
                String choOHienTai = "";
                String dienThoai = "";
                String Email = "";
                String donViCongTac = "";
                String hocVi = "";
                int namNhanHocVi = 0;
                String chucDanhKhoaHoc = "";
                int namBoNhiem = 0;
                String linkanh = "";
                if (response != null && response.length() != 2) {
                    giangVienArrayList.clear();
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            MaGV = jsonObject.getString("maGV");
                            hoTenLot = jsonObject.getString("hoTenLot");
                            Ten = jsonObject.getString("ten");
                            NgaySinh = jsonObject.getString("ngaySinh");
                            gioiTinh = jsonObject.getString("gioiTinh");
                            queQuan = jsonObject.getString("queQuan");
                            danToc = jsonObject.getString("danToc");
                            tonGiao = jsonObject.getString("tonGiao");
                            soCMND = jsonObject.getInt("soCMND");
                            hoKhauThuongTru = jsonObject.getString("hoKhauThuongTru");
                            choOHienTai = jsonObject.getString("choOHienTai");
                            dienThoai = jsonObject.getString("dienThoai");
                            Email = jsonObject.getString("Email");
                            donViCongTac = jsonObject.getString("donViCongTac");
                            hocVi = jsonObject.getString("hocVi");
                            namNhanHocVi = jsonObject.getInt("namNhanHocVi");
                            chucDanhKhoaHoc = jsonObject.getString("chucDanhKhoaHoc");
                            namBoNhiem = jsonObject.getInt("namBoNhiem");
                            linkanh = jsonObject.getString("linkanh");
                            giangVienArrayList.add(new GiangVien(id, MaGV, hoTenLot, Ten, NgaySinh, gioiTinh,queQuan,danToc,tonGiao
                                    ,soCMND,hoKhauThuongTru,choOHienTai,dienThoai,Email,donViCongTac,hocVi,namNhanHocVi,chucDanhKhoaHoc,
                                    namBoNhiem,linkanh ));
                            giangVienAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                giangVienAdapter.notifyDataSetChanged();
                pDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GiangVienActivity3.this, "Lỗi"+error, Toast.LENGTH_SHORT).show();
                Log.d("err",error+"");
            }
        }
        );
        requestQueue.add(stringRequest);
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
    public void XoaSinhVien(final int idsv){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlxoadata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(getApplicationContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    DocDuLieu(urlgetdata);
                }else {
                    Toast.makeText(getApplicationContext(), "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA","lỖI!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",String.valueOf(idsv));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void timkiem(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0 ;
                String MaGV = "";
                String hoTenLot = "";
                String Ten = "";
                String gioiTinh = "";
                String NgaySinh = "";
                String queQuan = "";
                String danToc = "";
                String tonGiao = "";
                int soCMND = 0;
                String hoKhauThuongTru = "";
                String choOHienTai = "";
                String dienThoai = "";
                String Email = "";
                String donViCongTac = "";
                String hocVi = "";
                int namNhanHocVi = 0;
                String chucDanhKhoaHoc = "";
                int namBoNhiem = 0;
                String linkanh = "";
                giangVienArrayList.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        id = jsonObject.getInt("id");
                        MaGV = jsonObject.getString("maGV");
                        hoTenLot = jsonObject.getString("hoTenLot");
                        Ten = jsonObject.getString("ten");
                        NgaySinh = jsonObject.getString("ngaySinh");
                        gioiTinh = jsonObject.getString("gioiTinh");
                        queQuan = jsonObject.getString("queQuan");
                        danToc = jsonObject.getString("danToc");
                        tonGiao = jsonObject.getString("tonGiao");
                        soCMND = jsonObject.getInt("soCMND");
                        hoKhauThuongTru = jsonObject.getString("hoKhauThuongTru");
                        choOHienTai = jsonObject.getString("choOHienTai");
                        dienThoai = jsonObject.getString("dienThoai");
                        Email = jsonObject.getString("Email");
                        donViCongTac = jsonObject.getString("donViCongTac");
                        hocVi = jsonObject.getString("hocVi");
                        namNhanHocVi = jsonObject.getInt("namNhanHocVi");
                        chucDanhKhoaHoc = jsonObject.getString("chucDanhKhoaHoc");
                        namBoNhiem = jsonObject.getInt("namBoNhiem");
                        linkanh = jsonObject.getString("linkanh");
                        giangVienArrayList.add(new GiangVien(id, MaGV, hoTenLot, Ten, NgaySinh, gioiTinh,queQuan,danToc,tonGiao
                                ,soCMND,hoKhauThuongTru,choOHienTai,dienThoai,Email,donViCongTac,hocVi,namNhanHocVi,chucDanhKhoaHoc,
                                namBoNhiem,linkanh ));
                        giangVienAdapter.notifyDataSetChanged();

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
//                    startActivity(new Intent(ThemSinhVien.this,SinhVien.class));
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GiangVienActivity3.this, "Xẩy ra lỗi!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA","lỖI!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("ten",edt3.getText().toString().trim());
                Log.d("AAA","ten!"+edt3.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
