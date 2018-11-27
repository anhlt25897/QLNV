package com.qlnhansu.app.quanlynhansu.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qlnhansu.app.quanlynhansu.CheckUrl.UrlConnect;
import com.qlnhansu.app.quanlynhansu.Model.GiangVien;
import com.qlnhansu.app.quanlynhansu.R;
import com.qlnhansu.app.quanlynhansu.SesionLogin.SQLiteHandler;
import com.qlnhansu.app.quanlynhansu.SesionLogin.SessionManager;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ThemActivity extends AppCompatActivity {
    String urlthemgv = UrlConnect.themGV;
    EditText editmaGV,editHoTenLot,editTen,editNgaySinh,editquanQuan,editdanToc,edittonGiao,editsoCMND,
            edithktt,editcoht,editdienThoai,editEmail,editdvct,edithocVi,editnamNhanHocVi,editcdkh
            ,editnamBoNhiem,editlinkAnh;
    Button set,bntThem,bntThoat;
    Dialog picker;
    DatePicker datep;
    Integer month, day, year;
    RadioButton radioButtonNam;
    RadioButton radioButtonNu;
    RadioGroup radioGroupDiffLevel;
    String userGender = "";
    //TODO: Chọn ảnh từ thư viện
    Button btnAvatar;
    ImageView avatarThumbnail;
    Bitmap bitmap;
    private static final int REQ_AVATAR = 123;
    private static final int PERMISSION_REQUEST_CODE = 1;
    //------------------------------
    public void setViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarthemgv);
        setSupportActionBar(toolbar);
        TextView titleListUser = toolbar.findViewById(R.id.title_themgv);
        titleListUser.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_32dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editmaGV = (EditText) findViewById(R.id.editTextMaGV);
        editHoTenLot = (EditText) findViewById(R.id.edithoTenLot);
        editTen = (EditText) findViewById(R.id.editen);
        editNgaySinh = (EditText) findViewById(R.id.editngaysinh);
        editquanQuan = (EditText) findViewById(R.id.editquequan);
        editdanToc = (EditText) findViewById(R.id.editdantoc);
        edittonGiao = (EditText) findViewById(R.id.edittongiao);
        editsoCMND = (EditText) findViewById(R.id.editsocmnd);
        edithktt = (EditText) findViewById(R.id.edithktt);
        editcoht = (EditText) findViewById(R.id.editcoht);
        editdienThoai = (EditText) findViewById(R.id.editdienthoai);
        editEmail = (EditText) findViewById(R.id.editemail);
        editdvct = (EditText) findViewById(R.id.editdvct);
        edithocVi = (EditText) findViewById(R.id.edithocvi);
        editnamNhanHocVi = (EditText) findViewById(R.id.editnamnhv);
        editcdkh = (EditText) findViewById(R.id.editcdkh);
        editnamBoNhiem = (EditText) findViewById(R.id.editnambonhiem);
        editlinkAnh = (EditText) findViewById(R.id.editlinkanh);
        bntThem = (Button) findViewById(R.id.bntthem);
        bntThoat = (Button) findViewById(R.id.bntthoat);
        radioButtonNam = (RadioButton) this.findViewById(R.id.radioButton_nam);
        radioButtonNu = (RadioButton) this.findViewById(R.id.radioButton_nu);
        radioGroupDiffLevel = (RadioGroup) this.findViewById(R.id.radioGroup_diffLevel);
        btnAvatar = (Button) findViewById(R.id.btn_get_avatar);
        avatarThumbnail = (ImageView) findViewById(R.id.img_avatar_thumbnail);
    }

    public void setEvents() {
        //region Chọn ngày sinh
        editNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker = new Dialog(ThemActivity.this);
                picker.setContentView(R.layout.ngaygio);
                picker.setTitle("Chọn ngày tháng năm sinh");
                datep = (DatePicker) picker.findViewById(R.id.datePicker);
                set = (Button) picker.findViewById(R.id.btnSet);
                //picker thời gian
                set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO Auto-generated method stub
                        month = datep.getMonth();
                        day = datep.getDayOfMonth();
                        year = datep.getYear();
                        editNgaySinh.setText(year + "-" + month + "-"
                                + day);
                        picker.dismiss();
                    }
                });
                picker.show();
            }
        });
        //endregion
        bntThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //region Thêm giảng viên
        bntThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maGV = editmaGV.getText().toString().trim();
                String hoTenLot = editHoTenLot.getText().toString().trim();
                String ten = editTen.getText().toString().trim();
                String ngaysinh = editNgaySinh.getText().toString().trim();
                String quequan = editquanQuan.getText().toString().trim();
                String dantoc = editdanToc.getText().toString().trim();
                String tongiao = edittonGiao.getText().toString().trim();
                int soCMND =Integer.parseInt(editsoCMND.getText().toString().trim());
                String hktt = edithktt.getText().toString().trim();
                String coht = editcoht.getText().toString().trim();
                String dienthoai = editdienThoai.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                String dvct = editdvct.getText().toString().trim();
                int nambonhiem =Integer.parseInt(editnamBoNhiem.getText().toString().trim());
                String linkanh = editlinkAnh.getText().toString().trim();
                String hocvi = edithocVi.getText().toString().trim();
                int namnhanhocvi = Integer.parseInt(editnamNhanHocVi.getText().toString().trim());
                String cdkh = editcdkh.getText().toString().trim();
                if (maGV.equals("") || hoTenLot.equals("") || ten.equals("") || quequan.equals("") || ngaysinh.equals("")
                        || dantoc.equals("") || tongiao.equals("") || editsoCMND.equals("") || hktt.equals("") || coht.equals("")
                        || dienthoai.equals("") || email.equals("") || dvct.equals("") || editnamBoNhiem.equals("") || hocvi.equals("")
                        || editnamNhanHocVi.equals("") || cdkh.equals("") || userGender.isEmpty()) {
                    Toast.makeText(ThemActivity.this, "Chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    themgiangvien(urlthemgv);
                }
            }
        });
        //endregion
        btnAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQ_AVATAR);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        setViews();
        bitmap = BitmapFactory.decodeResource(getResources(), R.id.img_avatar_thumbnail);
        setEvents();
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission();
            }
        }

    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(ThemActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(ThemActivity.this, " Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(ThemActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(ThemActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ThemActivity.this, "Permission Granted Successfully! ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ThemActivity.this, "Permission Denied 🙁 ", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    public void RadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButton_nam:
                if (checked)
                    userGender = "Nam";
                break;
            case R.id.radioButton_nu:
                if (checked)
                    userGender = "Nữ";
                break;
        }
    }
    private void themgiangvien(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(ThemActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(ThemSinhVien.this,SinhVien.class));
                    finish();
                }else {
                    Toast.makeText(ThemActivity.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ThemActivity.this, "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA","lỖI!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //region Set params
                Map<String,String> params = new HashMap<>();
                params.put("maGV",editmaGV.getText().toString().trim());
                params.put("hoTenLot",editHoTenLot.getText().toString().trim());
                params.put("ten",editTen.getText().toString().trim());
                params.put("ngaySinh",editNgaySinh.getText().toString().trim());
                params.put("gioiTinh",userGender);
                params.put("queQuan",editquanQuan.getText().toString().trim());
                params.put("danToc",editdanToc.getText().toString().trim());
                params.put("tonGiao",edittonGiao.getText().toString().trim());
                params.put("soCMND",editsoCMND.getText().toString().trim());
                params.put("hoKhauThuongTru",edithktt.getText().toString().trim());
                params.put("choOHienTai",editcoht.getText().toString().trim());
                params.put("dienThoai",editdienThoai.getText().toString().trim());
                params.put("Email",editEmail.getText().toString().trim());
                params.put("donViCongTac",editdvct.getText().toString().trim());
                params.put("hocVi",edithocVi.getText().toString().trim());
                params.put("namNhanHocVi",editnamNhanHocVi.getText().toString().trim());
                params.put("chucDanhKhoaHoc",editcdkh.getText().toString().trim());
                params.put("namBoNhiem",editnamBoNhiem.getText().toString().trim());
                String imageString = getStringImage(bitmap);
                params.put("image", imageString);
                //endregion
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQ_AVATAR) {
                Uri targetUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), targetUri);
                    avatarThumbnail.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getStringImage(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}
