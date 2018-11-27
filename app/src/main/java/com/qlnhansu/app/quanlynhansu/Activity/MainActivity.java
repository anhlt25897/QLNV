package com.qlnhansu.app.quanlynhansu.Activity;

import android.content.DialogInterface;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<>();
    CustomView customView;
    Bitmap giangVienIcon,moiquanheIcon,thongtinIcon,qlkhoahocIcon;
    private SQLiteHandler db;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarmain);
        setSupportActionBar(toolbar);
        TextView titleListUser = toolbar.findViewById(R.id.title_main);
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
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                Intent intent = new Intent(MainActivity.this, GiangVienActivity2.class);
                startActivity(intent);
                break;
            case 1:
                /*Intent intent1 = new Intent(MainActivity.this, GiangVienActivity2.class);
                startActivity(intent1);*/
                break;
            case 2:
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        this);
                // set title
                alertDialogBuilder.setTitle("Quản lý sơ yếu lí lịch");
                alertDialogBuilder.setMessage("Đăng nhập để sử dụng chức năng");
                alertDialogBuilder.setIcon(R.drawable.management);
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        session.setLogin(false);

                        db.deleteUsers();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialogBuilder.show();
                break;
            case 3:
                final AlertDialog.Builder alertDialogBuilderll = new AlertDialog.Builder(
                        this);
                // set title
                alertDialogBuilderll.setTitle("Quản lý khoa hoc");
                alertDialogBuilderll.setMessage("Đăng nhập để sử dụng chức năng");
                alertDialogBuilderll.setIcon(R.drawable.management);
                alertDialogBuilderll.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        session.setLogin(false);

                        db.deleteUsers();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialogBuilderll.show();
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.thongtin2) {
            session.setLogin(false);

            db.deleteUsers();
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);
            // set title
            alertDialogBuilder.setTitle("Đăng nhập");
            alertDialogBuilder.setMessage("Đăng nhập để quản lý");
            alertDialogBuilder.setIcon(R.drawable.login);
            alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).setPositiveButton("Login", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    session.setLogin(false);

                    db.deleteUsers();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
            alertDialogBuilder.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
