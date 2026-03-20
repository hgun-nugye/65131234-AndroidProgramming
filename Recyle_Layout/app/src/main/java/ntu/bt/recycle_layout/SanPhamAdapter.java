package ntu.bt.recycle_layout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {
    Context context;
    List<SanPham> list;

    public SanPhamAdapter(Context context, List<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    // Lớp ViewHolder giữ tham chiếu đến các view trong layout của mỗi item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDesc, txtPrice;
        ImageView imgProduct, imgCart;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            imgCart = itemView.findViewById(R.id.imgCart);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_san_pham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SanPham p = list.get(position);

        holder.txtName.setText(p.ten);
        holder.txtDesc.setText(p.moTa);
        holder.txtPrice.setText(p.gia);
        holder.imgProduct.setImageResource(p.anhMH);

        holder.imgCart.setOnClickListener(v ->
                Toast.makeText(context, "Đã thêm vào giỏ", Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
