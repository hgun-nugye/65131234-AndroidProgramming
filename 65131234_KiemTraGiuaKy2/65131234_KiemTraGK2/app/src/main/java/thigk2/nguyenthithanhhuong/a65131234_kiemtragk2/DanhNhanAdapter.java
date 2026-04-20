package thigk2.nguyenthithanhhuong.a65131234_kiemtragk2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DanhNhanAdapter extends RecyclerView.Adapter<DanhNhanAdapter.ViewHolder>  {

    private List<DanhNhan> danhSach;
    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(DanhNhan item);
    }

    public DanhNhanAdapter(List<DanhNhan> danhSach, OnItemClickListener listener) {
        this.listener = listener;
        this.danhSach = danhSach;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_danh_nhan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DanhNhan item = danhSach.get(position);

        holder.img_danhnhan.setImageResource(item.getAnh());
        holder.ten.setText(item.getTen());
        holder.mota.setText(item.getQuequan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(item);
                }
                else
                    Toast.makeText(view.getContext(), "Bạn chọn: " + item.getTen(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhSach.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_danhnhan;
        TextView ten, mota;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_danhnhan  = itemView.findViewById(R.id.img_danhnhan);
            ten = itemView.findViewById(R.id.tv_ten);
            mota = itemView.findViewById(R.id.tv_mo_ta);
        }
    }
}
