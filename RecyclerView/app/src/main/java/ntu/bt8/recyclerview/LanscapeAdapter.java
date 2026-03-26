package ntu.bt8.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class LanscapeAdapter extends RecyclerView.Adapter<LanscapeAdapter.ItemLandHolder> {
    Context context;
    ArrayList<Landscape> lstData;

    public LanscapeAdapter(Context context, ArrayList<Landscape> lstData) {
        this.context = context;
        this.lstData = lstData;
    }

    @NonNull
    @Override
    public ItemLandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater cai_bom = LayoutInflater.from(context);
        View view = cai_bom.inflate(R.layout.item_land, parent, false);
        ItemLandHolder holderCreated = new ItemLandHolder(view);
        return holderCreated;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLandHolder holder, int position) {
        //lay doi tuong hien thi
        Landscape landscapeHienThi = lstData.get(position);
        //trich thong tin
        String caption = landscapeHienThi.getCaption();
        String tenAnh=landscapeHienThi.getImageName();
        //Dat cac truong thong tin cua holder
        holder.tvCaption.setText(caption);
            //dat anh
            String packageName = holder.itemView.getContext().getPackageName();
            int imageID=holder.itemView.getResources().getIdentifier(tenAnh, "mipmap", packageName);
        holder.imgViewLand.setImageResource(imageID);
    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }

    class ItemLandHolder extends RecyclerView.ViewHolder{
        TextView tvCaption;
        ImageView imgViewLand;

        public ItemLandHolder(@NonNull View itemView) {
            super(itemView);
            this.tvCaption = itemView.findViewById(R.id.textViewCaption);
            this.imgViewLand = itemView.findViewById(R.id.imageViewLand);
        }
    }


}
