package com.qlnhansu.app.quanlynhansu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qlnhansu.app.quanlynhansu.Activity.GiangVienActivity2;
import com.qlnhansu.app.quanlynhansu.Activity.GiangVienActivity3;
import com.qlnhansu.app.quanlynhansu.Activity.SoYeuLiLichActivity;
import com.qlnhansu.app.quanlynhansu.Model.GiangVien;
import com.qlnhansu.app.quanlynhansu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GiangVienAdapter3 extends BaseAdapter {
    private GiangVienActivity3 context;
    private int layout;
    private List<GiangVien> giangVienList;

    public GiangVienAdapter3(GiangVienActivity3 context, List<GiangVien> giangVienList) {
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
        Button bntview;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GiangVienAdapter3.ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new GiangVienAdapter3.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.doc_giangvien3,null);
            viewHolder.img = (ImageView) view.findViewById(R.id.imghinhanh3);
            viewHolder.txtmaGV = (TextView) view.findViewById(R.id.txtmagiangvien3);
            viewHolder.txtTen = (TextView) view.findViewById(R.id.txttengiangvien3);
            viewHolder.txtgioiTinh = (TextView) view.findViewById(R.id.txtgioitinh3);
            viewHolder.txtnamsinh = (TextView) view.findViewById(R.id.txtnamsinh3);
            viewHolder.bntview = (Button) view.findViewById(R.id.bntview3);
            view.setTag(viewHolder);
        }else {
            viewHolder = (GiangVienAdapter3.ViewHolder) view.getTag();
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
        return view;
    }
}
