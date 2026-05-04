package ntu.nguyenthithanhhuong.todolistapp;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter {
    List<TASKS> dataSource;

    public TaskAdapter(List<TASKS> dataSource) {
        this.dataSource = dataSource;
    }

    public class TaskItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView ten, ngay, noiDung, uuTien;
        public int position;

        public TaskItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ten = itemView.findViewById(R.id.tvTaskName);
            ngay = itemView.findViewById(R.id.tvTaskDate);
            noiDung = itemView.findViewById(R.id.tvTaskMessage);
            uuTien = itemView.findViewById(R.id.tvTaskPriority);
        }

        @Override
        public void onClick(View view) {
            //lay vi tri
            int viTri = getAdapterPosition();


            TASKS task = dataSource.get(viTri);

            Toast.makeText(view.getContext(), "Bạn vừa chọn việc: " + task.getName(), Toast.LENGTH_SHORT).show();


        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TaskItemViewHolder viewHolder = (TaskItemViewHolder) holder;
       viewHolder.position = position;
       TASKS task = dataSource.get(position);
       viewHolder.ten.setText(task.getName());
       viewHolder.ngay.setText(task.getDate());
       viewHolder.noiDung.setText(task.getMessage());
       viewHolder.uuTien.setText(task.getPriority());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}
