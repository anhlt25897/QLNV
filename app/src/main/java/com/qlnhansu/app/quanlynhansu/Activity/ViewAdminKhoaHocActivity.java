package com.qlnhansu.app.quanlynhansu.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qlnhansu.app.quanlynhansu.Adapter.KhoaHocViewAdminAdapter;
import com.qlnhansu.app.quanlynhansu.CheckUrl.UrlConnect;
import com.qlnhansu.app.quanlynhansu.Model.GiangVien;
import com.qlnhansu.app.quanlynhansu.Model.KhoaHoc;
import com.qlnhansu.app.quanlynhansu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewAdminKhoaHocActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    KhoaHocViewAdminAdapter khoaHocViewAdminAdapter;
    ArrayList<KhoaHoc>khoaHocArrayList;
    String urlgetdatakh = UrlConnect.getDataKh;
    String urlxoadata = UrlConnect.deleteQLKH;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_admin_khoa_hoc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarkhoahocview);
        setSupportActionBar(toolbar);
        TextView titleListUser = toolbar.findViewById(R.id.title_khoahocview);
        titleListUser.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_32dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recyKH);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        khoaHocArrayList = new ArrayList<>();
        khoaHocViewAdminAdapter = new KhoaHocViewAdminAdapter(khoaHocArrayList,ViewAdminKhoaHocActivity.this);
        recyclerView.setAdapter(khoaHocViewAdminAdapter);
        DocDuLieu(urlgetdatakh);
    }
    private void DocDuLieu(String url) {
        pDialog = new ProgressDialog(ViewAdminKhoaHocActivity.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Đang tải dữ liệu...");
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(ViewAdminKhoaHocActivity.this);
        //truyền đường dẫn
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0 ;
                String hoTenLot = "";
                String Ten = "";
                String hoiNghi = "";
                String congTrinh = "";
                String deTai = "";
                String giaoTrinh = "";
                String baiBao = "";
                String sach = "";
                String linkanh = "";
                String keyView = "";
                if (response != null && response.length() != 2) {
                    khoaHocArrayList.clear();
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            hoTenLot = jsonObject.getString("hoTenLot");
                            Ten = jsonObject.getString("ten");
                            hoiNghi = jsonObject.getString("hoiNghi");
                            congTrinh = jsonObject.getString("congTrinh");
                            deTai = jsonObject.getString("deTai");
                            giaoTrinh = jsonObject.getString("giaoTrinh");
                            baiBao = jsonObject.getString("baiBao");
                            sach = jsonObject.getString("sach");
                            linkanh = jsonObject.getString("linkanh");
                            keyView = jsonObject.getString("keyView");
                            khoaHocArrayList.add(new KhoaHoc(id,hoTenLot, Ten, hoiNghi, congTrinh,deTai,giaoTrinh,baiBao
                                    ,sach,linkanh,keyView));
                            khoaHocViewAdminAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                khoaHocViewAdminAdapter.notifyDataSetChanged();
                pDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewAdminKhoaHocActivity.this, "Lỗi"+error, Toast.LENGTH_SHORT).show();
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
    public void XoaQLKH(final int idkh){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlxoadata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(getApplicationContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    DocDuLieu(urlgetdatakh);
                }else {
                    Toast.makeText(getApplicationContext(), "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Xẩy ra lỗi!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA","lỖI!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",String.valueOf(idkh));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
