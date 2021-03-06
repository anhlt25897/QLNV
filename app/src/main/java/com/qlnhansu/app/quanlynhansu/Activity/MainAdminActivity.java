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
import com.qlnhansu.app.quanlynhansu.Model.Item;
import com.qlnhansu.app.quanlynhansu.R;
import com.qlnhansu.app.quanlynhansu.SesionLogin.SQLiteHandler;
import com.qlnhansu.app.quanlynhansu.SesionLogin.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

public class MainAdminActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<>();
    CustomView customView;
    Bitmap giangVienIcon,moiquanheIcon,dangkytinIcon,giangviendanghiIcon,khoahocviewIcon;
    private SQLiteHandler db;
    private SessionManager session;
    TextView txtName;
    TextView txtEmail;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainadmin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarmain2);
        setSupportActionBar(toolbar);
        TextView titleListUser = toolbar.findViewById(R.id.title_main2);
        titleListUser.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        giangVienIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.giangvien);
        moiquanheIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.moiquanhe);
        dangkytinIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.reques);
        giangviendanghiIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.giangvienvehuu);
        khoahocviewIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.khoahoc);
        gridArray.add(new Item(giangVienIcon, "Giảng Viên"));
        gridArray.add(new Item(moiquanheIcon, "Mối Quan Hệ"));
        gridArray.add(new Item(dangkytinIcon, "Tạo Tài Khoảng User"));
        gridArray.add(new Item(giangviendanghiIcon, "Giảng Viên Đã Nghỉ"));
        gridArray.add(new Item(khoahocviewIcon, "Quản Lí Khoa Học View"));
        gridView = (GridView) findViewById(R.id.gridView);
        customView = new CustomView(this,
                R.layout.row_img, gridArray);
        gridView.setAdapter(customView);
        gridView.setOnItemClickListener(this);
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                Intent intent = new Intent(MainAdminActivity.this, GiangVienAcitivity.class);
                startActivity(intent);
                break;
            case 1:
               /* Intent intent1 = new Intent(Main2Activity.this, GiangVienAcitivity.class);
                startActivity(intent1);*/
                break;
            case 2:
                Intent intent2 = new Intent(MainAdminActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(MainAdminActivity.this, GiangVienDaNghiActivity.class);
                startActivity(intent3);
                break;
            case 4:
                Intent intent4 = new Intent(MainAdminActivity.this, ViewAdminKhoaHocActivity.class);
                startActivity(intent4);
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
        Intent intent = new Intent(MainAdminActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
