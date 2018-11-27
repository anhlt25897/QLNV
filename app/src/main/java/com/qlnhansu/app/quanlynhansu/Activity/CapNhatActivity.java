package com.qlnhansu.app.quanlynhansu.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
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
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CapNhatActivity extends AppCompatActivity {
    String urlcapnhatgv = UrlConnect.capnhatGV;
    EditText editmaGVCN, editHoTenLotCN, editTenCN, editNgaySinhCN, editquanQuanCN, editdanTocCN, edittonGiaoCN, editsoCMNDCN,
            edithkttCN, editcohtCN, editdienThoaiCN, editEmailCN, editdvctCN, edithocViCN, editnamNhanHocViCN, editcdkhCN, editnamBoNhiemCN;
    Button set, bntCapNhat, bntThoatCN;
    Dialog picker;
    DatePicker datep;
    Integer month, day, year;
    RadioButton radioButtonNam;
    RadioButton radioButtonNu;
    RadioGroup radioGroupDiffLevel;
    String userGender = "";
    int id = 0;

    //TODO: Chọn ảnh từ thư viện
    Button btnAvatar;
    ImageView avatarThumbnail;
    Bitmap bitmap;
    private static final int REQ_AVATAR = 123;
    private static final int PERMISSION_REQUEST_CODE = 1;
    //------------------------------

    public void setViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarcapnhatgv);
        setSupportActionBar(toolbar);
        TextView titleListUser = toolbar.findViewById(R.id.title_capnhatgv);
        titleListUser.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_32dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editmaGVCN = (EditText) findViewById(R.id.editTextMaGVCN);
        editHoTenLotCN = (EditText) findViewById(R.id.edithoTenLotCN);
        editTenCN = (EditText) findViewById(R.id.editenCN);
        editNgaySinhCN = (EditText) findViewById(R.id.editngaysinhCN);
        editquanQuanCN = (EditText) findViewById(R.id.editquequanCN);
        editdanTocCN = (EditText) findViewById(R.id.editdantocCN);
        edittonGiaoCN = (EditText) findViewById(R.id.edittongiaoCN);
        editsoCMNDCN = (EditText) findViewById(R.id.editsocmndCN);
        edithkttCN = (EditText) findViewById(R.id.edithkttCN);
        editcohtCN = (EditText) findViewById(R.id.editcohtCN);
        editdienThoaiCN = (EditText) findViewById(R.id.editdienthoaiCN);
        editEmailCN = (EditText) findViewById(R.id.editemailCN);
        editdvctCN = (EditText) findViewById(R.id.editdvctCN);
        edithocViCN = (EditText) findViewById(R.id.edithocviCN);
        editnamNhanHocViCN = (EditText) findViewById(R.id.editnamnhvCN);
        editcdkhCN = (EditText) findViewById(R.id.editcdkhCN);
        editnamBoNhiemCN = (EditText) findViewById(R.id.editnambonhiemCN);
        bntCapNhat = (Button) findViewById(R.id.bntcapnhat);
        bntThoatCN = (Button) findViewById(R.id.bntthoatCN);
        radioButtonNam = (RadioButton) this.findViewById(R.id.radioButton_namCN);
        radioButtonNu = (RadioButton) this.findViewById(R.id.radioButton_nuCN);
        radioGroupDiffLevel = (RadioGroup) this.findViewById(R.id.radioGroup_diffLevelCN);
        btnAvatar = (Button) findViewById(R.id.btn_get_avatar);
        avatarThumbnail = (ImageView) findViewById(R.id.img_avatar_thumbnail);
    }

    public void setData() {
        Intent intent = getIntent();
        GiangVien giangVien = (GiangVien) intent.getSerializableExtra("dataGiangVien");
        id = giangVien.getId();
        editmaGVCN.setText(giangVien.getMaGV());
        editHoTenLotCN.setText(giangVien.getHoTenLot());
        editTenCN.setText(giangVien.getTen());
        editNgaySinhCN.setText(giangVien.getNgaySinh());
        editquanQuanCN.setText(giangVien.getQueQuan());
        editdanTocCN.setText(giangVien.getDanToc());
        edittonGiaoCN.setText(giangVien.getTonGiao());
        editsoCMNDCN.setText(Integer.toString(giangVien.getSoCMND()));
        edithkttCN.setText(giangVien.getHoKhauThuongTru());
        editcohtCN.setText(giangVien.getChoOHienTai());
        editdienThoaiCN.setText(giangVien.getDienThoai());
        editEmailCN.setText(giangVien.getEmail());
        editdvctCN.setText(giangVien.getDonViCongTac());
        edithocViCN.setText(giangVien.getHocVi());
        editnamNhanHocViCN.setText(Integer.toString(giangVien.getNamNhanHocVi()));
        editcdkhCN.setText(giangVien.getChucDanhKhoaHoc());
        editnamBoNhiemCN.setText(Integer.toString(giangVien.getNamBoNhiem()));
        Picasso.with(this).load(giangVien.getLinkanh()).into(avatarThumbnail);
        if (giangVien.getGioiTinh().equalsIgnoreCase("Nam")) {
            radioButtonNam.setChecked(true);
        } else {
            radioButtonNu.setChecked(true);
        }
    }

    public void setEvents() {
        btnAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
        editNgaySinhCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker = new Dialog(CapNhatActivity.this);
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
                        editNgaySinhCN.setText(year + "-" + month + "-"
                                + day);
                        picker.dismiss();
                    }
                });
                picker.show();
            }
        });
        bntThoatCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bntCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maGV = editmaGVCN.getText().toString().trim();
                String hoTenLot = editHoTenLotCN.getText().toString().trim();
                String ten = editTenCN.getText().toString().trim();
                String ngaysinh = editNgaySinhCN.getText().toString().trim();
                String quequan = editquanQuanCN.getText().toString().trim();
                String dantoc = editdanTocCN.getText().toString().trim();
                String tongiao = edittonGiaoCN.getText().toString().trim();
                int soCMND = Integer.parseInt(editsoCMNDCN.getText().toString().trim());
                String hktt = edithkttCN.getText().toString().trim();
                String coht = editcohtCN.getText().toString().trim();
                String dienthoai = editdienThoaiCN.getText().toString().trim();
                String email = editEmailCN.getText().toString().trim();
                String dvct = editdvctCN.getText().toString().trim();
                int nambonhiem = Integer.parseInt(editnamBoNhiemCN.getText().toString().trim());

                String hocvi = edithocViCN.getText().toString().trim();
                int namnhanhocvi = Integer.parseInt(editnamNhanHocViCN.getText().toString().trim());
                String cdkh = editcdkhCN.getText().toString().trim();
                if (radioButtonNam.isChecked()){
                    userGender = "Nam";
                }
                if (radioButtonNu.isChecked()){
                    userGender = "Nữ";
                }
                if (maGV.equals("") || hoTenLot.equals("") || ten.equals("") || quequan.equals("") || ngaysinh.equals("")
                        || dantoc.equals("") || tongiao.equals("") || editsoCMNDCN.equals("") || hktt.equals("") || coht.equals("")
                        || dienthoai.equals("") || email.equals("") || dvct.equals("") || editnamBoNhiemCN.equals("") || hocvi.equals("")
                        || editnamNhanHocViCN.equals("") || cdkh.equals("")) {
                    Toast.makeText(CapNhatActivity.this, "Chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    capnhatGV(urlcapnhatgv);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat);
        setViews();
        //set dữ liệu
        setData();
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

    public void RadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButton_namCN:
                if (checked)
                    userGender = "Nam";
                break;
            case R.id.radioButton_nuCN:
                if (checked)
                    userGender = "Nữ";
                break;
        }
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

    private void capnhatGV(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {
                    Toast.makeText(CapNhatActivity.this, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(ThemSinhVien.this,SinhVien.class));
                    finish();
                } else {
                    Toast.makeText(CapNhatActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CapNhatActivity.this, "Xẩy ra lỗi!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA", "lỖI!\n" + error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                params.put("maGV", editmaGVCN.getText().toString().trim());
                params.put("hoTenLot", editHoTenLotCN.getText().toString().trim());
                params.put("ten", editTenCN.getText().toString().trim());
                params.put("ngaySinh", editNgaySinhCN.getText().toString().trim());
                params.put("gioiTinh", userGender);
                params.put("queQuan", editquanQuanCN.getText().toString().trim());
                params.put("danToc", editdanTocCN.getText().toString().trim());
                params.put("tonGiao", edittonGiaoCN.getText().toString().trim());
                params.put("soCMND", editsoCMNDCN.getText().toString().trim());
                params.put("hoKhauThuongTru", edithkttCN.getText().toString().trim());
                params.put("choOHienTai", editcohtCN.getText().toString().trim());
                params.put("dienThoai", editdienThoaiCN.getText().toString().trim());
                params.put("Email", editEmailCN.getText().toString().trim());
                params.put("donViCongTac", editdvctCN.getText().toString().trim());
                params.put("hocVi", edithocViCN.getText().toString().trim());
                params.put("namNhanHocVi", editnamNhanHocViCN.getText().toString().trim());
                params.put("chucDanhKhoaHoc", editcdkhCN.getText().toString().trim());
                params.put("namBoNhiem", editnamBoNhiemCN.getText().toString().trim());
                String imageString = getStringImage(bitmap);
                params.put("image", imageString);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQ_AVATAR);
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(CapNhatActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(CapNhatActivity.this, " Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(CapNhatActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(CapNhatActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
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
                    Toast.makeText(CapNhatActivity.this, "Permission Granted Successfully! ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CapNhatActivity.this, "Permission Denied 🙁 ", Toast.LENGTH_LONG).show();
                }
                break;
        }
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
