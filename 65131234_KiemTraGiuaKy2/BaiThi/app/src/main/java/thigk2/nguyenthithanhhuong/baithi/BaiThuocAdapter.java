package thigk2.nguyenthithanhhuong.baithi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BaiThuocAdapter extends RecyclerView.Adapter<BaiThuocAdapter.ViewHolder> {

    private List<BaiThuoc> danhSach;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(BaiThuoc item);
    }

    public BaiThuocAdapter(List<BaiThuoc> danhSach, OnItemClickListener listener) {
        this.danhSach = danhSach;
        this.listener = listener;
    }

    // Cập nhật danh sách khi tìm kiếm
    public void updateList(List<BaiThuoc> newList) {
        this.danhSach = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bai_thuoc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiThuoc item = danhSach.get(position);

        holder.img_medicine.setImageResource(item.getAnh());
        holder.tvTen.setText(item.getTenBaiThuoc());
        holder.tvCongDung.setText(item.getCongDung());
        holder.tvThoiGian.setText("⏱ " + item.getThoiGian());

        // Click listener
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));

        // Animation stagger
        holder.itemView.setAlpha(0f);
        holder.itemView.setTranslationY(30f);
        holder.itemView.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(300)
                .setStartDelay(position * 50L)
                .start();
    }

    @Override
    public int getItemCount() {
        return danhSach.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen, tvCongDung, tvThoiGian;
        ImageView img_medicine;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_medicine      = itemView.findViewById(R.id.img_medicine);
            tvTen       = itemView.findViewById(R.id.tv_ten_bai_thuoc);
            tvCongDung  = itemView.findViewById(R.id.tv_cong_dung);
            tvThoiGian  = itemView.findViewById(R.id.tv_thoi_gian);
        }
    }
}