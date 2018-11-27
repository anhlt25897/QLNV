package com.qlnhansu.app.quanlynhansu.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qlnhansu.app.quanlynhansu.Activity.ViewAdminKhoaHocActivity;
import com.qlnhansu.app.quanlynhansu.Model.GiangVien;
import com.qlnhansu.app.quanlynhansu.Model.KhoaHoc;
import com.qlnhansu.app.quanlynhansu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KhoaHocViewAdminAdapter extends RecyclerView.Adapter<KhoaHocViewAdminAdapter.ViewHolder> {
    private List<KhoaHoc> khoaHocList;
    private ViewAdminKhoaHocActivity context;

    public KhoaHocViewAdminAdapter(List<KhoaHoc> khoaHocList, ViewAdminKhoaHocActivity context) {
        this.khoaHocList = khoaHocList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_adminviewkhoahoc,viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
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
        viewHolder.bntDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xatnhanxoa(khoaHoc.getTen(),khoaHoc.getId());
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
        ImageView bntDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txthoTenLot = (TextView) itemView.findViewById(R.id.hotenlotkhad);
            txtTen = (TextView) itemView.findViewById(R.id.tenkhad);
            txtHoNghi = (TextView) itemView.findViewById(R.id.txthothaokhad);
            txtcongTrinh = (TextView) itemView.findViewById(R.id.txtcongtrinhkhad);
            txtDeTai = (TextView) itemView.findViewById(R.id.txtdetaikhad);
            txtgiaoTrinh = (TextView) itemView.findViewById(R.id.txtgiaotrinhkhad);
            txtbaiBao = (TextView) itemView.findViewById(R.id.txtbaibaokhad);
            txtSach = (TextView) itemView.findViewById(R.id.txtsachkhad);
            imgAvatarkhad = (ImageView) itemView.findViewById(R.id.imgAvatarkhad);
            bntDelete = (ImageView) itemView.findViewById(R.id.imgdeletekhad);
        }
    }
    private void xatnhanxoa(String ten ,final int id){
        String xoa = "Bạn có muốn xóa QLKH của giảng viên <font color='blue'> <Strong>"+ ten+ "</Strong></font> ra khỏi danh sách không";
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(context);
        dialogxoa.setTitle("Xóa Giảng Viên");
        dialogxoa.setIcon(R.drawable.dete);
        dialogxoa.setMessage(Html.fromHtml(xoa));
        dialogxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.XoaQLKH(id);

            }
        });
        dialogxoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogxoa.show();
    }
}
