package com.qlnhansu.app.quanlynhansu.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.qlnhansu.app.quanlynhansu.ConfigUpload.FilePath;
import com.qlnhansu.app.quanlynhansu.R;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ThemQLKhoaHocActivity extends AppCompatActivity implements View.OnClickListener {
    String urlthemqlkh = UrlConnect.themKhGV;
    String urlgetqlkh = UrlConnect.timkiemqlkh;
    EditText editHoTenLot,editTen,edithoiNghi,editcongTrinh,editdeTai,editgiaoTrinh,editbaiBao,
            editsach,editlinkanh;
    Button bntThem;
    String keyloadkh = "";
    //-----------------------------
    Button btnCongtrinh, btnDetai, btnGiaotrinh, btnBaibao, btnSach, btnHoinghi;
    //Pdf request code
    private int PICK_PDF_REQUEST = 1;
    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;
    Uri filePath;
    List<Uri> uris = new ArrayList<>();
    private static final int REQ_HN = 90;
    private static final int REQ_CT = 91;
    private static final int REQ_GT = 92;
    private static final int REQ_BB = 93;
    private static final int REQ_S = 94;
    private static final int REQ_DT = 95;
    //-----------------------------

    public void setViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarthemqlkh);
        setSupportActionBar(toolbar);
        TextView titleListUser = toolbar.findViewById(R.id.title_themqlkh);
        titleListUser.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_32dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editHoTenLot = (EditText) findViewById(R.id.addhoTenLot);
        editTen = (EditText) findViewById(R.id.addten);
        edithoiNghi = (EditText) findViewById(R.id.addhoinghi);
        editcongTrinh = (EditText) findViewById(R.id.addcongtrinh);
        editdeTai = (EditText) findViewById(R.id.adddetai);
        editgiaoTrinh = (EditText) findViewById(R.id.addgiaotrinh);
        editbaiBao = (EditText) findViewById(R.id.addbaibao);
        editsach = (EditText) findViewById(R.id.addsach);
        editlinkanh = (EditText) findViewById(R.id.addlinkanh);
        bntThem = (Button) findViewById(R.id.btnThemqlkh);
        keyloadkh =getIntent().getStringExtra("keyaddqlkh");
        //--------------------------------------------------------
        btnDetai = (Button) findViewById(R.id.btnDeTai);
        btnCongtrinh = (Button) findViewById(R.id.btnCongTrinh);
        btnGiaotrinh = (Button) findViewById(R.id.btnGiaoTrinh);
        btnBaibao = (Button) findViewById(R.id.btnBaiBao);
        btnSach = (Button) findViewById(R.id.btnSach);
        btnHoinghi = (Button) findViewById(R.id.btnHoiNghi);
        //--------------------------------------------------------
    }

    public void setEvents() {
        timkiem(urlgetqlkh);
        bntThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(!edithoiNghi.getText().toString().trim().contains("http") || !editcongTrinh.getText().toString().trim().contains("http") ||
//                        !editdeTai.getText().toString().trim().contains("http") || !editgiaoTrinh.getText().toString().trim().contains("http") ||
//                        !editbaiBao.getText().toString().trim().contains("http") || !editsach.getText().toString().trim().contains("http")){
//                    Toast.makeText(ThemQLKhoaHocActivity.this, "Vui lòng điền các trường là đường dẫn link", Toast.LENGTH_SHORT).show();
//                }else {
                themqlkh(urlthemqlkh);
//                }
            }
        });
        btnSach.setOnClickListener(this);
        btnBaibao.setOnClickListener(this);
        btnGiaotrinh.setOnClickListener(this);
        btnCongtrinh.setOnClickListener(this);
        btnDetai.setOnClickListener(this);
        btnHoinghi.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_qlkhoa_hoc);
        setViews();
        requestStoragePermission();
        setEvents();
        Log.d("data", editTen.getText().toString());
        Log.d("data", editHoTenLot.getText().toString());
        Log.d("data", editlinkanh.getText().toString());
        Log.d("data", keyloadkh);
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
        RequestQueue requestQueue = Volley.newRequestQueue(ThemQLKhoaHocActivity.this);
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
                            Log.d("data", String.valueOf(jsonObject.getString("hoTenLot")));
                            Log.d("data", String.valueOf(jsonObject.getString("ten")));
                            Log.d("data", String.valueOf(jsonObject.getString("linkanh")));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThemQLKhoaHocActivity.this, "Lỗi"+error, Toast.LENGTH_SHORT).show();
                Log.d("err",error+"");
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("Email",keyloadkh);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void themqlkh(String url){
        if (uris.size() > 0) {
            uploadMultipart(url);
            RequestQueue requestQueue = Volley.newRequestQueue(ThemQLKhoaHocActivity.this);
            //truyền đường dẫn
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.trim().equals("success")) {
                        Toast.makeText(getApplicationContext(), "Thêm thành công" ,Toast.LENGTH_SHORT);
                    } else {
                        Toast.makeText(getApplicationContext(), "Lỗi" ,Toast.LENGTH_SHORT);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ThemQLKhoaHocActivity.this, "Lỗi"+error, Toast.LENGTH_SHORT).show();
                    Log.d("err",error+"");
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> params = new HashMap<String, String>();
                    params.put("hoTenLot",editHoTenLot.getText().toString().trim());
                    params.put("linkanh",editlinkanh.getText().toString().trim());
                    params.put("ten",editTen.getText().toString().trim());
                    params.put("keyView",keyloadkh);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        } else {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.trim().equals("success")){
                        Toast.makeText(ThemQLKhoaHocActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(ThemSinhVien.this,SinhVien.class));
                        finish();
                    }else {
                        Toast.makeText(ThemQLKhoaHocActivity.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ThemQLKhoaHocActivity.this, "Xẩy ra lỗi!", Toast.LENGTH_SHORT).show();
                            Log.d("AAA","lỖI!\n"+error.toString());
                        }
                    }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("hoTenLot",editHoTenLot.getText().toString().trim());
                    params.put("ten",editTen.getText().toString().trim());
                    params.put("hoiNghi",edithoiNghi.getText().toString().trim());
                    params.put("congTrinh",editcongTrinh.getText().toString().trim());
                    params.put("deTai",editdeTai.getText().toString().trim());
                    params.put("giaoTrinh",editgiaoTrinh.getText().toString().trim());
                    params.put("baiBao",editbaiBao.getText().toString().trim());
                    params.put("sach",editsach.getText().toString().trim());
                    params.put("linkanh",editlinkanh.getText().toString().trim());
                    params.put("keyView",keyloadkh);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnBaiBao:
                showFileChooser(REQ_BB);
                break;
            case R.id.btnCongTrinh:
                showFileChooser(REQ_CT);
                break;
            case R.id.btnGiaoTrinh:
                showFileChooser(REQ_GT);
                break;
            case R.id.btnDeTai:
                showFileChooser(REQ_DT);
                break;
            case R.id.btnSach:
                showFileChooser(REQ_S);
                break;
            case R.id.btnHoiNghi:
                showFileChooser(REQ_HN);
                break;
        }
    }

    public void uploadMultipart(String url) {
        //getting name for the image
//        String name = editText.getText().toString().trim();

        //getting the actual path of the image
//        String path = FilePath.getPath(this, filePath);
        List<String> paths = new ArrayList<>();
        for (int i=0; i <uris.size(); i++) {
            String path = FilePath.getPath(this, uris.get(i));
            paths.add(path);
            Log.d("Data", path);
            Log.d("Data", String.valueOf(paths.size()));
        }
        if (paths == null) {

            Toast.makeText(this, "Please move your .pdf file to internal storage and retry", Toast.LENGTH_LONG).show();
        } else {
            //Uploading code
            try {
                String uploadId = UUID.randomUUID().toString();
                //Creating a multi part request
                new MultipartUploadRequest(this, uploadId, url)
                        .addFileToUpload(paths.get(0), "hoiNghi") //Adding file
                        .addFileToUpload(paths.get(1), "congTrinh") //Adding file
                        .addFileToUpload(paths.get(2), "deTai") //Adding file
                        .addFileToUpload(paths.get(3), "giaoTrinh") //Adding file
                        .addFileToUpload(paths.get(4), "baiBao") //Adding file
                        .addFileToUpload(paths.get(5), "sach") //Adding file
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload

            } catch (Exception exc) {
                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //method to show file chooser
    private void showFileChooser(int requestCode) {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), requestCode);
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null)
        {

            String displayName;
            switch (requestCode) {
                case REQ_BB:
                    displayName = setDisplayName(data);
                    editbaiBao.setText(displayName);
                    break;
                case REQ_HN:
                    displayName = setDisplayName(data);
                    edithoiNghi.setText(displayName);
                    break;
                case REQ_S:
                    displayName = setDisplayName(data);
                    editsach.setText(displayName);
                    break;
                case REQ_GT:
                    displayName = setDisplayName(data);
                    editgiaoTrinh.setText(displayName);
                    break;
                case REQ_CT:
                    displayName = setDisplayName(data);
                    editcongTrinh.setText(displayName);
                    break;
                case REQ_DT:
                    displayName = setDisplayName(data);
                    editdeTai.setText(displayName);
                    break;
            }
        }
    }

    public String setDisplayName(Intent data) {
        filePath = data.getData();
        uris.add(filePath);
        String uriString = filePath.toString();
        File file = new File(uriString);
        String path = file.getAbsolutePath();
        String displayName = null;
        if (uriString.startsWith("content://")) {
            Cursor cursor = null;
            try {
                cursor = this.getContentResolver().query(filePath, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        } else if (uriString.startsWith("file://")) {
            displayName = file.getName();
        }
        return displayName;
    }
    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
}
