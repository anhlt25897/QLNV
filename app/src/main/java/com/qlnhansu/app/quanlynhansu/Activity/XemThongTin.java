package com.qlnhansu.app.quanlynhansu.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
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
import com.qlnhansu.app.quanlynhansu.Adapter.XemThongTinAdapter;
import com.qlnhansu.app.quanlynhansu.CheckUrl.UrlConnect;
import com.qlnhansu.app.quanlynhansu.Model.GiangVien;
import com.qlnhansu.app.quanlynhansu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XemThongTin extends AppCompatActivity {
    String urlgetdatachitiet = UrlConnect.getdatachitiet;
    String urlxoadata = UrlConnect.xoaGV;
    String urltimkiemdata = UrlConnect.timkiemGV;
    ProgressDialog pDialog;
    ListView lssgiangvien;
    ArrayList<GiangVien> giangVienArrayList;
    XemThongTinAdapter xemThongTinAdapter;
    String keyuserloading = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_thong_tin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarxemthongtin);
        setSupportActionBar(toolbar);
        TextView titleListUser = toolbar.findViewById(R.id.title_xemthongtin);
        titleListUser.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_32dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lssgiangvien = (ListView) findViewById(R.id.listViewxemthongtin);
        giangVienArrayList = new ArrayList<>();
        xemThongTinAdapter = new XemThongTinAdapter(XemThongTin.this, giangVienArrayList);
        lssgiangvien.setAdapter(xemThongTinAdapter);
        keyuserloading =getIntent().getStringExtra("keyuserload");
        Log.d("giatrikey",keyuserloading);
        DocDuLieu(urlgetdatachitiet);
    }
    private void DocDuLieu(String url) {
        pDialog = new ProgressDialog(XemThongTin.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Đang tải dữ liệu...");
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(XemThongTin.this);
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
                            xemThongTinAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                xemThongTinAdapter.notifyDataSetChanged();
                pDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(XemThongTin.this, "Lỗi"+error, Toast.LENGTH_SHORT).show();
                Log.d("err",error+"");
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("Email",keyuserloading);
                return param;
            }
        };
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
}
