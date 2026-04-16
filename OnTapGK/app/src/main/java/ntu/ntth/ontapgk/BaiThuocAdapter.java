package ntu.ntth.ontapgk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BaiThuocAdapter extends RecyclerView.Adapter<BaiThuocAdapter.ViewHolder> {
    private List<BaiThuoc> dsBaiThuoc;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(BaiThuoc item);
    }
    public BaiThuocAdapter(List<BaiThuoc> dsBaiThuoc, OnItemClickListener listener) {
        this.dsBaiThuoc = dsBaiThuoc;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_bai_thuoc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiThuoc item = dsBaiThuoc.get(position);
        holder.tvTen.setText(item.getTen());
        holder.tvThoiGian.setText("Thời gian: " + item.getThoiGian());
        holder.ivHinh.setImageResource(item.getHinhAnh());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsBaiThuoc.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHinh;
        TextView tvTen, tvThoiGian;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHinh = itemView.findViewById(R.id.iv_bai_thuoc);
            tvTen = itemView.findViewById(R.id.tv_ten_bai_thuoc);
            tvThoiGian = itemView.findViewById(R.id.tv_thoi_gian);
        }
    }
}
