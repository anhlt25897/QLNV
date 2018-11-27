package com.qlnhansu.app.quanlynhansu.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.qlnhansu.app.quanlynhansu.Adapter.CustomView;
import com.qlnhansu.app.quanlynhansu.Model.GiangVien;
import com.qlnhansu.app.quanlynhansu.Model.Item;
import com.qlnhansu.app.quanlynhansu.R;
import com.qlnhansu.app.quanlynhansu.SesionLogin.SQLiteHandler;
import com.qlnhansu.app.quanlynhansu.SesionLogin.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

public class MainUserActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<>();
    CustomView customView;
    Bitmap giangVienIcon,moiquanheIcon,thongtinIcon,qlkhoahocIcon;
    private SQLiteHandler db;
    private SessionManager session;
    TextView txtName;
    TextView txtEmail;
    Button btnLogout;
    ArrayList<GiangVien> giangVienArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainuser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarmain3);
        setSupportActionBar(toolbar);
        TextView titleListUser = toolbar.findViewById(R.id.title_main3);
        titleListUser.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        giangVienIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.giangvien);
        moiquanheIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.moiquanhe);
        thongtinIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.thongtin);
        qlkhoahocIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.quanli);
        gridArray.add(new Item(giangVienIcon, "Giảng Viên"));
        gridArray.add(new Item(moiquanheIcon, "Mối Quan Hệ"));
        gridArray.add(new Item(thongtinIcon, "Quản Lý Sơ Yếu Lí Lịch"));
        gridArray.add(new Item(qlkhoahocIcon, "Quản Lý Khoa Học"));
        gridView = (GridView) findViewById(R.id.gridView);
        customView = new CustomView(this,
                R.layout.row_img, gridArray);
        gridView.setAdapter(customView);
        gridView.setOnItemClickListener(this);
        giangVienArrayList = new ArrayList<>();
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HashMap<String, String> user = db.getUserDetails();
        String email = "";
        switch (i) {
            case 0:
                Intent intent = new Intent(MainUserActivity.this, GiangVienActivity3.class);
                startActivity(intent);
                break;
            case 2:
                Intent intent2 = new Intent(MainUserActivity.this, XemThongTin.class);
                 email = user.get("email");
                intent2.putExtra("keyuserload",email);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(MainUserActivity.this, QuanLyKhoaHoc.class);
                 email = user.get("email");
                intent3.putExtra("keyloadkh",email);
                startActivity(intent3);
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.thongtin) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);
            // set title
            alertDialogBuilder.setTitle("Thông tin");
            alertDialogBuilder.setIcon(R.drawable.thongtinhienthi);
            LayoutInflater inflater = getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.dialoghienthi, null);
            alertDialogBuilder.setView(dialogView);
            txtName = (TextView)dialogView.findViewById(R.id.namedangnhap);
            txtEmail = (TextView) dialogView.findViewById(R.id.emaildangnhap);
            btnLogout = (Button) dialogView.findViewById(R.id.btnLogout);
            final AlertDialog b = alertDialogBuilder.create();
            // SqLite database handler
            b.show();
            HashMap<String, String> user = db.getUserDetails();
            String name = user.get("name");
            String email = user.get("email");
            txtName.setText(name);
            txtEmail.setText(email);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    logoutUser();
                    b.dismiss();
                }
            });

        }
        return super.onOptionsItemSelected(item);
    }
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainUserActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
