package com.qlnhansu.app.quanlynhansu.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qlnhansu.app.quanlynhansu.Activity.CapNhatActivity;
import com.qlnhansu.app.quanlynhansu.Activity.GiangVienAcitivity;
import com.qlnhansu.app.quanlynhansu.Activity.SoYeuLiLichActivity;
import com.qlnhansu.app.quanlynhansu.Activity.XemThongTin;
import com.qlnhansu.app.quanlynhansu.Model.GiangVien;
import com.qlnhansu.app.quanlynhansu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class XemThongTinAdapter extends BaseAdapter {
    private XemThongTin context;
    private int layout;
    private List<GiangVien> giangVienList;

    public XemThongTinAdapter(XemThongTin context, List<GiangVien> giangVienList) {
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
        Button bntsua,bntview;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        XemThongTinAdapter.ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new XemThongTinAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.doc_thongtinsylc,null);
            viewHolder.img = (ImageView) view.findViewById(R.id.imghinhanhtt);
            viewHolder.txtmaGV = (TextView) view.findViewById(R.id.txtmagiangvientt);
            viewHolder.txtTen = (TextView) view.findViewById(R.id.txttengiangvientt);
            viewHolder.txtgioiTinh = (TextView) view.findViewById(R.id.txtgioitinhtt);
            viewHolder.txtnamsinh = (TextView) view.findViewById(R.id.txtnamsinhtt);
            viewHolder.bntsua = (Button) view.findViewById(R.id.bntsuatt);
            viewHolder.bntview = (Button) view.findViewById(R.id.bntviewtt);
            view.setTag(viewHolder);
        }else {
            viewHolder = (XemThongTinAdapter.ViewHolder) view.getTag();
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
        viewHolder.bntsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , CapNhatActivity.class);
                intent.putExtra("dataGiangVien", giangVien);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
