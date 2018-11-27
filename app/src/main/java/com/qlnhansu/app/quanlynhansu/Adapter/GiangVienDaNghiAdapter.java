package com.qlnhansu.app.quanlynhansu.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qlnhansu.app.quanlynhansu.Activity.CapNhatActivity;
import com.qlnhansu.app.quanlynhansu.Activity.GiangVienAcitivity;
import com.qlnhansu.app.quanlynhansu.Activity.GiangVienDaNghiActivity;
import com.qlnhansu.app.quanlynhansu.Activity.SoYeuLiLichActivity;
import com.qlnhansu.app.quanlynhansu.Activity.ThemActivity;
import com.qlnhansu.app.quanlynhansu.CheckUrl.UrlConnect;
import com.qlnhansu.app.quanlynhansu.Model.GiangVien;
import com.qlnhansu.app.quanlynhansu.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiangVienDaNghiAdapter extends BaseAdapter {
    private GiangVienDaNghiActivity context;
    private int layout;
    private List<GiangVien> giangVienList;
    int id = 0;
    String maGV = "";
    String hoTenLot = "";
    String ten = "";
    String ngaySinh = "";
    String gioiTinh = "";
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
    String urlthemgv = UrlConnect.themGV;
    public GiangVienDaNghiAdapter(GiangVienDaNghiActivity context, List<GiangVien> giangVienList) {
        this.context = context;
        this.giangVienList = giangVienList;
    }

    @Override
    public int getCount() {
        return giangVienList.size();
    }

    @Override
    public Object getItem(int i) {
        return giangVienList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView txtmaGV,txtTen,txtgioiTinh,txtnamsinh;
        ImageView img ;
        Button bntxoa,bntsave,bntview;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GiangVienDaNghiAdapter.ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new GiangVienDaNghiAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.doc_giangvien_danghi,null);
            viewHolder.img = (ImageView) view.findViewById(R.id.imghinhanhgvdn);
            viewHolder.txtmaGV = (TextView) view.findViewById(R.id.txtmagiangviengvdn);
            viewHolder.txtTen = (TextView) view.findViewById(R.id.txttengiangviengvdn);
            viewHolder.txtgioiTinh = (TextView) view.findViewById(R.id.txtgioitinhgvdn);
            viewHolder.txtnamsinh = (TextView) view.findViewById(R.id.txtnamsinhgvdn);
            viewHolder.bntxoa = (Button) view.findViewById(R.id.bntxoagvdn);
            viewHolder.bntsave = (Button) view.findViewById(R.id.bntsavegvdn);
            viewHolder.bntview = (Button) view.findViewById(R.id.bntviewgvdn);
            view.setTag(viewHolder);
        }else {
            viewHolder = (GiangVienDaNghiAdapter.ViewHolder) view.getTag();
        }
        final GiangVien giangVien = giangVienList.get(i);
        viewHolder.txtmaGV.setText("Mã GV: "+giangVien.getMaGV());
        Picasso.with(context.getBaseContext()).load(giangVien.getLinkanh()).into(viewHolder.img);
        viewHolder.txtTen.setText("Tên : "+giangVien.getTen());
        viewHolder.txtgioiTinh.setText("Giới Tính: "+giangVien.getGioiTinh());
        viewHolder.txtnamsinh.setText("Ngày sinh: "+giangVien.getNgaySinh());
        viewHolder.bntview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SoYeuLiLichActivity.class);
                intent.putExtra("soyeulilich",giangVien);
                context.startActivity(intent);
            }
        });
        viewHolder.bntsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xatnhanthem(giangVien.getTen(),giangVien.getId());
                maGV = giangVien.getMaGV();
                hoTenLot = giangVien.getHoTenLot();
                ten = giangVien.getTen();
                ngaySinh = giangVien.getNgaySinh();
                gioiTinh = giangVien.getGioiTinh();
                queQuan = giangVien.getQueQuan();
                danToc = giangVien.getDanToc();
                tonGiao = giangVien.getTonGiao();
                soCMND = giangVien.getSoCMND();
                hoKhauThuongTru = giangVien.getHoKhauThuongTru();
                choOHienTai = giangVien.getChoOHienTai();
                dienThoai = giangVien.getDienThoai();
                Email = giangVien.getEmail();
                donViCongTac = giangVien.getDonViCongTac();
                hocVi = giangVien.getHocVi();
                namNhanHocVi = giangVien.getNamNhanHocVi();
                chucDanhKhoaHoc = giangVien.getChucDanhKhoaHoc();
                namBoNhiem = giangVien.getNamBoNhiem();
                linkanh = giangVien.getLinkanh();
                themgiangvien(urlthemgv);

            }
        });
        viewHolder.bntxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xatnhanxoa(giangVien.getTen(),giangVien.getId());
            }
        });
        return view;
    }
    private void xatnhanxoa(String ten ,final int id){
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(context);
        dialogxoa.setTitle("Xóa Giảng Viên");
        dialogxoa.setIcon(R.drawable.dete);
        dialogxoa.setMessage("Bạn có muốn xóa giảng viên "+ten+" ra khỏi hồ sơ lưu trữ không");
        dialogxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.XoaGiangVien(id);

            }
        });
        dialogxoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogxoa.show();
    }
    private void xatnhanthem(String ten ,final int id){
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(context);
        dialogxoa.setTitle("Thêm Giảng Viên");
        dialogxoa.setIcon(R.drawable.them);
        dialogxoa.setMessage("Bạn có muốn thêm giảng viên "+ten+" vào làm lại không");
        dialogxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.ThemGiangVien(id);

            }
        });
        dialogxoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogxoa.show();
    }
    private void themgiangvien(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {

                } else {
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Xẩy ra lỗi!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA","lỖI!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("maGV",maGV);
                params.put("hoTenLot",hoTenLot);
                params.put("ten",ten);
                params.put("ngaySinh",ngaySinh);
                params.put("gioiTinh",gioiTinh);
                params.put("queQuan",queQuan);
                params.put("danToc",danToc);
                params.put("tonGiao",tonGiao);
                params.put("soCMND",Integer.toString(soCMND));
                params.put("hoKhauThuongTru",hoKhauThuongTru);
                params.put("choOHienTai",choOHienTai);
                params.put("dienThoai",dienThoai);
                params.put("Email",Email);
                params.put("donViCongTac",donViCongTac);
                params.put("hocVi",hocVi);
                params.put("namNhanHocVi",Integer.toString(namNhanHocVi));
                params.put("chucDanhKhoaHoc",chucDanhKhoaHoc);
                params.put("namBoNhiem",Integer.toString(namBoNhiem));
                params.put("linkanh",linkanh);
                Log.d("ABC",maGV);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
