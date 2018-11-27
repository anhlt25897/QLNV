package com.qlnhansu.app.quanlynhansu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qlnhansu.app.quanlynhansu.CheckUrl.UrlConnect;
import com.qlnhansu.app.quanlynhansu.Model.GiangVien;
import com.qlnhansu.app.quanlynhansu.Model.KhoaHoc;
import com.qlnhansu.app.quanlynhansu.R;
import com.qlnhansu.app.quanlynhansu.SesionLogin.SQLiteHandler;
import com.qlnhansu.app.quanlynhansu.SesionLogin.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CapNhatQLKHActivity extends AppCompatActivity {
    String urlcnqlkh = UrlConnect.capnhatqlkh;
    String urlgetqlkh = UrlConnect.timkiemqlkh;
    EditText editHoTenLot,editTen,edithoiNghi,editcongTrinh,editdeTai,editgiaoTrinh,editbaiBao,
            editsach,editlinkanh;
    Button bntCapNhat,bntreaload;
    String keyloadqlkh = "";
    int id = 0;
    private SQLiteHandler db;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_qlkh);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarcnqlkh);
        setSupportActionBar(toolbar);
        TextView titleListUser = toolbar.findViewById(R.id.title_cnqlkh);
        titleListUser.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_32dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editHoTenLot = (EditText) findViewById(R.id.cnhoTenLot);
        editTen = (EditText) findViewById(R.id.cnten);
        edithoiNghi = (EditText) findViewById(R.id.cnhoinghi);
        editcongTrinh = (EditText) findViewById(R.id.cncongtrinh);
        editdeTai = (EditText) findViewById(R.id.cndetai);
        editgiaoTrinh = (EditText) findViewById(R.id.cngiaotrinh);
        editbaiBao = (EditText) findViewById(R.id.cnbaibao);
        editsach = (EditText) findViewById(R.id.cnsach);
        editlinkanh = (EditText) findViewById(R.id.cnlinkanh);
        bntCapNhat = (Button) findViewById(R.id.btncnqlkh);
        bntreaload = (Button) findViewById(R.id.btnreload);
        Intent intent = getIntent();
        KhoaHoc khoaHoc = (KhoaHoc) intent.getSerializableExtra("dataQLKH");
        id = khoaHoc.getId();
        editHoTenLot.setText(khoaHoc.getHoTenLot());
        editTen.setText(khoaHoc.getTen());
        edithoiNghi.setText(khoaHoc.getHoNghi());
        editcongTrinh.setText(khoaHoc.getCongTrinh());
        editdeTai.setText(khoaHoc.getDeTai());
        editgiaoTrinh.setText(khoaHoc.getDeTai());
        editbaiBao.setText(khoaHoc.getBaiBao());
        editsach.setText(khoaHoc.getSach());
        editlinkanh.setText(khoaHoc.getLinkanh());
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();
        keyloadqlkh = user.get("email");
        bntreaload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timkiem(urlgetqlkh);
            }
        });
        bntCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edithoiNghi.getText().toString().trim().contains("http") || !editcongTrinh.getText().toString().trim().contains("http") ||
                        !editdeTai.getText().toString().trim().contains("http") || !editgiaoTrinh.getText().toString().trim().contains("http") ||
                        !editbaiBao.getText().toString().trim().contains("http") || !editsach.getText().toString().trim().contains("http")){
                    Toast.makeText(CapNhatQLKHActivity.this, "Vui lòng điền các trường là đường dẫn link", Toast.LENGTH_SHORT).show();
                }else {
                    capnhatqlkh(urlcnqlkh);
                }
            }
        });
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
    private void timkiem(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(CapNhatQLKHActivity.this);
        //truyền đường dẫn
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        editHoTenLot.setText(jsonObject.getString("hoTenLot"));
                        editTen.setText(jsonObject.getString("ten"));
                        editlinkanh.setText(jsonObject.getString("linkanh"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CapNhatQLKHActivity.this, "Lỗi"+error, Toast.LENGTH_SHORT).show();
                Log.d("err",error+"");
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("Email",keyloadqlkh);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void capnhatqlkh(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(CapNhatQLKHActivity.this, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(ThemSinhVien.this,SinhVien.class));
                    finish();
                }else {
                    Toast.makeText(CapNhatQLKHActivity.this, "Cập Nhật không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CapNhatQLKHActivity.this, "Xẩy ra lỗi!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA","lỖI!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                params.put("hoTenLot",editHoTenLot.getText().toString().trim());
                params.put("ten",editTen.getText().toString().trim());
                params.put("hoiNghi",edithoiNghi.getText().toString().trim());
                params.put("congTrinh",editcongTrinh.getText().toString().trim());
                params.put("deTai",editdeTai.getText().toString().trim());
                params.put("giaoTrinh",editgiaoTrinh.getText().toString().trim());
                params.put("baiBao",editbaiBao.getText().toString().trim());
                params.put("sach",editsach.getText().toString().trim());
                params.put("linkanh",editlinkanh.getText().toString().trim());
                params.put("keyView",keyloadqlkh);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
