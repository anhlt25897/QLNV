package com.qlnhansu.app.quanlynhansu.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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
import com.qlnhansu.app.quanlynhansu.Adapter.QuanLyKhoaHocAdapter;
import com.qlnhansu.app.quanlynhansu.CheckUrl.UrlConnect;
import com.qlnhansu.app.quanlynhansu.Model.KhoaHoc;
import com.qlnhansu.app.quanlynhansu.R;
import com.qlnhansu.app.quanlynhansu.SesionLogin.SQLiteHandler;
import com.qlnhansu.app.quanlynhansu.SesionLogin.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuanLyKhoaHoc extends AppCompatActivity {
    RecyclerView recyclerView;
    QuanLyKhoaHocAdapter quanLyKhoaHocAdapter;
    ArrayList<KhoaHoc> khoaHocArrayList;
    String urlgetdatakh = UrlConnect.getDataKhGV;
    ProgressDialog pDialog;
    String keyloading = "";
    private SQLiteHandler db;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_khoa_hoc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarqlkhoahoc);
        setSupportActionBar(toolbar);
        TextView titleListUser = toolbar.findViewById(R.id.title_qlkhoahoc);
        titleListUser.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_32dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recyQLKH);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        khoaHocArrayList = new ArrayList<>();
        quanLyKhoaHocAdapter = new QuanLyKhoaHocAdapter(khoaHocArrayList,QuanLyKhoaHoc.this);
        recyclerView.setAdapter(quanLyKhoaHocAdapter);
        keyloading =getIntent().getStringExtra("keyloadkh");
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabqlkh);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanLyKhoaHoc.this,ThemQLKhoaHocActivity.class);
                HashMap<String, String> user = db.getUserDetails();
                String email = user.get("email");
                intent.putExtra("keyaddqlkh",email);
                startActivity(intent);
            }
        });
        DocDuLieu(urlgetdatakh);
    }
    private void DocDuLieu(String url) {
        pDialog = new ProgressDialog(QuanLyKhoaHoc.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Đang tải dữ liệu...");
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(QuanLyKhoaHoc.this);
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
                            quanLyKhoaHocAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                quanLyKhoaHocAdapter.notifyDataSetChanged();
                pDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(QuanLyKhoaHoc.this, "Lỗi"+error, Toast.LENGTH_SHORT).show();
                Log.d("err",error+"");
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("keyView",keyloading);
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
