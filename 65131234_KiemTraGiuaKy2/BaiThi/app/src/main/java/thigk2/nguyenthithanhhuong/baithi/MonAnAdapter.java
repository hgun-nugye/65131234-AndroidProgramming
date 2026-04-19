package thigk2.nguyenthithanhhuong.baithi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MonAnAdapter extends ArrayAdapter<MonAn> {

    private final Context context;
    private List<MonAn> danhSach;

    public MonAnAdapter(Context context, List<MonAn> danhSach) {
        super(context, 0, danhSach);
        this.context = context;
        this.danhSach = danhSach;
    }

    // Cập nhật danh sách khi tìm kiếm
    public void updateList(List<MonAn> newList) {
        this.danhSach = newList;
        clear();
        addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_mon_an, parent, false);
            holder = new ViewHolder();
            holder.img_food = convertView.findViewById(R.id.img_food);
            holder.tvTen = convertView.findViewById(R.id.tv_ten_mon);
            holder.tvMoTa = convertView.findViewById(R.id.tv_mo_ta);
            holder.tvCalo = convertView.findViewById(R.id.tv_calo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MonAn item = danhSach.get(position);
        holder.img_food.setImageResource(item.getAnh());
        holder.tvTen.setText(item.getTenMon());
        holder.tvMoTa.setText(item.getMoTa());
        holder.tvCalo.setText(item.getCalo());

        // Animation stagger nhẹ
        convertView.setAlpha(0f);
        convertView.animate().alpha(1f).setDuration(250).setStartDelay(position * 40L).start();

        return convertView;
    }

    static class ViewHolder {
        TextView tvTen, tvMoTa, tvCalo;
        ImageView img_food;
    }
}
