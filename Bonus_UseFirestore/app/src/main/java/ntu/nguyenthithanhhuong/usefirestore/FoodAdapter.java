package ntu.nguyenthithanhhuong.usefirestore;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private List<MonAn> list;

    public FoodAdapter(List<MonAn> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvDesc;
        ImageView imgFood;

        public ViewHolder(View itemView) {
            super(itemView);
            // Ánh xạ các View từ layout item_food.xml mới
            tvName = itemView.findViewById(R.id.tvFoodName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            imgFood = itemView.findViewById(R.id.imgFood);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonAn mon = list.get(position);

        holder.tvName.setText(mon.tenMon);
        holder.tvPrice.setText(String.format("%,d đ", mon.gia)); // Định dạng giá có dấu phẩy (vd: 50,000đ)

        if (holder.tvDesc != null) {
            holder.tvDesc.setText(mon.moTa);
        }

        // XỬ LÝ HIỂN THỊ ẢNH BASE64
        if (mon.hinhAnh != null && !mon.hinhAnh.isEmpty()) {
            try {
                // Giải mã chuỗi Base64 thành mảng byte
                byte[] decodedString = Base64.decode(mon.hinhAnh, Base64.DEFAULT);
                // Chuyển mảng byte thành Bitmap
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                // Hiển thị lên ImageView
                holder.imgFood.setImageBitmap(decodedByte);
            } catch (Exception e) {
                // Nếu lỗi giải mã, hiện ảnh mặc định
                holder.imgFood.setImageResource(android.R.drawable.ic_menu_report_image);
            }
        } else {
            // Nếu không có ảnh, hiện ảnh mặc định
            holder.imgFood.setImageResource(R.drawable.img);
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}