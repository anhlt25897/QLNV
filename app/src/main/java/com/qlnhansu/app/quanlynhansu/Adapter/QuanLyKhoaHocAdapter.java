package com.qlnhansu.app.quanlynhansu.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qlnhansu.app.quanlynhansu.Activity.CapNhatActivity;
import com.qlnhansu.app.quanlynhansu.Activity.CapNhatQLKHActivity;
import com.qlnhansu.app.quanlynhansu.Activity.QuanLyKhoaHoc;
import com.qlnhansu.app.quanlynhansu.Activity.ViewAdminKhoaHocActivity;
import com.qlnhansu.app.quanlynhansu.Model.KhoaHoc;
import com.qlnhansu.app.quanlynhansu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuanLyKhoaHocAdapter extends RecyclerView.Adapter<QuanLyKhoaHocAdapter.ViewHolder> {
    private List<KhoaHoc> khoaHocList;
    private QuanLyKhoaHoc context;

    public QuanLyKhoaHocAdapter(List<KhoaHoc> khoaHocList, QuanLyKhoaHoc context) {
        this.khoaHocList = khoaHocList;
        this.context = context;
    }

    @NonNull
    @Override
    public QuanLyKhoaHocAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_viewkhoahoc,viewGroup, false);
        return new QuanLyKhoaHocAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanLyKhoaHocAdapter.ViewHolder viewHolder, int i) {
        final KhoaHoc khoaHoc = khoaHocList.get(i);
        viewHolder.txthoTenLot.setMaxLines(1);
        viewHolder.txthoTenLot.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txthoTenLot.setText(khoaHoc.getHoTenLot());
        viewHolder.txtTen.setMaxLines(1);
        viewHolder.txtTen.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtTen.setText(khoaHoc.getTen());
        viewHolder.txtHoNghi.setMaxLines(1);
        viewHolder.txtHoNghi.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtHoNghi.setText(khoaHoc.getHoNghi());
        viewHolder.txtcongTrinh.setMaxLines(1);
        viewHolder.txtcongTrinh.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtcongTrinh.setText(khoaHoc.getCongTrinh());
        viewHolder.txtDeTai.setMaxLines(1);
        viewHolder.txtDeTai.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtDeTai.setText(khoaHoc.getDeTai());
        viewHolder.txtgiaoTrinh.setMaxLines(1);
        viewHolder.txtgiaoTrinh.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtgiaoTrinh.setText(khoaHoc.getGiaoTrinh());
        viewHolder.txtbaiBao.setMaxLines(1);
        viewHolder.txtbaiBao.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtbaiBao.setText(khoaHoc.getBaiBao());
        viewHolder.txtSach.setMaxLines(1);
        viewHolder.txtSach.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtSach.setText(khoaHoc.getSach());
        Picasso.with(context.getBaseContext()).load(khoaHoc.getLinkanh()).into(viewHolder.imgAvatarkhad);
        viewHolder.bntedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , CapNhatQLKHActivity.class);
                intent.putExtra("dataQLKH", khoaHoc);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return khoaHocList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txthoTenLot,txtTen,txtHoNghi,txtcongTrinh,txtDeTai,txtgiaoTrinh,txtbaiBao,txtSach;
        ImageView imgAvatarkhad;
        ImageView bntDelete , bntedit , bntviewpulic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txthoTenLot = (TextView) itemView.findViewById(R.id.hotenlot);
            txtTen = (TextView) itemView.findViewById(R.id.tenkh);
            txtHoNghi = (TextView) itemView.findViewById(R.id.txthothaokh);
            txtcongTrinh = (TextView) itemView.findViewById(R.id.txtcongtrinhkh);
            txtDeTai = (TextView) itemView.findViewById(R.id.txtdetaikh);
            txtgiaoTrinh = (TextView) itemView.findViewById(R.id.txtgiaotrinhkh);
            txtbaiBao = (TextView) itemView.findViewById(R.id.txtbaibaokh);
            txtSach = (TextView) itemView.findViewById(R.id.txtsachkh);
            imgAvatarkhad = (ImageView) itemView.findViewById(R.id.imgAvatarkh);
            bntDelete = (ImageView) itemView.findViewById(R.id.imgdelete);
            bntedit = (ImageView) itemView.findViewById(R.id.imgedit);
            bntviewpulic = (ImageView) itemView.findViewById(R.id.imgpulic);
        }
    }
}
